<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <section class="tables">   
            <div class="container-fluid">
              <div class="row">
                <div class="col-lg-12">
                  <div class="card">
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle">
                          <i class="fa fa-ellipsis-v"></i>
                        </button>
                        <div aria-labelledby="closeCard1" class="dropdown-menu dropdown-menu-right has-shadow">
                          <a href="#" class="dropdown-item remove"><i class="fa fa-times"></i>Close</a>
                          <a href="#" class="dropdown-item edit"><i class="fa fa-gear"></i>Edit</a>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-8">
                         <div class="card-header d-flex align-items-center">
                          <h4 class="h4">Liste des étudiants en :</h4>  ${niveau} ${nom_parcours}
                        </div>
                      </div>
                      <div class="col-md-4">
                         <form method="post" action="pdf_list_etudiant">
                          <input type="hidden" name="niveauEtu" value="${niveau}">
                          <input type="hidden" name="parcoursEtu" value="${nom_parcours}">
                          <input type="submit" value="Exporter" class="btn btn-primary">
                        </form>
                      </div> 
                    </div>

                    <table class="table">
                      <thead>
                        <tr>
                          <th></th>
                          <th>Nom</th>
                          <th>Prénom</th>
                          <!--<th>Niveau</th>-->
                          <th></th>
                          <th></th>
                          <th></th>
                          <th></th>
                          <th></th>
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach var="etudiant" items="${etudiants}">
                          <tr>
                              <td>
                                <input type="hidden" name="${etudiant.id_parcours}">
                              </td>
                              <td>${etudiant.nom}</td>
                              <td>${etudiant.prenom}</td>
                              <td>
                                    <button type="button" class="btn btn-primary modaly" data-toggle="modal" data-target="#details_${etudiant.id_etudiant}">
                                      Détails
                                    </button>
                              </td>
                              <td>
                                <button class="btn btn-success" data-toggle="modal" data-target="#photo_${etudiant.id_etudiant}">Photo</button>
                              </td>
                    
                              <!--<td>${etudiant.niveau}</td>-->
                              <td>
                                 <form method="post" action="cursus">
                                    <input type="hidden" name="cursus" value="${etudiant.id_etudiant}">
                                    <button type="button" class="btn btn-primary modaly" data-toggle="modal" data-target="#cursus_${etudiant.id_etudiant}">
                                      Cursus
                                    </button>
                                </form>
                              </td>
                              <td>
                                 <form method="post" action="carte">
                                    <input type="hidden" name="CE" value="${etudiant.id_etudiant}" />
                                    <button type="button" class="btn btn-primary modaly" data-toggle="modal" data-target="#carte_${etudiant.id_etudiant}">Carte</button>
                                </form>
                              </td>
                              <td>
                                 <form method="post" action="#">
                                    <button type="button" class="btn btn-primary modaly" data-toggle="modal" data-target="#certSco_${etudiant.id_etudiant}">CS</button>
                                </form>
                              </td>
                              <td>
                                 <form method="post" action="#">
                                    <input type="submit" class="btn btn-warning" value="RN" />
                                </form>
                              </td>
                             
                          </tr>
                      </c:forEach>
                      </tbody>                        
                   </table>
                </div>
            </div>
        </div>
    </div>
</section> 
  <c:forEach var="etudiant" items="${etudiants}">
            <div class="modal fade bd-example-modal-lg" id="details_${etudiant.id_etudiant}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="ModalLabel">${etudiant.nom} ${etudiant.prenom}</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="row">
                                <div class="col-md-4">
                                    Mention : ${etudiant.nom_mention}
                                </div>
                                <div class="col-md-4">
                                    Parcours : ${etudiant.nom_parcours}
                                </div>
                                <div class="col-md-4">
                                    Date de naissance : ${etudiant.ddn}
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    Adresse : ${etudiant.adresse}
                                </div>
                                <div class="col-md-6">
                                    Tel : ${etudiant.tel}
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    Année universitaire : ${etudiant.annee_u}
                                </div>
                                <div class="col-md-6">
                                    Situation : ${etudiant.situation}
                                </div>
                            </div>



                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Les cursus des étudiants -->
  <div class="modal fade bd-example-modal-lg" id="cursus_${etudiant.id_etudiant}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title" id="ModalLabel">${etudiant.nom} ${etudiant.prenom}</h3>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="table">
                                    <table>
                                        <thead>
                                            <th>Année-Universitaire</th>
                                            <th>Parcours</th>
                                            <th>Mention</th>
                                            <th>Niveau</th>
                                            <th>Situation</th>
                                        </thead>
                                            
                                        <tbody>
                                            <c:forEach var="cursus" items="${cursus}">
                                                <c:if test="${etudiant.id_etudiant eq cursus.id_etudiant}">
                                                    <td>${cursus.annee_u}</td>
                                                    <td>${cursus.nom_parcours}</td>
                                                    <td>${cursus.nom_mention}</td>
                                                    <td>${cursus.niveau}</td>
                                                    <td>${cursus.situation}</td>
                                                </c:if>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
    
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                            </div>
                        </div>
                    </div>    
                </div>
