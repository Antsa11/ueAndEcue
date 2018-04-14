package org.admin.beans;

public class Candidat {
	private int id_record;
	private int num_bacc;
	private int  n_dossier;
    private String nom_prenom;
    
    private String nom;
    private String prenoms;
    
    private String choix;
    private String serie;
    private float math;
    private float pc;
    private float sn;
    private float moyenne;
    private String mention="";
    private int annee;
    private String ddn;
    
    private String centre;
    
    private String obs;
    
    private String tel;
    
    private boolean isConfirmed;
    private boolean isSelected;
    
    private String num_recu;
    
    public Candidat()
    {
    	
    }
    
	public Candidat(int num_bacc, int n_dossier, String nom_prenom) {
		super();
		this.num_bacc = num_bacc;
		this.n_dossier = n_dossier;
		this.nom_prenom = nom_prenom;
	}
	
	public void setTel(String tel)
	{
		this.tel=tel;
	}
	public String getTel()
	{
		return this.tel;
	}
	
	public void setObs(String obs)
	{
		this.obs=obs;
	}
	public String getObs()
	{
		return this.obs;
	}
	
	public void setDdn(String ddn)
	{
		this.ddn=ddn;
	}
	public String getDdn()
	{
		return this.ddn;
	}
	
	public void setCentre(String centre)
	{
		this.centre=centre;
	}
	
	public String getCentre()
	{
		return this.centre;
	}
	
	public int getId_record() {
		return id_record;
	}
	public void setId_record(int id_record) {
		this.id_record = id_record;
	}
	
	public int getNum_bacc() {
		return num_bacc;
	}
	public void setNum_bacc(int num_bacc) {
		this.num_bacc = num_bacc;
	}
	public int getN_dossier() {
		return n_dossier;
	}
	public void setN_dossier(int n_dossier) {
		this.n_dossier = n_dossier;
	}
	public String getNom_prenom() {
		return nom_prenom;
	}
	
	public String getNom() {
		return this.nom;
	}
	public String getPrenoms() {
		return this.prenoms;
	}
	public void setNom_prenom(String nom_prenom) {
		this.nom_prenom = nom_prenom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}
	public String getChoix() {
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public float getMath() {
		return math;
	}
	public void setMath(float math) {
		this.math = math;
	}
	public float getPc() {
		return pc;
	}
	public void setPc(float pc) {
		this.pc = pc;
	}
	public float getSn() {
		return sn;
	}
	public void setSn(float sn) {
		this.sn = sn;
	}
	public float getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}
	public String getMention() {
		return mention;
	}
	public void setMention(String mention) {
		this.mention = mention;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
    
    public void setIsConfirmed(boolean isConfirmed)
    {
		this.isConfirmed=isConfirmed;
	}
	public boolean getIsConfirmed()
	{
		return this.isConfirmed;
	}
	public void setIsSelected(boolean isSelected)
    {
		this.isSelected=isSelected;
	}
	public boolean getIsSelected()
	{
		return this.isSelected;
	}
	public void setNum_recu(String num_recu)
	{
		this.num_recu=num_recu;
	}
	public String getNum_recu()
	{
		return this.num_recu;
	}

}
