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
                <div class="card-header d-flex align-items-center">
                  <h4 class="h4">Liste des étudiants en :</h4>  ${niveau} ${nom_parcours}
                  <!--<form method="post" action="#">
                      <input type="submit" class="btn btn-warning" value="PDF" />
                  </form>-->
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

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    </div>
                </div>
            </div>

        </div>

</c:forEach>
