package com.airhacks;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "Servlet", value="/affiche")
public class TestServlet extends HttpServlet {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    { 
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");

	    String title = "bonjour Mr";
	    Date date = new Date();

	    out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");

        out.print("Identifiant");
        out.println("<input>");
        out.print("Identifiant");
        out.println("<input>");
        out.println(date);
        out.println("</body>");
        out.println("</html>");
    }

}
