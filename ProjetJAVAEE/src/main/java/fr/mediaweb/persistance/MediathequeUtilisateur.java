package fr.mediaweb.persistance;

import mediatek2022.Utilisateur;

public class MediathequeUtilisateur implements Utilisateur {

	public MediathequeUtilisateur(int idU, String nom, String type) {
		this.IdU = idU;
		this.nom = nom;
		this.type = type;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBibliothecaire() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] data() {
		// TODO Auto-generated method stub
		return null;
	}

	private int IdU;
	
	private String login;
	
	private String mdp;
	
	private String nom;
	
	private String type;
}
