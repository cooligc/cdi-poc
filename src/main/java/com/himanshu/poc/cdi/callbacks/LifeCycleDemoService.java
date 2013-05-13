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
package com.himanshu.poc.cdi.callbacks;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("lifeCycleDemoService")
@RequestScoped	//So that PostConstruct and PreDestroy can be seen in a single request, usually will keep it as ApplicationScoped
//@ApplicationScoped
public class LifeCycleDemoService {

	@PostConstruct
	private void init() {
		System.out.println("I can open some resources here bounded by this service");
	}

	@PreDestroy
	private void cleanup() {
		System.out.println("I will close resources here bounded by this service");
	}

	public void testMe() {
		System.out.println("Inside TestME of LifeCycleDemoService");
	}
}
