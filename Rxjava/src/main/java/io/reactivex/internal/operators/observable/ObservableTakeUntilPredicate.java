/**
 * Copyright (c) 2016-present, RxJava Contributors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package io.reactivex.internal.operators.observable;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableTakeUntilPredicate<T> extends AbstractObservableWithUpstream<T, T> {
  final Predicate<? super T> predicate;

  public ObservableTakeUntilPredicate(ObservableSource<T> source, Predicate<? super T> predicate) {
    super(source);
    this.predicate = predicate;
  }

  @Override
  public void subscribeActual(Observer<? super T> s) {
    source.subscribe(new TakeUntilPredicateObserver<T>(s, predicate));
  }

  static final class TakeUntilPredicateObserver<T> implements Observer<T>, Disposable {
    final Observer<? super T> actual;
    final Predicate<? super T> predicate;
    Disposable s;
    boolean done;

    TakeUntilPredicateObserver(Observer<? super T> actual, Predicate<? super T> predicate) {
      this.actual = actual;
      this.predicate = predicate;
    }

    @Override
    public void onSubscribe(Disposable s) {
      if (DisposableHelper.validate(this.s, s)) {
        this.s = s;
        actual.onSubscribe(this);
      }
    }

    @Override
    public void dispose() {
      s.dispose();
    }

    @Override
    public boolean isDisposed() {
      return s.isDisposed();
    }

    @Override
    public void onNext(T t) {
      if (!done) {
        actual.onNext(t);
        boolean b;
        try {
          b = predicate.test(t);
        } catch (Throwable e) {
          Exceptions.throwIfFatal(e);
          s.dispose();
          onError(e);
          return;
        }
        if (b) {
          done = true;
          s.dispose();
          actual.onComplete();
        }
      }
    }

    @Override
    public void onError(Throwable t) {
      if (!done) {
        done = true;
        actual.onError(t);
      } else {
        RxJavaPlugins.onError(t);
      }
    }

    @Override
    public void onComplete() {
      if (!done) {
        done = true;
        actual.onComplete();
      }
    }
  }
}
