package fr.mediaweb.persistance;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.PersistentMediatheque;
import mediatek2022.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MediathequeData implements PersistentMediatheque {
	
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
	}

	private MediathequeData() {
	}
	
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		Connection conn = ConnectSQL.getConnection();
		Statement stmt;
		ResultSet res;
		List<Document> documents = new ArrayList<>();
		try {
			stmt = conn.createStatement();

			res = stmt.executeQuery(
					"SELECT `id_d`, `type_d`, `emprunt_d`, `titre_d`, `auteur_d`, `options_d` FROM document;");

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
	
	@Override
	public Utilisateur getUser(String login, String password) {
		Connection conn = ConnectSQL.getConnection();
		PreparedStatement stmt;
		ResultSet res;
		MediathequeUtilisateur utilisateur = null;
		try {
			stmt = conn
					.prepareStatement("SELECT `id_u`, `nom_u`, `type_u` FROM utilisateur WHERE `login`=? AND `mdp`=?;");

			stmt.setString(1, login);
			stmt.setString(2, password);

			res = stmt.executeQuery();

			utilisateur = res.next() ? new MediathequeUtilisateur(res.getInt(1), res.getString(2), res.getString(3))
					: null;

			res.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
	
	@Override
	public Document getDocument(int numDocument) {
		Connection conn = ConnectSQL.getConnection();
		PreparedStatement stmt;
		ResultSet res;
		MediathequeDocument document = null;
		try {
			stmt = conn.prepareStatement(
					"SELECT `id_d`, `type_d`, `emprunt_d`, `titre_d`, `auteur_d`, `options_d` FROM document WHERE `id_d`=?;");

			stmt.setInt(1, numDocument);
			res = stmt.executeQuery();

			document = res.next()
					? new MediathequeDocument(res.getInt(1), res.getString(2), res.getInt(3), res.getString(4),
							res.getString(5), res.getString(6))
					: null;

			res.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return document;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		Connection conn = ConnectSQL.getConnection();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("INSERT INTO document (`type_d`, `auteur_d`, `titre_d`) VALUES (?, ?, ?);");

			stmt.setString(1, (String) args[0]);
			stmt.setString(2, (String) args[1]);
			stmt.setString(3, (String) args[2]);

			stmt.execute();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
