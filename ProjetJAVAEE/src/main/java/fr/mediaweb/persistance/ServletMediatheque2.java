package fr.mediaweb.persistance;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myservlet")
public class ServletMediatheque2 extends HttpServlet {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// MediathequeData myClass = new MediathequeData();
	    	// response.setContentType("text/html");
			// RequestDispatcher view;
			
	        if (request.getParameter("BtnEmp") != null) {
	           // myClass.emprunterDocument(0, request.getParameter("nomU"));
	            System.out.println("bouton emprunt appuyé");
	        } else if (request.getParameter("BtnRend") != null) {
	            // myClass.retournerDocument(0);
	            System.out.println("bouton retourner appuyé");
	        } 
	        
	    }

}