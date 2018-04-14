package org.admin.dao.impl.scolarite;
import java.sql.*;

import java.util.List;
import java.util.ArrayList;
import org.admin.beans.scolarite.EtudiantImport;

import org.admin.dao.DaoFactory;
import org.admin.dao.EtudiantImportDao;
import org.admin.dao.CandidatDao;
import org.admin.dao.DaoException;
import org.admin.dao.InscriptionDao;
import org.admin.dao.UtilisateurDao;

/*
public class EtudiantImportDaoImpl implements EtudiantImportDao{

	private DaoFactory daoFactory;
	private InscriptionDao inscriptionDao;
	private UtilisateurDao utilisateurDao;

	public EtudiantImportDaoImpl(DaoFactory daoFactory)
	{
		this.daoFactory = daoFactory;
		inscriptionDao=daoFactory.getInscriptionDao();
		utilisateurDao=daoFactory.getUtilisateurDao();
	}

	public List<EtudiantImport> validateInscription()throws DaoException{

		List<EtudiantImport> etudiantImpList = new ArrayList<EtudiantImport>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Statement statement = null;
		ResultSet resultat = null;
		//String query = "SELECT * FROM \"Vue_import_mention_parcours_idEtudiant\"";
		try{

			connection = daoFactory.getConnection();
			statement = connection.createStatement();
			preparedStatement = connection.prepareStatement( "SELECT * FROM \"Vue_import_mention_parcours_idEtudiant\";");
			resultat = preparedStatement.executeQuery();

			while(resultat.next()){

				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");
				String niveau = resultat.getString("niveau");
				String anne_u = resultat.getString("anne_u");

				EtudiantImport etudiantImp1 = new EtudiantImport();
				etudiantImp1.setNom_etu(nom);
				etudiantImp1.setPrenom_etu(prenom);
				etudiantImp1.setNiveau(niveau);
				etudiantImp1.setAnneUn(anne_u);

				etudiantImpList.add(etudiantImp1);
				System.out.println(nom + " " + prenom + " " + niveau + " " + anne_u);
			}
			resultat.close();
			statement.close();
			connection.close();

		}catch(SQLException e){

			throw new DaoException("Erreur SQL: "+e.getMessage());
		}
		return etudiantImpList;

	}
*/
	///public void validerInscription(Etudiant etudiants ,Utilisateur userConnected, String num_recu) throws DaoException
	//{
		/*Etudiant etudiantDetails=this.getDetailsFrom(etudiant);

		this.insertEtudiant(etudiantDetails);

		//System.out.println(userConnected);

		//System.out.println("Last record="+this.getLastIdEtudiant());
		Inscription inscription=new Inscription();
		inscription.setId_etudiant(this.getLastIdEtudiant());
		inscription.setAnnee_univ(annee_univ);
		inscription.setConfirmedByUserId(utilisateurDao.getUserId(userConnected));
		inscription.setObs(etudiant.getObs());
		inscription.setPortail(etudiant.getChoix());
		inscription.setNum_recu(num_recu);
		inscriptionDao.addInscription(inscription);

		this.confirmInfoSaisieEtudiant(etudiant);*/
		// Connection connection = null;
		// //PreparedStatement preparedStatement = null;
		// Statement statement = null;
		// Result resultat = null;
		// String query="SELECT * FROM t_import_inscription INNER JOIN t_etudiants ON t_import_inscription.nom=t_etudiants.nom AND t_import_inscription.prenom=t_etudiants.prenom";

		// try{

		// 	connection = daoFactory.getConnection();
		// 	statement = connection.statement(query);
		// 	resultat = statement.executeQuery();
		// 	while (resultat.next()) {
		// 		String nom = resultat.getString("nom");
		// 		String prenom = resultat.getString("prenom");
		// 		String ddn = resultat.getString("ddn");
		// 		String lieu_naissance = resultat.getString("lieu_naissance");
		// 		String adresse = resultat.getString("adresse");
		// 		String mention= resultat.getString("mention");
		// 		System.out.println(nom +" " + prenom + " " + ddn +" " + lieu_naissance + " " + adresse + " " + mention);

		// 	}
		// 	connection.close();
		// }

		// catch (SQLException e) {

		// 	e.printStackTrace();
		// 	System.out.println("tena hoatran tena ts nety ann");
		// }

	//}
//}