<!-- Carte des étudiants -->
<div class="modal fade bd-example-modal-lg" id="carte_${etudiant.id_etudiant}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                              <div class="col-md-3">  
                               <img src="img/logo_scs.png" width="50%" height="100px">
                               </div>
                              <div class="col-md-6">
                               <h3 align="center">UNIVERSITE D'ANTANANARIVO</h3><br>
                               <h4 align="center">Faculté des Sciences</h4><br>
                               <h5 align="center">"Domaine Sciences et Téchonlogies"</h5><br>
                               <h3 align="center">CARTE D'ÉTUDIANT</h3>
                              </div>
                              <div class="col-md-3">  
                               <img src="img/logo_ua.png" width="50%" height="100px" style="float: right;">
                               </div>
                                <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button> -->
                            </div>
                            <div class="modal-body">                              
                                <h6>${etudiant.nom}</h6>
                                <h6>${etudiant.prenom}</h6>
                                <div class="col-md6">
                                <label>Né(e) le : ${etudiant.ddn}</label><br>
                                <label>Adresse : ${etudiant.adresse}</label><br>
                                <h6>Niveau : ${etudiant.niveau} ${etudiant.situation}</h6>
                                <label>Parcours : ${etudiant.nom_parcours}</label><br>
                                <label>Mention : ${etudiant.nom_mention}</label>
                                </div>
                                  <label class="col-md-4 ml-auto" style="float: right;">Le responsable</label>
                            
                              <form method="post" action="pdf_carte_etudiant" enctype="multipart/form-data">
                                
                                <input type="hidden" name="nomEtudiant" value="${etudiant.nom}">
                                <input type="hidden" name="prenomEtudiant" value="${etudiant.prenom}">
                                <input type="hidden" name="ddnEtudiant" value="${etudiant.ddn}">
                                <input type="hidden" name="adresseEtudiant" value="${etudiant.adresse}">
                                <input type="hidden" name="niveauEtudiant" value="${etudiant.niveau}">
                                <input type="hidden" name="parcoursEtudiant" value="${etudiant.nom_parcours}">
                                <input type="hidden" name="mentionEtudiant" value="${etudiant.nom_mention}">
                                <div class="control-group">
                                    <label for="text">Entrer ici la photo de cette étudiant</label>
                                   <input type="file" name="file">
                                </div>
                            
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-primary" value="Exporter">
                            </form>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                            </div>
                        </div>
                    </div>
    
                </div>

<!-- Photo des étudiants !-->
   <div class="modal fade bd-example-modal-lg" id="photo_${etudiant.id_etudiant}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true"> 

          <div class="modal-dialog modal-lg" role="document">
              <div class="modal-content">
                  <div class="modal-header">
                      <h2>Importer ici la photo de cette étudiant</h2>
                  </div>
                  <div class="modal-body">
                    <form action="importPhoto" method="post" enctype="multipart/form-data">
                      <div class="control-group">
                          <label for="text">Entrer ici la photo de cette étudiant</label>
                         <input type="file" name="file">
                      </div>
                      <div class="control-group">
                        <input type="hidden" name="nom" value="${etudiant.nom}">
                        <input type="hidden" name="prenom" value="${etudiant.prenom}">
                      </div>
                  </div>
                  <div class="modal-footer">
                      <div class="control-group">
                        <input type="submit" value="Enregistrer" class="btn btn-success">
                      </div> 
                    </form>
                       <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                  </div>
              </div>
          </div>
        
      </div>

  <div class="modal fade bd-example-modal-lg" id="certSco_${etudiant.id_etudiant}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                              <div class="col-md-3">  
                               <img src="img/logo_scs.png" width="50%" height="100px">
                               </div>
                              <div class="col-md-6">
                               <h3 align="center">UNIVERSITE D'ANTANANARIVO</h3><br>
                               <h4 align="center">Faculté des Sciences</h4><br>
                               <h5 align="center">"Domaine Sciences et Téchonlogies"</h5><br>
                               <h3 align="center">CERTIFICAT DE SCOLARITÉ</h3>
                              </div>
                              <div class="col-md-3">  
                               <img src="img/logo_ua.png" width="50%" height="100px" style="float: right;">
                               </div>
                                <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button> -->
                            </div>
                            <div class="modal-body">                              
                                <h6>${etudiant.nom}</h6>
                                <h6>${etudiant.prenom}</h6>
                                <div class="col-md6">
                                <label>Né(e) le : ${etudiant.ddn}</label><br>
                                <label>Adresse : ${etudiant.adresse}</label><br>
                                <h6>Niveau : ${etudiant.niveau} ${etudiant.situation}</h6>
                                <label>Parcours : ${etudiant.nom_parcours}</label><br>
                                <label>Mention : ${etudiant.nom_mention}</label>
                                </div>
                                  <label class="col-md-4 ml-auto" style="float: right;">Le responsable</label>
                            
                              <form method="post" action="pdf_scola_etudiant">
                                
                                <input type="hidden" name="nomEtudiant" value="${etudiant.nom}">
                                <input type="hidden" name="prenomEtudiant" value="${etudiant.prenom}">
                                <input type="hidden" name="ddnEtudiant" value="${etudiant.ddn}">
                                <input type="hidden" name="adresseEtudiant" value="${etudiant.adresse}">
                                <input type="hidden" name="niveauEtudiant" value="${etudiant.niveau}">
                                <input type="hidden" name="parcoursEtudiant" value="${etudiant.nom_parcours}">
                                <input type="hidden" name="mentionEtudiant" value="${etudiant.nom_mention}">
                            
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-primary" value="Exporter">
                            </form>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                            </div>
                        </div>
                    </div>
    
                </div>


</c:forEach>
                                <!-- <div class="table">
                                    <table>
                                        <thead>
                                            <th>Année-Universitaire</th>
                                            <th>Parcours</th>
                                            <th>Mention</th>
                                            <th>Niveau</th>
                                            <th>Situation</th>
                                        </thead>
                                            
                                        <tbody>
                                            <c:forEach var="carte" items="${carte}">
                                                <c:if test="${etudiant.id_etudiant eq carte.id_etudiant}">
                                                    <td>${carte.annee_u}</td>
                                                    <td>${carte.nom_parcours}</td>
                                                    <td>${carte.nom_mention}</td>
                                                    <td>${carte.niveau}</td>
                                                    <td>${carte.situation}</td>
                                                </c:if>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div> -->
