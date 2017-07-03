/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package main.org.apache.commons.proxy2.provider;

import main.org.apache.commons.proxy2.ObjectProvider;

import java.io.Serializable;


/**
 * Always returns the same object.
 * 
 * @since 1.0
 */
public class ConstantProvider<T> implements ObjectProvider<T>, Serializable
{
    /** Serialization version */
    private static final long serialVersionUID = 1L;

    //******************************************************************************************************************
    // Fields
    //******************************************************************************************************************

    private final T constant;

    //******************************************************************************************************************
    // Constructors
    //******************************************************************************************************************

    /**
     * Create a new ConstantProvider instance.
     * 
     * @param constant
     */
    public ConstantProvider(T constant)
    {
        this.constant = constant;
    }

    //******************************************************************************************************************
    // ObjectProvider Implementation
    //******************************************************************************************************************

    /**
     * {@inheritDoc}
     */
    @Override
    public T getObject()
    {
        return constant;
    }
}   
