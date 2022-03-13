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
import javax.servlet.http.HttpSession;

@WebServlet(name = "Servlet", value = "/")
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
			if (utilisateurEnCours == null
					&& (request.getParameter("login") != null || request.getParameter("mdp") != null)) {
				request.setAttribute("msgAction", "Indentifiants incorrects");
			}
		}

		// Cas d'un utilisateur connecté
		if (utilisateurEnCours != null) {
			request.setAttribute("nomU", utilisateurEnCours.name());

			// Cas d'un d'un bibliothécaire
			if (utilisateurEnCours.isBibliothecaire() == true) {
				Object arg1, arg2, arg3 = null;
				// Bouton "Ajout de nouveau document"
				if (request.getParameter("BtnAjou") != null) {
					view = request.getRequestDispatcher("WEB-INF/template/pagepBiblio.jsp");
					arg1 = request.getParameter("type");
					arg2 = request.getParameter("titre");
					arg3 = request.getParameter("auteur");

					if (arg1 == null || arg2 == "" || arg3 == "") {
						request.setAttribute("msgAction", "Veuillez remplir tous les champs");
					} else {
						maMediatheque.ajoutDocument(0, arg1, arg2, arg3);
						request.setAttribute("msgAction", "Nouveau document ajouté");
					}
				}
				// Bouton "Se déconnecter"
				else if (request.getParameter("BtnDeco") != null ) {
					view = request.getRequestDispatcher("WEB-INF/template/accueil.jsp");
					request.setAttribute("login", null);
					request.setAttribute("mdp", null);
					utilisateurEnCours = null;
					request.setAttribute("msgAction", "Déconnection");

				}
				
				else {
					view = request.getRequestDispatcher("WEB-INF/template/pagepBiblio.jsp");
				}
				view.forward(request, response);

				// Cas d'un abonné
			} else {
				List<Document> listeDocuments = maMediatheque.tousLesDocumentsDisponibles();
				request.setAttribute("documents", listeDocuments);
				request.setAttribute("nbDoc", listeDocuments.size());

				// Bouton "Emprunter un document"
				if (request.getParameter("BtnEmp") != null) {
					view = request.getRequestDispatcher("WEB-INF/template/pagep.jsp");

					// Pour ne pas pouvoir sélectionner la ligne "Veuillez choisir un document"
					if (request.getParameter("listeD") == "") {
						request.setAttribute("msgAction", "Veuillez sélectionner un document à emprunter");

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
				}
				
				// Bouton "Rendre un document"
				else if (request.getParameter("BtnRend") != null) {
					view = request.getRequestDispatcher("WEB-INF/template/pagep.jsp");
					// Pour ne pas pouvoir sélectionner la ligne "Veuillez choisir un document"
					if (request.getParameter("listeD") == "") {
						request.setAttribute("msgAction", "Veuillez sélectionner un document à rendre");

						// Sélection du document choisi
					} else {
						Document doc = listeDocuments.get(Integer.parseInt(request.getParameter("listeD").substring(0,
								request.getParameter("listeD").indexOf(" "))) - 1);
						try {
							// Si le document est disponible, alors on ne peut pas le rendre vu qu'on ne l'a pas emprunté
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
				}
				
				// Bouton "Se déconnecter"
				else if (request.getParameter("BtnDeco") != null ) {
					view = request.getRequestDispatcher("WEB-INF/template/accueil.jsp");
					request.setAttribute("login", null);
					request.setAttribute("mdp", null);
					utilisateurEnCours = null;
					request.setAttribute("msgAction", "Déconnection");
				}
				else {
					view = request.getRequestDispatcher("WEB-INF/template/pagep.jsp");
					request.setAttribute("login", null);
					request.setAttribute("mdp", null);
					utilisateurEnCours = null;
				}
				view.forward(request, response);
			}

			// Cas d'aucune utilisateur connecté
		} else {
			view = request.getRequestDispatcher("WEB-INF/template/accueil.jsp");
			view.forward(request, response);
		}
	}
}
