package com.spring.main.entities;

import java.util.List;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vehicule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String marque;
	private String model;
	private String immatriculation;
	private double prix;
	@ManyToOne
	private Client client;
	@OneToMany(mappedBy = "vehicule")
	private List<Accident> accidents;
	
	public Vehicule() {
		accidents = new Vector<Accident>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Accident> getAccidents() {
		return accidents;
	}

	public void setAccidents(List<Accident> accidents) {
		this.accidents = accidents;
	}

	@Override
	public String toString() {
		return "Vehicule [id=" + id + ", marque=" + marque + ", model=" + model + ", immatriculation=" + immatriculation
				+ ", prix=" + prix + ", client=" + client + ", accidents=" + accidents + "]";
	}

}
