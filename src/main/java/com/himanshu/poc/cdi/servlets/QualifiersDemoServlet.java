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
package com.himanshu.poc.cdi.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.himanshu.poc.cdi.qualifiers.GreetingService;
import com.himanshu.poc.cdi.qualifiers.Informal;

@WebServlet(name="qualifiersServlet", asyncSupported=true, urlPatterns={"/qualifiersdemo"})
public class QualifiersDemoServlet extends HttpServlet {
	
	@Inject
	@Named("formal")
	private GreetingService greetingService1;
	
	@Inject
	@Informal		//Using qualifiers
	private GreetingService greetingService2;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name = "Himanshu";
		String formalGreet = greetingService1.greet(name);
		String informalGreet = greetingService2.greet(name);
		res.getOutputStream().write((formalGreet+"<br />"+informalGreet).getBytes());
	}

}
