package org.admin.beans;
public class Bachelier
{
	private int id_candidat;
	private int num_bacc;
	private String nom_prenom;
	private String serie;
	private String centre;
	private float math;
	private float pc;
	private float sn;
	private float moyenne;
	private String mention;
	private String date_naissance;
	private String province;
	private int annee;
	private int id_portail;
	private String cree_le;
	private int id_status;
	private String importer_par;
	private String importer_le;
	private String num_fact;
	private String tel;
	private String choix;
	
	
	public Bachelier()
	{
	}
	
	public void setChoix(String choix)
	{
		this.choix=choix;
	}
	
	public String getChoix()
	{
		return this.choix;
	}
	public String getTel()
	{
		return this.tel;
	}
	
	public void setTel(String tel)
	{
		this.tel=tel;
	}
	public int getId_candidat() {
		return id_candidat;
	}

	public void setId_candidat(int id_candidat) {
		this.id_candidat = id_candidat;
	}

	public int getNum_bacc() {
		return num_bacc;
	}

	public void setNum_bacc(int num_bacc) {
		this.num_bacc = num_bacc;
	}

	public String getNom_prenom() {
		return nom_prenom;
	}

	public void setNom_prenom(String nom_prenom) {
		this.nom_prenom = nom_prenom;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getCentre() {
		return centre;
	}

	public void setCentre(String centre) {
		this.centre = centre;
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

	public String getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public int getAnnee() {
		return annee;
	}

	/*public void setAnnee(int annee) throws BeanException {
		if(annee<2015) {
			throw new BeanException("L'année doit être > 2015");
		}
		else{
		this.annee = annee;
		}

	}*/
	public void setAnnee(int annee)
	{
		this.annee = annee;
	}

	public int getId_portail() {
		return id_portail;
	}

	public void setId_portail(int id_portail) {
		this.id_portail = id_portail;
	}

	public String getCree_le() {
		return cree_le;
	}

	public void setCree_le(String cree_le) {
		this.cree_le = cree_le;
	}

	public int getId_status() {
		return id_status;
	}

	public void setId_status(int id_status) {
		this.id_status = id_status;
	}

	public String getImporter_par() {
		return importer_par;
	}

	public void setImporter_par(String importer_par) {
		this.importer_par = importer_par;
	}

	public String getImporter_le() {
		return importer_le;
	}

	public void setImporter_le(String importer_le) {
		this.importer_le = importer_le;
	}

	public String getNum_fact() {
		return num_fact;
	}

	public void setNum_fact(String num_fact) {
		this.num_fact = num_fact;
	}

	
}
