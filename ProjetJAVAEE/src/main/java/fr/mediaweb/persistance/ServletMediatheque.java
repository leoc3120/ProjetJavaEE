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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html");
		RequestDispatcher view;

		// Si aucun utilisateur n'est connecté, alors on tente de se connecter avec les
		// login/mdp entrés
		if (utilisateurEnCours == null) {
			utilisateurEnCours = maMediatheque.getUser(request.getParameter("login"), request.getParameter("mdp"));
			if (utilisateurEnCours == null && request.getParameter("login") != null
					&& request.getParameter("mdp") != null) {
				request.setAttribute("msgAction", "Indentifiants incorrects");
			}
		}

		if (utilisateurEnCours != null) {
			request.setAttribute("nomU", utilisateurEnCours.name());
			if (utilisateurEnCours.isBibliothecaire() == true) {
				System.out.println("biblio connecté");
				view = request.getRequestDispatcher("WEB-INF/template/pagepBiblio.jsp");
				Object arg1, arg2, arg3 = null;
				if (request.getParameter("BtnAjou") != null) {
					arg1 = request.getParameter("type");
					arg2 = request.getParameter("titre");
					arg3 = request.getParameter("auteur");

					if(arg1 == null || arg2 == null  || arg3 == null) {
						request.setAttribute("msgAction", "Veuillez remplir tous les champs");
					}
					else {
						maMediatheque.ajoutDocument(0, arg1, arg2, arg3);
						request.setAttribute("msgAction", "Nouveau document ajouté");
					}
				}
				view.forward(request, response);

			} else {
				view = request.getRequestDispatcher("WEB-INF/template/pagep.jsp");
				List<Document> listeDocuments = maMediatheque.tousLesDocumentsDisponibles();
				request.setAttribute("documents", listeDocuments);
				request.setAttribute("nbDoc", listeDocuments.size());

				// Bouton "Emprunter un document"
				if (request.getParameter("BtnEmp") != null) {
					// Pour ne pas pouvoir sélectionner la ligne "Veuillez choisir un document"
					if (request.getParameter("listeD") == "") {
						request.setAttribute("msgAction", "Veuillez sélectionner un document");

						// Sélection du document choisi
					} else {
						Document doc = listeDocuments.get(Integer.parseInt(request.getParameter("listeD").substring(0,
								request.getParameter("listeD").indexOf(" "))) - 1);
						try {
							// Si le document n'est pas disponible, on ne peut pas l'emprunter
							if (!doc.disponible()) {
								request.setAttribute("msgAction",
										doc.toString() + " n'est pas disponible pour le moment");

								// Emprunt du document
							} else {
								maMediatheque.emprunt(doc, utilisateurEnCours);
								request.setAttribute("msgAction", "Vous avez emprunté " + doc.toString());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					// Bouton "Rendre un document"
				} else if (request.getParameter("BtnRend") != null) {
					// Pour ne pas pouvoir sélectionner la ligne "Veuillez choisir un document"
					if (request.getParameter("listeD").startsWith("--")) {
						request.setAttribute("msgAction", "Veuillez sélectionner un document");

						// Sélection du document choisi
					} else {
						Document doc = listeDocuments.get(Integer.parseInt(request.getParameter("listeD").substring(0,
								request.getParameter("listeD").indexOf(" "))) - 1);
						try {
							// Si le document est disponible, alors on ne peut pas le rendre vu qu'on ne l'a
							// pas emprunté
							if (doc.disponible() == true) {
								request.setAttribute("msgAction", "Vous n'empruntez pas " + doc.toString()
										+ " pour le moment, vous ne pouvez pas le rendre !");

								// Retour du document
							} else {
								maMediatheque.retour(doc, utilisateurEnCours);
								request.setAttribute("msgAction", "Vous avez rendu " + doc.toString());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}


					// Bouton "Se déconnecter"
					/*
					 * } else if (request.getParameter("BtnDeco") != null) { utilisateurEnCours =
					 * null; view = request.getRequestDispatcher("WEB-INF/template/accueil.jsp");
					 * response.sendRedirect("WEB-INF/template/accueil.jsp"); }
					 */
				}
				view.forward(request, response);
			}
		}
		else {
			view = request.getRequestDispatcher("WEB-INF/template/accueil.jsp");
			view.forward(request, response);
		}
	}
}
