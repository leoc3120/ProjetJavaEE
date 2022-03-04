package fr.mediaweb.persistance;

import mediatek2022.Utilisateur;

public class MediathequeUtilisateur implements Utilisateur {
	
	private int IdU;
	private String nomU;	
	private String typeU;

	public MediathequeUtilisateur(int idU, String nomU, String typeU) {
		this.IdU = idU;
		this.nomU = nomU;
		this.typeU = typeU;
	}

	@Override
	public String name() {
		return this.nomU;
	}

	@Override
	public boolean isBibliothecaire() {
		if(this.typeU=="biblicoth�caire") {
			return true;
		}
		return false;
	}

	@Override
	public Object[] data() {
		return null;
	}


}
