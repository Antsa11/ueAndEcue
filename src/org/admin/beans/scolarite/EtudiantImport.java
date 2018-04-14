package org.admin.beans.scolarite;
import org.admin.beans.Candidat;

public class EtudiantImport extends Candidat{

	private String nom_etu;
	private String prenom_etu;
	private String ddn;
	private String lieu_naiss;
	private String sexe;
	private String adresse;
	private String tel;
	private String anne_u;
	private String niveau;
	private int id_etudiant;
	private String specialite;
  private String situation;
  private String observation;

	public EtudiantImport()
	{
		super();
	}
	public void setNom_etu(String nom_etu)
	{
		this.nom_etu=nom_etu;
	}
	public String getNom_etu()
	{
		return this.nom_etu;
	}
	public void setPrenom_etu(String prenom_etu)
	{
		this.prenom_etu=prenom_etu;
	}
	public String getPrenom_etu()
	{
		return this.prenom_etu;
	}
	public void setAdresse(String adresse)
	{
		this.adresse=adresse;
	}
	public String getAdresse()
	{
		return this.adresse;
	}
	public void setLieu_naissance(String lieu_naiss)
	{
		this.lieu_naiss=lieu_naiss;
	}
	public String getLieu_naissance()
	{
		return this.lieu_naiss;
	}
	public void setDdn(String ddn){
		this.ddn=ddn;
    }
	public String getDdn(){
		return this.ddn;
	}
	public void setSexe(String sexe){

		this.sexe = sexe;
	}
	public String getSexe(){
		return this.sexe;
	}
	public void setNumTel(String tel){
		this.tel = tel;
	}
	public String getNumTel(){
		return this.tel;
	}

	public void setNiveau(String niveau){
		this.niveau = niveau;
	}
	public String getNiveau(){
		return this.niveau;
	}

	public void setAnneUn(String anne_u){
		this.anne_u = anne_u;
	}
	public String getAnneUn(){
		return this.anne_u;
	}

	public void setIdEtudiant(int id_etudiant){
		this.id_etudiant = id_etudiant;
	}
	public int getIdEtudiant(){
		return this.id_etudiant;
	}

	public void setSpecialite(String specialite)
    {
		this.specialite = specialite;
	}
    public String getSpecialite()
    {
		return this.specialite;
    }
    public void setSituation(String situation)
    {
		this.situation = situation;
	}
    public String getSituation()
    {
		return this.situation;
    }
    public void setObservation(String observation)
    {
		this.observation = observation;
	}
    public String getObservation()
    {
		return this.observation;
    }
}
