package fr.mediaweb.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class ConnectSQL {

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
	
	private Integer getUserID(String nomUtilisateur) {
		Connection conn = getConnection();
		PreparedStatement stmt;
		ResultSet res;
		Integer id = null;
		try {
			stmt = conn.prepareStatement("SELECT `id_u` FROM utilisateur WHERE `nom_u`=?;");

			stmt.setString(1, nomUtilisateur);

			res = stmt.executeQuery();

			id = res.next() ? res.getInt(1) : null;

			res.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	
	
	public int emprunterDocument(int numDocument, String nomUtilisateur) {
		int id_u = getUserID(nomUtilisateur);

		Connection conn = getConnection();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("UPDATE document SET `emprunt_d`=? WHERE `id_d`=?;");

			stmt.setInt(1, id_u);
			stmt.setInt(2, numDocument);

			stmt.execute();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_u;
	}

	public void retournerDocument(int numDocument) {
		Connection conn = getConnection();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("UPDATE document SET `emprunt_d`=-1 WHERE `id_d`=?;");

			stmt.setInt(1, numDocument);

			stmt.execute();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<Document> tousLesDocumentsEmpruntés(Utilisateur u) {
		Connection conn = getConnection();
		PreparedStatement stmt;
		ResultSet res;
		List<Document> documents = new ArrayList<>();
		try {
			stmt = conn.prepareStatement(
					"SELECT `id_d`, `type_d`, `emprunt_d`, `titre_d`, `auteur_d`, `options_d` FROM document WHERE `emprunt_d`=?;");

			stmt.setInt(1, getUserID(u.name()));
			res = stmt.executeQuery();
			

			while (res.next())
				documents.add(new MediathequeDocument(res.getInt(1), res.getString(2), res.getInt(3),
						res.getString(4), res.getString(5), res.getString(6)));
			res.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return documents;
	}
	
	
	
}
