package com.himanshu.poc.cdi.async;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.AsyncContext;
import javax.servlet.ServletRequest;

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

public class AsyncRequestHandler implements Runnable {

	private AsyncContext asyncContext;
	private boolean dispatch;

	public AsyncRequestHandler(AsyncContext asyncContext) {
		this.asyncContext = asyncContext;
		dispatch = true;
	}

	public AsyncRequestHandler(AsyncContext asyncContext, boolean dispatch) {
		this(asyncContext);
		this.dispatch = dispatch;
	}

	public void run() {
        //do something asynchronously –
        //  wait for a resource to become available
        //  or for a long running computation to finish
        String reqId = asyncContext.getRequest().getParameter("id");
        if (null == reqId || reqId.length() == 0) {
        	reqId = "<unk>";
        }
        long id = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        if (null == threadName || threadName.length() == 0)
            threadName = "";
        String threadId = id + ":" + threadName;

        String result = longRunningProcess(reqId, threadId);

        //Store the result of the computation in a "view helper"
        ServletRequest req = asyncContext.getRequest();
        if (null != req && req.isAsyncStarted()) {
        	req.setAttribute("result", result);
        } else {
        	return;
        }

        //once done, dispatch back to the final destination
        if (dispatch) {
            asyncContext.dispatch("/results.jsp");
        } else {
            //alternatively handle it ourselves
            String html = "Result of the process for request id: " +
                    reqId + "<br/> Started at:" +
                    asyncContext.getRequest().getAttribute("receivedAt") +
                    ".<br/> Completed at:" + result +
                    "<br/> Called complete.";
            asyncContext.getResponse().setContentType("text/html");
            try {
                PrintWriter out = asyncContext.getResponse().getWriter();
                out.println(html);
            } catch(Exception e) {
                e.printStackTrace();
            }
            asyncContext.complete();
        }
    }

    public String longRunningProcess(String reqId, String threadId) {
        Random randomGenerator = new Random();
        int randomInt = 5000 + randomGenerator.nextInt(10000);

        System.out.println("AsyncRequestProcessor: Begin long run process (" +
                    randomInt + " ms) for request: " +
                    reqId + ". on thread: " + threadId +
                    "[" + new Date() + "]");

        try {
            Thread.sleep(randomInt);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        System.out.println("AsyncRequestProcessor: Done processing request: " +
                    reqId + ". on thread: " +
                    threadId + "[" + new Date() + "]");

        return new Date().toString();
    }

}
