package com.example.code.model;

public enum LaymanType {
	ANIMAL("Animals","Animaux"),
	ART("Art/Culture","Art/Culture"),
	EDUCATION("Education","Éducation"),
	ENVIRONMENT("Environment","Environment"),
	HEALTH("Health","Santé"),
	SOCIAL("Human Services","Services à la Personne"),
	FOREIGN("International","International"),
	PUBLIC("Public Benefit","D'Intérêt Public "),
	RELIGION("Religion","Religion"),
	RESEARCH("Research","Recherche"),
	SUPPORT("Support Groups","Groupes de Soutien");
	
	boolean language = true;
	
	private String desc_en;
	private String desc_fr;
	
	private LaymanType (String english, String french) {
		this.desc_en = english;
		this.desc_fr = french;
	}
	
	
	private LaymanType (String english, String french, boolean language) {
		this(english, french);
		this.language = language;
	}
	
	public String getDesc() {
		if (language) {
			return desc_en;
		}
		else {
			return desc_fr;
		}
	}
}
