package fr.mediaweb.persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class MediathequeDocument implements Document{
	
	private int IdD;
	private String typeD;
	private String empruntePar;
	private String titreD;
	private String auteurD;
	private String descriptionD;

	public MediathequeDocument(int IdD, String typeD, String empruntePar, String titreD, String auteurD, String descriptionD) {
		this.IdD = IdD;
		this.typeD = typeD;
		this.empruntePar = empruntePar;
		this.titreD = titreD;
		this.auteurD = auteurD;
		this.descriptionD = descriptionD;
	}

	@Override
	public boolean disponible() {
		if(empruntePar != null)
			return true;
		return false;
	}

	@Override
	public void emprunt(Utilisateur u) throws Exception {
		assert(empruntePar == null);
		this.empruntePar = u.name();
	}

	@Override
	public void retour() {
		assert(empruntePar != null);
		this.empruntePar =null;
	}


}
