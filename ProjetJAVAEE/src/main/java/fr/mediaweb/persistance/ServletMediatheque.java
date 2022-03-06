package fr.mediaweb.persistance;

import mediatek2022.Mediatheque;
import mediatek2022.PersistentMediatheque;
import mediatek2022.Utilisateur;

import fr.mediaweb.persistance.MediathequeData;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", value = "/affiche")
public class ServletMediatheque extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Mediatheque m = Mediatheque.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html");
        RequestDispatcher view;
        Utilisateur u;
        u = m.getUser(request.getParameter("login"),request.getParameter("mdp"));
        if(u != null) {
            view = request.getRequestDispatcher("WEB-INF/template/pagep.jsp");
            request.setAttribute("nomU", u.name());
            request.setAttribute("login", request.getParameter("login"));
            request.setAttribute("mdp", request.getParameter("mdp"));
            /*for(int i=1; i<Mediatheque.tousLesDocumentsDisponibles().size(); i++) {
                if(i == 1) {
                    request.setAttribute("documents", Mediatheque.tousLesDocumentsDisponibles().get(i).toString());
                }
                else {
                    request.setAttribute("documents", request.getAttribute("documents") 
                            + Mediatheque.tousLesDocumentsDisponibles().get(i).toString());
                }
            };*/
            request.setAttribute("documents", m.tousLesDocumentsDisponibles());
            request.setAttribute("nbDoc", m.tousLesDocumentsDisponibles().size());
                    
            view.forward(request, response);
        }

        view = request.getRequestDispatcher("WEB-INF/template/accueil.jsp");
        view.forward(request, response);
    }
}
