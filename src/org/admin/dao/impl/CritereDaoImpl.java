package org.admin.dao.impl;

import java.sql.*;

import java.util.List;
import java.util.ArrayList;
import org.admin.beans.Critere;

import org.admin.dao.DaoFactory;
import org.admin.dao.CritereDao;
import org.admin.dao.CandidatDao;
import org.admin.dao.DaoException;

public class CritereDaoImpl implements CritereDao
{
	private DaoFactory daoFactory;
	private CandidatDao candidatDao;

	public CritereDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	public void ajouter(Critere critere) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO t_critere (description,condition,portail,id_vague) VALUES(?,?,?,?);");
			preparedStatement.setString(1,critere.getDescription());
			preparedStatement.setString(2,critere.getCondition());
			preparedStatement.setString(3,critere.getPortail());
			preparedStatement.setInt(4,critere.getId_vague());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}


	}

	public void update(Critere critere) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try{
			connexion = daoFactory.getConnection();
			//preparedStatement = connexion.prepareStatement("UPDATE t_critere SET description = ?, condition = ?, portail = ?, id_vague = ? WHERE id = ? ");
			preparedStatement = connexion.prepareStatement("UPDATE t_critere SET portail = ?, description = ?, condition = ?, id_vague = ? WHERE id = ? ");

			preparedStatement.setString(2,critere.getDescription());
			preparedStatement.setString(3,critere.getCondition());
			preparedStatement.setString(1,critere.getPortail());
			preparedStatement.setInt(4,critere.getId_vague());
			preparedStatement.setInt(5,critere.getId_critere());

			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}


	}
	public void publishedResulatFrom(int id_critere) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("UPDATE t_critere SET \"isPublished\"=true where id=? ");


			preparedStatement.setInt(1,id_critere);

			preparedStatement.executeUpdate();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}


	}
	public void reinitCriterePublished(int id_vague)throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("UPDATE t_critere SET \"isPublished\"=false WHERE id_vague = ? ");

			preparedStatement.setInt(1,id_vague);


			preparedStatement.executeUpdate();

			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void delete(int id_critere) throws DaoException
	{
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM t_critere where id_critere=?");
			preparedStatement.setInt(1,id_critere);
			// statement = connexion.createStatement();
			// statement.executeQuery("DELETE FROM t_critere where id="+id_critere);

			preparedStatement.executeQuery();

			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Critere> getCriteres() throws DaoException
	{
		List<Critere> criteres = new ArrayList<Critere>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;

		try{
			connexion = daoFactory.getConnection();
			// statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM t_critere;");
			resultat = preparedStatement.executeQuery();
			// resultat = statement.executeQuery("SELECT * FROM t_critere;");

			while(resultat.next())
			{
				int id_critere = resultat.getInt("id");
				String portail = resultat.getString("portail");
				String description = resultat.getString("description");
				String condition = resultat.getString ("condition");

				Critere critere = new Critere(id_critere,description, portail,condition);
				criteres.add(critere);
			}
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e)
		{
		        e.printStackTrace();
		}
		return criteres;
	}
	public List<Critere> getCriteresWithCandidat() throws DaoException
	{
		List<Critere> criteres = new ArrayList<Critere>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;
		//DaoFactory daoFactory=DaoFactory.getInstance();
		candidatDao=daoFactory.getCandidatDao();
		try{
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM t_critere ORDER BY portail,id ;");
			resultat = preparedStatement.executeQuery();
			// statement = connexion.createStatement();
			// resultat = statement.executeQuery("SELECT * FROM t_critere ORDER BY portail,id ;");

			while(resultat.next())
			{
				int id_critere = resultat.getInt("id");
				String portail = resultat.getString("portail");
				String description = resultat.getString("description");
				String condition = resultat.getString ("condition");
				int id_vague=resultat.getInt ("id_vague");
				Critere critere = new Critere(id_critere,description, portail.toUpperCase(),condition,id_vague);
				int countRecords=candidatDao.countRecords(critere);
				critere.setCountRecords(countRecords);
				criteres.add(critere);
			}
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e)
		{
		        e.printStackTrace();
		}
		return criteres;
	}
	public List<Critere> getCriteresWithCandidat(int vague) throws DaoException
	{
		List<Critere> criteres = new ArrayList<Critere>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;
		//DaoFactory daoFactory=DaoFactory.getInstance();
		candidatDao=daoFactory.getCandidatDao();
		try{
			connexion = daoFactory.getConnection();
			// statement = connexion.createStatement();
            if(vague!=0){
       			String query = "SELECT * FROM t_critere where id_vague= ? ORDER BY portail,id ;";
				   	preparedStatement = connexion.prepareStatement(query);
				   	preparedStatement.setInt(1, vague);
						System.out.println(preparedStatement);
       				resultat = preparedStatement.executeQuery();
				// resultat = statement.executeQuery("SELECT * FROM t_critere where id_vague='"+vague+"' ORDER BY portail,id ;");
            }
            else{
            	preparedStatement = connexion.prepareStatement("SELECT * FROM t_critere  ORDER BY portail,id ;");
            	resultat = preparedStatement.executeQuery();
           		// resultat = statement.executeQuery("SELECT * FROM t_critere  ORDER BY portail,id ;");
            }


			while(resultat.next())
			{
				int id_critere = resultat.getInt("id");
				String portail = resultat.getString("portail");
				String description = resultat.getString("description");
				String condition = resultat.getString ("condition");
				int id_vague=resultat.getInt ("id_vague");
				boolean isPublished=resultat.getBoolean ("isPublished");
				Critere critere = new Critere(id_critere,description, portail.toUpperCase(),condition,id_vague);
				critere.setIsPublished(isPublished);

				int countRecords=candidatDao.countRecords(critere);
				critere.setCountRecords(countRecords);
				criteres.add(critere);
			}
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e)
		{
		        e.printStackTrace();
		}
		return criteres;

	}
	public Critere getCritereFromId(int id_critere)throws DaoException
	{
		Critere critere=null;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		// Statement statement = null;
		ResultSet resultat = null;
		//DaoFactory daoFactory=DaoFactory.getInstance();
		candidatDao=daoFactory.getCandidatDao();
		try{
			connexion = daoFactory.getConnection();
			// statement = connexion.createStatement();
			preparedStatement = connexion.prepareStatement("SELECT * FROM t_critere");
			resultat = preparedStatement.executeQuery();
			// resultat = statement.executeQuery("SELECT * FROM t_critere");

			while(resultat.next())
			{
				int id = resultat.getInt("id");
				String portail = resultat.getString("portail");
				String description = resultat.getString("description");
				String condition = resultat.getString ("condition");
				int id_vague=resultat.getInt("id_vague");
				if(id==id_critere)
				{
					critere = new Critere(id_critere,description, portail.toUpperCase(),condition,id_vague);
				}
			}
			resultat.close();
			preparedStatement.close();
			connexion.close();
		}
		catch (SQLException e)
		{
		        e.printStackTrace();
		}
		return critere;
	}


}
