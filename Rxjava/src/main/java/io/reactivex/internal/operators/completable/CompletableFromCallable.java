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

package io.reactivex.internal.operators.completable;

import java.util.concurrent.Callable;

import io.reactivex.*;
import io.reactivex.disposables.*;
import io.reactivex.exceptions.Exceptions;

public final class CompletableFromCallable extends Completable {

  final Callable<?> callable;

  public CompletableFromCallable(Callable<?> callable) {
    this.callable = callable;
  }

  @Override
  protected void subscribeActual(CompletableObserver s) {
    Disposable d = Disposables.empty();
    s.onSubscribe(d);
    try {
      callable.call();
    } catch (Throwable e) {
      Exceptions.throwIfFatal(e);
      if (!d.isDisposed()) {
        s.onError(e);
      }
      return;
    }
    if (!d.isDisposed()) {
      s.onComplete();
    }
  }
}
