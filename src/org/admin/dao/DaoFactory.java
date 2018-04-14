package org.admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.admin.dao.UtilisateurDao;
import org.admin.dao.impl.UtilisateurDaoImpl;

import org.admin.dao.PersonnelDao;
import org.admin.dao.impl.PersonnelDaoImpl;

import org.admin.dao.BachelierDao;
import org.admin.dao.impl.BachelierDaoImpl;

import org.admin.dao.CandidatDao;
import org.admin.dao.impl.CandidatDaoImpl;

import org.admin.dao.EtudiantDao;
import org.admin.dao.impl.EtudiantDaoImpl;

import org.admin.dao.EtudiantImportDao;
import org.admin.dao.impl.EtudiantImportDaoImpl;

import org.admin.dao.CritereDao;
import org.admin.dao.impl.CritereDaoImpl;

import org.admin.dao.InscriptionDao;
import org.admin.dao.impl.InscriptionDaoImpl;

import org.admin.dao.DerogationDao;
import org.admin.dao.impl.DerogationDaoImpl;

import org.admin.dao.stat.StatPreinscriptionDao;
import org.admin.dao.stat.impl.StatPreinscriptionDaoImpl;

import org.admin.dao.MentionDao;
import org.admin.dao.impl.MentionDaoImpl;


import org.admin.dao.ParcoursDao;
import org.admin.dao.impl.ParcoursDaoImpl;

import org.admin.dao.StudentDao;
import org.admin.dao.impl.StudentDaoImpl;

import org.admin.dao.CursusDao;
import org.admin.dao.impl.CursusDaoImpl;

import org.admin.dao.CarteDao;
import org.admin.dao.impl.CarteDaoImpl;

import org.admin.dao.UeDao;
import org.admin.dao.impl.UeDaoImpl;

import org.admin.dao.EcueDao;
import org.admin.dao.impl.EcueDaoImpl;



public class DaoFactory
{
	private String url;
	private String username;
	private String password;
	DaoFactory(String url, String username,String password)
	{
		this.url=url;
		this.username=username;
		this.password=password;
	}

	public static DaoFactory getInstance()
	{
		try{
			Class.forName("org.postgresql.Driver");
		}
		catch (ClassNotFoundException e)
		{
		}
		DaoFactory instance =new DaoFactory("jdbc:postgresql://localhost:5432/bdd2","postgres","123456");

		return instance;
	}

	public Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url,username,password);
	}

	public UtilisateurDao getUtilisateurDao()
	{
		return new UtilisateurDaoImpl(this);
	}
	public CandidatDao getCandidatDao()
	{
		return new CandidatDaoImpl(this);
	}
	public EtudiantDao getEtudiantDao()
	{
		return new EtudiantDaoImpl(this);
	}
	public BachelierDao getBachelierDao()
	{
		return new BachelierDaoImpl(this);
	}

	public CritereDao getCritereDao()
	{
		return new CritereDaoImpl(this);
	}

	public StatPreinscriptionDao getStatPreinscriptionDao()
	{
		return new StatPreinscriptionDaoImpl(this);
	}

	public InscriptionDao getInscriptionDao()
	{
		return new InscriptionDaoImpl(this);
	}

	public PersonnelDao getPersonnelDao()
	{
		return new PersonnelDaoImpl(this);
	}

	public DerogationDao getDerogationDao()
	{
		return new DerogationDaoImpl(this);
	}
	public UrlDao getUrlDao()
	{
		return new UrlDaoImpl(this);
	}
	public EtudiantImportDao getEtudiantImportDao()
	{
		return new EtudiantImportDaoImpl(this);
	}
	public MentionDao getMentionDao(){
		return new MentionDaoImpl(this);
	}

	public ParcoursDao getParcoursDao(){
		return new ParcoursDaoImpl(this);
	}

	public StudentDao getStudentDao(){
		return new StudentDaoImpl(this);
	}

	public CursusDao getCursusDao(){
		return new CursusDaoImpl(this);
	}

	public CarteDao getCarteDao(){
		return new CarteDaoImpl(this);
	}

	public UeDao getUeDao(){
		return new UeDaoImpl(this);
	}

	public EcueDao getEcueDao(){
		return new EcueDaoImpl(this);
	}
}
