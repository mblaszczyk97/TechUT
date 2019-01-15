package com.example.shdemo.domain;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "danetechniczne.all", query = "Select m from DaneTechniczne m"),
		@NamedQuery(name = "danetechniczne.filmiku", query = "Select p from DaneTechniczne p")

})
public class DaneTechniczne {
	private Long id;

	private String opis = "opis";
	private String rozdzielczosc = "4:3";
	private String typ_kamery = "cyfrowa";
	private int minut_filmu = 120;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public String getRozdzielczosc() {
		return rozdzielczosc;
	}
	public void setRozdzielczosc(String rozdzielczosc) {
		this.rozdzielczosc = rozdzielczosc;
	}
	public String getTyp_kamery() {
		return typ_kamery;
	}
	public void setTyp_kamery(String typ_kamery) {
		this.typ_kamery = typ_kamery;
	}
	public int getMinut_filmu() {
		return minut_filmu;
	}
	public void setMinut_filmu(int minut_filmu) {
		this.minut_filmu = minut_filmu;
	}


	public DaneTechniczne() {
		
	}
	
}
