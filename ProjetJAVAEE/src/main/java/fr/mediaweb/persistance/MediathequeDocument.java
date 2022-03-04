package fr.mediaweb.persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class MediathequeDocument implements Document{

	public MediathequeDocument(int IdD, String typeD, String empruntePar, String titre, String auteur, String description) {
		this.IdD = IdD;
		this.typeD = typeD;
		this.empruntePar = empruntePar;
		this.titre = titre;
		this.auteur = auteur;
		this.Description = description;
	}

	@Override
	public boolean disponible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void emprunt(Utilisateur u) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retour() {
		// TODO Auto-generated method stub
		
	}
	private int IdD;
	
	private String typeD;
	
	private String empruntePar;
	
	private String titre;
	
	private String auteur;
	
	private String Description;

}
