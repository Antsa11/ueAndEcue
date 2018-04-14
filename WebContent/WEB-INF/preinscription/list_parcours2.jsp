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
                          <a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
                          <a href="#" class="dropdown-item edit"><i class="fa fa-gear"></i>Edit</a>
                        </div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">Liste des parcours</h3>
                      <form method="post" action="#">
                          <input type="submit" class="btn btn-primary" value="PDF" align="right" />
                      </form>
                    </div>
                    <table class="table">
                      <thead>
                        <tr>
                            <c:forEach var="parcours" items="${parcours}">
                            <td><label>${parcours.nom_parcours}</label>
                            </td>
                            <td>${parcours.niveau}</td>
                            <td>${parcours.nom_responsable}</td>
                            <td>${parcours.tel}</td>
                             <td>${parcours.n_etudiant}</td>
                            <td>
                                <form method="post" action="list_etudiant">
                                    <input type="hidden" name="id_parcours" value="${parcours.id_parcours}"/>
                                    <input type="hidden" name="niveau" value="${parcours.niveau}"/>
                                    <input type="submit" class="btn btn-primary" value="Student" />
                            </form>
                            <td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#ajoutUe_${parcours.id_parcours}">UE</button></td>
                            <td><button type="button" class="btn btn-success" data-toggle="modal" data-target="#ajoutEcue_${parcours.id_parcours}">ECUE</button></td>                          
                        </tr>
                      </thead>  

<div class="modal fade bd-example-modal-lg enregis" id="ajoutUe_${parcours.id_parcours}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
          <h1 class="modal-title" id="exampleModalLabel">Ajout des UE et ECUES</h1>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
      </div>
      <div class="modal-body">

        
        <form method="post" action="ajoutUe" class="form-inline">

          <div class="row">
            <div class="input-group">
              <label for="text">Nom du responsable </label>
              <input type="text" name="nomResp" class="form-control" style="margin-left:4%;">
            </div>
          </div>
          <div class="row" style="margin-top:4%;">
            <div class="input-group">
              <label for="text">Nom de l'UE </label>
              <input type="text" name="nomUe" class="form-control" style="margin-left:16%;">

             <!--  <input type="hidden" name="parcour" value="${parcours.id_parcours}"> -->
              <input type="hidden" value="${parcours.id_parcours}" id="idParcours" name="id_parcours">
              <input type="hidden" value="${parcours.niveau}" name="niveau">
              <input type="hidden" value="${parcours.id_mention}" name="mention">

            </div>
          </div>
          <div class="row" style="margin-top:4%;">
            <div class="input-group">
              <label for="text">Crédit UE</label>
              <input type="text" name="credit" class="form-control" style="margin-left:20%;">
            </div>
          </div>

        

        
      </div>

      <div class="modal-footer">
          <input type="submit" class="btn btn-success" value="Enregistrer" id="enreg">
        </form>

        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
        
      </div>
    </div>
  </div>
</div>


<div class="modal fade bd-example-modal-lg enregis" id="ajoutEcue_${parcours.id_parcours}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
          <h1 class="modal-title" id="exampleModalLabel">Ajout ECUE</h1>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
      </div>
      <div class="modal-body">

        
        <form method="post" action="ajoutEcue" class="form-inline">

          <div class="row">
            <div class="input-group">
              <label for="text">Nom du responsable </label>
              <input type="text" name="nomRespEc" class="form-control" style="margin-left:4%;">
            </div>
          </div>
          <div class="row" style="margin-top:4%;">
            <div class="input-group">
              <label for="text">Nom de l'ECUE </label>
              <input type="text" name="nomEcue" class="form-control" style="margin-left:16%;">

             <!--  <input type="hidden" name="parcour" value="${parcours.id_parcours}"> -->
              <input type="hidden" value="${parcours.id_parcours}" id="idParcours" name="id_parcours">
              <input type="hidden" value="${parcours.niveau}" name="niveau">

            </div>
          </div>
          <div class="row" style="margin-top:4%;">
            <div class="input-group">
              <label for="text">Crédit ECUE</label>
              <input type="text" name="credite" class="form-control" style="margin-left:20%;">
            </div>
          </div>

          <div class="row" style="margin-top:4%;">
            <div class="input-group">
              <div class="row">
                <label for="text">Selectionner l'UE correspondant</label>
              </div>
              <div class="row">
                <select name="nomUeco" class="form-control" style="">
                <!-- <c:forEach var="ue" items="${ue}">
                    <option value="${ue.id_ue}">${ue.nomUe}</option>
                </c:forEach>  -->
                 <!--  <option value="0">nom</option>
                  <option value="1">nom1</option>
                  <option value="2">nom2</option> -->
                </select>
              </div>   
            </div>
          </div>

        
      </div>

      <div class="modal-footer">
          <input type="submit" class="btn btn-success" value="Enregistrer" id="enreg">
        </form>

        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
        
      </div>
    </div>
  </div>
</div>


                      </c:forEach>
                   </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Ajout modal de UE !-->



</section>
<c:forEach var="ue" items="${ue}">
                    <option value="${ue.id_ue}">${ue.nomUe}</option>
                </c:forEach> 
<script type="text/javascript" src="jquery/jquery-3.2.1.js"></script>
<!--<script type="text/javascript" src="jquery/stock.js"></script>!-->
