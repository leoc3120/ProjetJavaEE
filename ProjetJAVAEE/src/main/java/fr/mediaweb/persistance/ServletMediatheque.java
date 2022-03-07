package fr.mediaweb.persistance;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", value = "/affiche")
public class ServletMediatheque extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Mediatheque maMediatheque = Mediatheque.getInstance();
	private Utilisateur utilisateurEnCours;
	private ConnectSQL c = new ConnectSQL();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        RequestDispatcher view;
        
        if(utilisateurEnCours == null) {
        	utilisateurEnCours = maMediatheque.getUser(request.getParameter("login"),request.getParameter("mdp"));
        }
        
        if(utilisateurEnCours != null) {
            view = request.getRequestDispatcher("WEB-INF/template/pagep.jsp");
            request.setAttribute("nomU", utilisateurEnCours.name());
            List<Document> listeDocuments = maMediatheque.tousLesDocumentsDisponibles();
            request.setAttribute("documents", listeDocuments);
            request.setAttribute("nbDoc", listeDocuments.size());
            //System.out.println(c.tousLesDocumentsEmpruntés(utilisateurEnCours));
    		if (request.getParameter("BtnEmp") != null) {
    			System.out.println(request.getParameter("listeD"));
        		Document doc = listeDocuments.get(Integer.parseInt(request.getParameter("listeD").substring(0,request.getParameter("listeD").indexOf(" "))));

            	try {
            		if (doc.disponible()) {
            			maMediatheque.emprunt(doc, utilisateurEnCours);
            			view.forward(request, response);
            		}
            		else {
            			System.out.println("pas dispo");
            			view.forward(request, response);
            		}
				} catch (Exception e) {
					e.printStackTrace();
				}
                 System.out.println("bouton emprunt appuyé");
                 
            } else if (request.getParameter("BtnRend") != null) {
        		Document doc = listeDocuments.get(Integer.parseInt(request.getParameter("listeD").substring(0,request.getParameter("listeD").indexOf(" "))));

                try {
                	maMediatheque.retour(doc, utilisateurEnCours);
                	view.forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
                 System.out.println("bouton retourner appuyé");
            } 
    		
            view.forward(request, response);
        }

	 view = request.getRequestDispatcher("WEB-INF/template/accueil.jsp");
     view.forward(request, response);
    }
}
