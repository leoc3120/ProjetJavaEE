package fr.mediaweb.persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;


import java.sql.SQLException;
import java.util.List;
public interface DataManager  {
    List<Document> tousLesDocumentsDisponibles() throws SQLException;
    Utilisateur getUser(String login, String password) throws SQLException;
    Document getDocument(int numDocument) throws SQLException;
    void ajoutDocument(int type, Object... args) throws SQLException;
    int emprunterDocument(int numDocument, String nomUtilisateur) throws SQLException;
    void retournerDocument(int numDocument) throws SQLException;
}
