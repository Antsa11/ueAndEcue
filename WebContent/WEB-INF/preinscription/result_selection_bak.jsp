<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <!-- Dashboard Counts Section-->
          <section class="dashboard-counts no-padding-bottom">
            <div class="container-fluid">
              <div class="row bg-white has-shadow">
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center">
                    <div class="icon bg-violet"><i class="icon-user"></i></div>
                    <div class="title"><span>MI</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 25%; height: 4px;" aria-valuenow="{#val.value}" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-violet"></div>
                      </div>
                    </div>
                    <div class="number"><strong>${count_mi}</strong></div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center">
                    <div class="icon bg-red"><i class="icon-padnote"></i></div>
                    <div class="title"><span>PC</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 70%; height: 4px;" aria-valuenow="{#val.value}" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div>
                      </div>
                    </div>
                    <div class="number"><strong>${count_pc}</strong></div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center">
                    <div class="icon bg-green"><i class="icon-bill"></i></div>
                    <div class="title"><span>SVT</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 40%; height: 4px;" aria-valuenow="{#val.value}" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-green"></div>
                      </div>
                    </div>
                    <div class="number"><strong>${count_svt}</strong></div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center">
                    <div class="icon bg-green"><i class="icon-bill"></i></div>
                    <div class="title"><span>Total</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 40%; height: 4px;" aria-valuenow="{#val.value}" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-green"></div>
                      </div>
                    </div>
                    <div class="number"><strong>${count_mi+count_pc+count_svt}</strong></div>
                  </div>
                </div>
              </div>
            </div>
          </section>
          <!-- Dashboard Header Section    -->
          <section class="dashboard-header">
            <div class="container-fluid">
              <div class="row">
                <!-- Statistics -->
                <div class="statistics col-lg-3 col-12">
                  <div class="statistic d-flex align-items-center bg-white has-shadow">
                    <div class="icon bg-red"><a href="${ base_url }/resultat_preinscription?choix=MI"><i class="fa fa-tasks"></i></a></div>
                    <div class="text"><strong>${count_mi}</strong><br><small>MI</small> - <a href="${ base_url }/pdf_resultat_selection?choix=MI" class="btn btn-primary">Pdf</a></div>
                  </div>
                  <div class="statistic d-flex align-items-center bg-white has-shadow">
                    <div class="icon bg-green"><a href="${ base_url }/resultat_preinscription?choix=PC"><i class="fa fa-calendar-o"></i></a></div>
                    <div class="text"><strong>${count_pc}</strong><br><small>PC</small> - <a href="${ base_url }/pdf_resultat_selection?choix=PC" class="btn btn-primary">Pdf</a></div>
                  </div>
                  <div class="statistic d-flex align-items-center bg-white has-shadow">
                    <div class="icon bg-orange"><a href="${ base_url }/resultat_preinscription?choix=SVT"><i class="fa fa-paper-plane-o"></i></a></div>
                    <div class="text"><strong>${count_svt}</strong><br><small>SVT</small> - <a href="${ base_url }/pdf_resultat_selection?choix=SVT" class="btn btn-primary">Pdf</a></div>
                  </div>
                   <div class="statistic d-flex align-items-center bg-white has-shadow">
                    <div class="icon bg-orange"><i class="fa fa-paper-plane-o"></i></div>
                    <div class="text"><strong>${count_mi+count_pc+count_svt}</strong><br><small>Total</small></div>
                  </div>
                </div>
                <!-- Line Chart            -->
                     <div class="col-lg-9">
                  <div class="card">
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard4" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a><a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a></div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">Liste des étudiants préselectionnés</h3>
                    </div>
                    <div class="card-body">
                    <form class="form-horizontal" method="post" action="resultat_search">
                        
                        <div class="form-group row">       
                        
                        <div class="col-sm-3">
							<select class="custom-select" name="annee">
								<option selected>Année</option>
								<option value="2017">2017</option>
								<option value="2016">2016</option>
								<option value="2015">2015</option>
								<option value="2014">2014</option>
								<option value="2013">2013</option>
								<option value="2012">2012</option>
								<option value="2011">2011</option>
								<option value="2010">2010</option>
				
							</select>
                        </div>
                        
                        <div class="col-sm-3">
							<select class="custom-select" name="choix">
								<option selected>Choix</option>
								<option value="MI">MI</option>
								<option value="PC">PC</option>
								<option value="SVT">SVT</option>
				
							</select>
                        </div>
                        
                        <div class="col-sm-3">
							<input id="search" type="text" name="search" placeholder="Chercher">
                        </div>
                        
                         <div class="col-sm-3">
                            <input type="submit" value="Afficher" class="btn btn-primary">
                          </div>
                        
                        </div>
                        
                      </form>
                    
                     <table class="table">
                        <thead>
                          <tr>
                            <!--<th>Dossier n°</th>-->
                            <th>Nom et Prénoms</th>
                            
                           
									<th>Choix</th>
							
							
								
							
                            <th>Série</th>
                            <th>Observation</th>
                          </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="etudiant" items="${etudiants}">
							<tr>
							<!--<td>${etudiant.n_dossier}</td>-->
							<td>${etudiant.nom_prenom}</td>
							<!--<td>${etudiant.sn}</td>-->
							<td>${etudiant.choix}</td>
							<td>${etudiant.serie}</td>
							<td>
							<c:if test="${empty etudiant.obs}">
								<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#edit_obs_${etudiant.id_record}">
									Editer
								</button>
							</c:if>
							
							</td>
							</tr>
						</c:forEach>
                          
                        </tbody>
                      </table>
                      
   <!--   
  <nav aria-label="Page navigation example">
  <ul class="pagination pagination-sm">
   <c:choose>
     <c:when test="${currentPage eq 1}">
     <li class="page-item disabled">
     </c:when>
      <c:otherwise>
      <li class="page-item">
      </c:otherwise>
      </c:choose>   
      <a class="page-link" href="${ base_url }/resultat_critere?page=1&id=${id}">Début</a>
    </li>
    <c:choose>
     <c:when test="${currentPage eq 1}">
     <li class="page-item disabled">
     </c:when>
      <c:otherwise>
      <li class="page-item">
      </c:otherwise>
      </c:choose>
      <a class="page-link" href="${ base_url }/resultat_critere?page=${currentPage-1}&id=${id}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Précédent</span>
      </a>
    </li>
    <c:forEach begin="${start}" end="${end}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                           <li class="page-item active">
      <span class="page-link">
        ${i}
        <span class="sr-only">(current)</span>
      </span>
    </li>
                    </c:when>
                    <c:otherwise>
                    <li class="page-item"><a class="page-link" href="${ base_url }/resultat_critere?page=${i}&id=${id}">${i}</a></li>
                        
                    </c:otherwise>
                </c:choose>
     </c:forEach>
     <c:choose>
		<c:when test="${currentPage eq noOfPages}">
			<li class="page-item disabled">
		</c:when>
		<c:otherwise>
			<li class="page-item">
		</c:otherwise>
      </c:choose>
      <a class="page-link" href="${ base_url }/resultat_critere?page=${currentPage+1}&id=${id}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
        <span class="sr-only">Suivant</span>
      </a>
    </li>
    
    
     <c:choose>
     <c:when test="${currentPage eq noOfPages}">
     <li class="page-item disabled">
     </c:when>
      <c:otherwise>
      <li class="page-item">
      </c:otherwise>
      </c:choose>
      <a class="page-link" href="${ base_url }/resultat_critere?page=${noOfPages}&id=${id}">Fin</a>
    </li>
  </ul>
</nav>
-->

    
                    </div>
                  </div>
                </div>
                
              </div>
            </div>
          </section>
         

<!--Modal Edit-->

<c:forEach var="etudiant" items="${etudiants}">


<div class="modal fade bd-example-modal-lg" id="edit_obs_${etudiant.id_record}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="exampleModalLabel">Mise à jour - Etudiant</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
	<div class="col-md-12">
		<div class="box box-danger">
            <div class="box-header">
              <h3 class="box-title"></h3>
            </div>
            <div class="box-body">
				<form id="form" class="form-horizontal" accept-charset="utf-8" method="post" action="modifier_etudiant">
				<input type="hidden" name="id_record" value="${etudiant.id_record}"/>
		    
		
        <div class="row">
			<div class="col-md-8">
			<label>Observation:</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-id-card-o"></i>
                  </div>
                   <textarea id="obs" name="obs" placeholder="Observation..." rows="5" class="form-control">${etudiant.obs}</textarea>
                </div>

			</div>
			<div class="col-md-4">
			<label>Année:</label>
				<div class="input-group ">
					<div class="input-group-addon">
							<i class="fa fa-book"></i>
					</div>
					<select name="annee" id="annee" class="form-control custom-select" style="width: 100%;" tabindex="-1" aria-hidden="true">
						<option selected="${etudiant.annee}">${etudiant.annee}</option>
						
						<option value="2017">2017</option>
						<option value="2016">2016</option>
						<option value="2015">2015</option>
					
					</select>
				</div>	
			
	
			</div>
		</div>
          
		
	
			
 </div>
    	    
	
			<hr>

<!--			<button name="confirmer" class="btn btn-primary" type="submit" value="1">Valider</button>-->
		
			</div>
		</div>

	</div>
	
	</div>


                    
      </div>
      <div class="modal-footer">
       <button type="submit" class="btn btn-primary">Enregistrer</button>
       </form>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
       
      </div>
    </div>
  </div>
</div>



</c:forEach>
