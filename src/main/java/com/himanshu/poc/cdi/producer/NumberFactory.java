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
package com.himanshu.poc.cdi.producer;

import javax.enterprise.inject.Produces;

public class NumberFactory {
	
	private int max = 199;
	
	private java.util.Random random = new java.util.Random( System.currentTimeMillis() );

	@Produces
	@MaxNumber
	public int getMax() {
		return max;
	}
	
	/*
	 * If the value can vary at runtime, the process is slightly different. For example, the following code defines a producer method that generates a random number defined by a qualifier called @Random:

private java.util.Random random = 
    new java.util.Random( System.currentTimeMillis() );

java.util.Random getRandom() {
        return random;
}

@Produces @Random int next() {
    return getRandom().nextInt(maxNumber);
}

When you inject this object in another managed bean, you declare a contextual instance of the object:

@Inject @Random Instance<Integer> randomInt;

You then call the get method of the Instance:

this.number = randomInt.get();
	 */
	@Produces
	@Random
	public int getRandomNumber() {
		return random.nextInt();
	}
	
}
