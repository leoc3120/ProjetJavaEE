package fr.mediaweb.persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", value = "/affiche")
public class TestServlet extends HttpServlet implements DataManager {

	private static final long serialVersionUID = 1L;
	private static final String url = "jdbc:mysql://tijger.o2switch.net:3306/vmvo1438_mediaweb";
    private static final String user = "vmvo1438_mediaweb";
    private static final String password = "mediaweb4568";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html");
		RequestDispatcher view;
		Utilisateur u;
		try {
			u = getUser(request.getParameter("login"),request.getParameter("mdp"));
			if(u != null) {
				view = request.getRequestDispatcher("WEB-INF/template/pagep.jsp");
				request.setAttribute("nomU", u.name());
				for(int i=1; i<tousLesDocumentsDisponibles().size(); i++) {
					if(i == 1) {
						request.setAttribute("documents", tousLesDocumentsDisponibles().get(i).toString());
					}
					else {
						request.setAttribute("documents", request.getAttribute("documents") 
								+ tousLesDocumentsDisponibles().get(i).toString());
					}
				};
						
				view.forward(request, response);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}

		view = request.getRequestDispatcher("WEB-INF/template/accueil.jsp");
		view.forward(request, response);
	}

    @Override
    public List<Document> tousLesDocumentsDisponibles() throws SQLException {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("SELECT `id_d`, `type_d`, `emprunt_d`, `titre_d`, `auteur_d`, `options_d` FROM document;");

        List<Document> documents = new ArrayList<>();
        while (res.next()) documents.add(new MediathequeDocument(res.getInt(1), res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6)));

        res.close();
        stmt.close();
        conn.close();

        return documents;
    }

    @Override
    public Utilisateur getUser(String identifiant, String mdp) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT `id_u`, `nom_u`, `type_u` FROM utilisateur WHERE `login`=? AND `mdp`=?;");
        stmt.setString(1, identifiant);
        stmt.setString(2, mdp);

        ResultSet res = stmt.executeQuery();

        MediathequeUtilisateur utilisateur = res.next() ? new MediathequeUtilisateur(res.getInt(1),
                res.getString(2),
                res.getString(3)) : null;

        res.close();
        stmt.close();
        conn.close();

        return utilisateur;
    }

    private Integer getUserID(String nomUtilisateur) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT `id_u` FROM utilisateur WHERE `nom_u`=?;");
        stmt.setString(1, nomUtilisateur);

        ResultSet res = stmt.executeQuery();

        Integer id = res.next() ? res.getInt(1) : null;

        res.close();
        stmt.close();
        conn.close();

        return id;
    }

    @Override
    public Document getDocument(int numDocument) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT `id_d`, `type_d`, `emprunt_d`, `titre_d`, `auteur_d`, `options_d` FROM document WHERE `id_d`=?;");
        stmt.setInt(1, numDocument);

        ResultSet res = stmt.executeQuery();

        MediathequeDocument document = res.next() ? new MediathequeDocument(res.getInt(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                res.getString(5),
                res.getString(6)) : null;

        res.close();
        stmt.close();
        conn.close();

        return document;
    }

    @Override
    public void ajoutDocument(int type, Object... args) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO document (`type_d`, `auteur_d`, `titre_d`) VALUES (?, ?, ?);");
        stmt.setString(1, (String) args[0]);
        stmt.setString(2, (String) args[1]);
        stmt.setString(3, (String) args[2]);

        stmt.execute();
        stmt.close();
        conn.close();
    }

    @Override
    public int emprunterDocument(int numDocument, String nomUtilisateur) throws SQLException {
        int id_u = getUserID(nomUtilisateur);

        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE document SET `emprunt_d`=? WHERE `id_d`=?;");
        stmt.setInt(1, id_u);
        stmt.setInt(2, numDocument);

        stmt.execute();
        stmt.close();
        conn.close();

        return id_u;
    }

    @Override
    public void retournerDocument(int numDocument) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE document SET `emprunt_d`=-1 WHERE `id_d`=?;");
        stmt.setInt(1, numDocument);

        stmt.execute();
        stmt.close();
        conn.close();
    }
    
}
