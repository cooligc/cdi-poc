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
import java.util.Date;
import java.util.concurrent.Executor;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.himanshu.poc.cdi.async.AsyncRequestHandler;
import com.himanshu.poc.cdi.async.RequestAsyncListener;


@WebServlet(name="asyncServlet", asyncSupported=true, urlPatterns={"/asyncdemo"})
public class AsyncServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("AsyncServlet: Processing request:  on thread: " + Thread.currentThread().getId() + ":" + Thread.currentThread().getName() + "[" + new Date() + "]");
		req.setAttribute("receivedAt", new Date());
		AsyncContext asyncContext = req.startAsync(req, res);
		asyncContext.addListener(new RequestAsyncListener());
		Executor executor = (Executor)req.getServletContext().getAttribute("executor");
	    //delegate long running process to an "async" thread
	    executor.execute(new AsyncRequestHandler(asyncContext, false));
	    System.out.println("AsyncServlet: Returning after request: on thread: " +
	                    Thread.currentThread().getId() + ":" +
	                    Thread.currentThread().getName()+ "[" +
	                    new Date() + "]");
	}

}
