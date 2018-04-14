package org.admin.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/baseservlet")
public class BaseServlet extends HttpServlet {

//	protected String base_url="http://172.16.1.10:8080/admin";
	//protected String base_url="http://fac-scs.univ-antananarivo.mg";//I changed the url into a locale one
    protected String base_url="http://localhost:8180/admin";
    /*
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
        super();

    }
}
