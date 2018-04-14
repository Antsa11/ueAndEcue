package org.admin.beans;

public class Critere{
	
	private int id_critere;
	private String description;
	private String portail;
	private String condition;
	private float noteMath;
	private float notePc;
	private float noteSvt;

	private int id_vague;
	
	private int countRecords;
	
	private boolean isPublished;

	private boolean isSelected=false;
	
	public Critere()
	{
		
	}
	
	public Critere(String description, String portail, String condition)
	{
		this.description=description;
		this.portail=portail;
		this.condition=condition;
	}
	
	public Critere(int id_critere, String description, String portail, String condition)
	{
		this.id_critere=id_critere;
		this.description=description;
		this.portail=portail;
		this.condition=condition;
	}
	public Critere(int id_critere, String description, String portail, String condition,int id_vague)
	{
		this.id_critere=id_critere;
		this.description=description;
		this.portail=portail;
		this.condition=condition;
		this.id_vague=id_vague;
	}
	
	public void setDescription(String description)
	{
		this.description=description;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setPortail(String portail)
	{
		this.portail=portail;
	}
	
	public String getPortail()
	{
		return this.portail;
	}
	
	public void setCondition(String condition)
	{
		this.condition=condition;
	}
	
	public String getCondition()
	{
		return this.condition;
	}
	
	public void setId_critere(int id_critere)
	{
		this.id_critere=id_critere;
	}
	
	public int getId_critere()
	{
		return this.id_critere;
	}
	public void setId_vague(int id_vague)
	{
		this.id_vague=id_vague;
	}
	
	public int getId_vague()
	{
		return this.id_vague;
	}
	
	public void setCountRecords(int countRecords)
	{
		this.countRecords=countRecords;
	}
	
	public int getCountRecords()
	{
		return this.countRecords;
	}
	
	public boolean getIsPublished()
	{
		return this.isPublished;
	}
	public void setIsPublished(boolean isPublished)
	{
		this.isPublished=isPublished;
	}
	public float getNoteMath()
	{
		return this.noteMath;
	}
	public void setNoteMath(float noteMath)
	{
		this.noteMath =noteMath; 
	}
	public float getNotePc()
	{
		return this.notePc;
	}
	public void setNotePc(float notePc)
	{
		this.notePc=notePc;
	}
	public float getNoteSvt()
	{
		return this.noteSvt;
	}
	public void setNoteSvt(float noteSvt)
	{
		this.noteSvt = noteSvt;
	}
	public float calculMoyenne(float nbre)
	{
		float moy=0;

		return moy;
	}
}
