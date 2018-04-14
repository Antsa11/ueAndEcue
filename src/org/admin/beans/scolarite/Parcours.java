package org.admin.beans.scolarite;

import java.util.List;
import org.admin.beans.scolarite.Ue;

public class Parcours {
	private int id_parcours;
	private int id_mention;
	private String nom_parcours;
	private String id_responsable;
	private String nom_responsable;
	private String niveau;
	private int n_etudiant;
	private String tel;
	private List<Ue> listUe;
	
	public Parcours(int id_parcours, int id_mention, String nom_parcours, String id_responsable) {
		super();
		this.id_parcours = id_parcours;
		this.id_mention = id_mention;
		this.nom_parcours = nom_parcours;
		this.id_responsable = id_responsable;
	}

	public Parcours(int id_parcours, int id_mention, String nom_parcours) {
		super();
		this.id_parcours = id_parcours;
		this.id_mention = id_mention;
		this.nom_parcours = nom_parcours;
	}
    public String getTel()
	{
		return this.tel;
	}
	public void setTel(String tel)
	{
		this.tel=tel;
	}
	public String getNom_responsable()
	{
		return this.nom_responsable;
	}
	public void setNom_responsable(String nom_responsable)
	{
		this.nom_responsable=nom_responsable;
	}
	public String getNiveau()
	{
		return this.niveau;
	}
	public void setNiveau(String niveau)
	{
		this.niveau=niveau;
	}
	public int getN_etudiant()
	{
		return n_etudiant;
	}
	public void setN_etudiant(int n_etudiant)
	{
		this.n_etudiant=n_etudiant;
	}
	public int getId_parcours() {
		return id_parcours;
	}

	public void setId_parcours(int id_parcours) {
		this.id_parcours = id_parcours;
	}

	public int getId_mention() {
		return id_mention;
	}

	public void setId_mention(int id_mention) {
		this.id_mention = id_mention;
	}

	public String getNom_parcours() {
		return nom_parcours;
	}

	public void setNom_parcours(String nom_parcours) {
		this.nom_parcours = nom_parcours;
	}

	public String getId_responsable() {
		return id_responsable;
	}

	public void setId_responsable(String id_responsable) {
		this.id_responsable = id_responsable;
	}

	public void setListUe(List<Ue> list){
		this.listUe = list;
	}

	public List getListUe(){
		return listUe;
	}

	@Override
	public String toString() {
		return "Parcours [id_parcours=" + id_parcours + ", id_mention=" + id_mention + ", nom_parcours=" + nom_parcours
				+ ", id_responsable=" + id_responsable + "]";
	}
	
	
	
	
}
