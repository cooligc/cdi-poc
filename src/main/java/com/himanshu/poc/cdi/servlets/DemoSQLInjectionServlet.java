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
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "demoServlet", urlPatterns = "/demosqlinjection")
public class DemoSQLInjectionServlet extends HttpServlet {


	//Vulnerable to SQLI attacks
	/*@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		try {

            String user = req.getParameter("username");
            Connection conn = null;
            String url = "jdbc:mysql://127.0.0.1:3306/";
            String dbName = "test";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root";
            String password = "root";
            try {
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url + dbName, userName, password);

                Statement st = conn.createStatement();
                String query = "SELECT * FROM  user where username='" + user + "'";
                out.println("Query : " + query);

                System.out.printf(query);
                ResultSet resultSet = st.executeQuery(query);
                out.println("<br />");
                out.println("<br />");
                out.println("\n<b>Results</b>");
                while (resultSet.next()) {
                	out.println("<br /><br />");
                    String s = resultSet.getString("username");
                    out.println("\t\t" + s);
                }
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            out.close();
        }
	}*/

	//Safe from SQLI
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		try {

            String user = req.getParameter("username");
            Connection conn = null;
            String url = "jdbc:mysql://127.0.0.1:3306/";
            String dbName = "test";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root";
            String password = "root";
            try {
                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url + dbName, userName, password);

                String query = "SELECT * FROM  user where username=?";
                java.sql.PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, user);

                out.println("Query : " + query);

                System.out.printf(query);
                ResultSet resultSet = pstmt.executeQuery();
                out.println("<br />");
                out.println("<br />");
                out.println("\n<b>Results</b>");
                while (resultSet.next()) {
                	out.println("<br /><br />");
                    String s = resultSet.getString("username");
                    out.println("\t\t" + s);
                }
                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            out.close();
        }
	}
}
