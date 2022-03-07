package fr.mediaweb.persistance;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class MediathequeDocument implements Document{
	
	private ConnectSQL c = new ConnectSQL();
	
	private int IdD;
	private String typeD;
	private int empruntePar;
	private String titreD;
	private String auteurD;
	private String descriptionD;

	public MediathequeDocument(int IdD, String typeD, int empruntePar, String titreD, String auteurD, String descriptionD) {
		this.IdD = IdD;
		this.typeD = typeD;
		this.empruntePar = empruntePar;
		this.titreD = titreD;
		this.auteurD = auteurD;
		this.descriptionD = descriptionD;
	}

	@Override
	public boolean disponible() {
		if(empruntePar == -1)
			return true;
		return false;
	}

	@Override
	public void emprunt(Utilisateur u) throws Exception {
		assert(empruntePar != -1);
		this.empruntePar = c.emprunterDocument(IdD,u.name());
	}

	@Override
	public void retour() {
		assert(empruntePar != -1);
		c.retournerDocument(IdD);
		this.empruntePar = -1;
	}
	
	@Override
	public String toString() {
		return this.typeD + " " + this.titreD + " " + this.auteurD;
	}

}
