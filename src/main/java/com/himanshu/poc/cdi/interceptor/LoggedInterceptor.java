/*
 * Copyright 2013 Himanshu Bhardwaj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.himanshu.poc.cdi.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Logged
public class LoggedInterceptor {

	@AroundInvoke
	public Object logMethodEntry (InvocationContext ctx) throws Exception {
		System.out.println("Intercepted method: "+ctx.getMethod().getName()+" for class "+ctx.getMethod().getDeclaringClass().getCanonicalName());
		for (Object obj : ctx.getParameters()) {
			System.out.println("Input parameters are : "+obj);
		}
		//Overriding the parameter
		ctx.setParameters(new Object[] {"Interceptor Called\n"});
		return ctx.proceed();
	}

}
