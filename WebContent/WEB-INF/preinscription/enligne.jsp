<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <!-- Dashboard Counts Section-->

          <!-- Dashboard Header Section    -->
          <section class="dashboard-header">
            <div class="container-fluid">
              <div class="row">
                <!-- Statistics -->
              <!--  <div class="statistics col-lg-3 col-12">
                  <div class="statistic d-flex align-items-center bg-white has-shadow">
                    <div class="icon bg-red"><i class="fa fa-tasks"></i></div>
                    <div class="text"><strong>${stat.mi}</strong><br><small>MI</small></div>
                  </div>
                  <div class="statistic d-flex align-items-center bg-white has-shadow">
                    <div class="icon bg-green"><i class="fa fa-calendar-o"></i></div>
                    <div class="text"><strong>${stat.pc}</strong><br><small>PC</small></div>
                  </div>
                  <div class="statistic d-flex align-items-center bg-white has-shadow">
                    <div class="icon bg-orange"><i class="fa fa-paper-plane-o"></i></div>
                    <div class="text"><strong>${stat.svt}</strong><br><small>SVT</small></div>
                  </div>
                </div>-->
                <!-- Line Chart            -->
                     <div class="col-lg-12">
                  <div class="card">
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard4" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a><a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a></div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">Pré-inscription en ligne</h3>
                    </div>
                    <div class="card-body">
                       <table class="table">
                        <thead>
                          <tr>
                            <th>N° Bac</th>
                            <th>Nom et Prénoms</th>
                            <th>Série</th>
                            <th>Mention</th>
                            <th>Choix</th>
                            <th></th>
                            
                          </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="etudiant" items="${etudiants}">
							<tr>
							<td>${etudiant.num_bacc}</td>
							<td>${etudiant.nom_prenom}</td>
							<td>${etudiant.serie}</td>
							<td>${etudiant.mention}</td>
							<td>${etudiant.choix}</td>
							<td>
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#updateStudent_${etudiant.id_record}">
									Edit
							</button>
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
      <a class="page-link" href="${ base_url }/preinscription?page=1">Début</a>
    </li>
    <c:choose>
     <c:when test="${currentPage eq 1}">
     <li class="page-item disabled">
     </c:when>
      <c:otherwise>
      <li class="page-item">
      </c:otherwise>
      </c:choose>
      <a class="page-link" href="${ base_url }/preinscription?page=${currentPage-1}" aria-label="Previous">
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
                    <li class="page-item"><a class="page-link" href="${ base_url }/preinscription?page=${i}">${i}</a></li>
                        
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
      <a class="page-link" href="${ base_url }/preinscription?page=${currentPage+1}" aria-label="Next">
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
      <a class="page-link" href="${ base_url }/preinscription?page=${noOfPages}">Fin</a>
    </li>
  </ul>
</nav>
-->


			<!--Modal Edit-->

<c:forEach var="etudiant" items="${etudiants}">


<div class="modal fade bd-example-modal-lg" id="updateStudent_${etudiant.id_record}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="exampleModalLabel">Mise à jour <br> ${etudiant.nom_prenom}</h3>
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
				<form id="form" class="form-horizontal" accept-charset="utf-8" method="post" action="updateOnlineStudent">
				<input type="hidden" name="id_record" value="${etudiant.id_record}"/>
				<input type="hidden" name="tel" value="${etudiant.tel}"/>
				<input type="hidden" name="choix" value="${etudiant.choix}"/>
				<input type="hidden" name="annee" value="${etudiant.annee}"/>
		    
		    
		    
		<div class="row">
			<div class="col-md-8">
			<label>Nom:</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-id-card-o"></i>
                  </div>
                  <input id="nom" name="nom" placeholder="Nom" class="form-control" type="text" value="${etudiant.nom_prenom}"/>
                </div>

			</div>
			
		</div> 
		<div class="row">
			<div class="col-md-8">
			<label>Prénoms:</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-id-card-o"></i>
                  </div>
                  <input id="prenom" name="prenom" placeholder="Prénoms" class="form-control" type="text"/>
                </div>

			</div>
			
		</div> 
		
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
			<label></label>
				<div class="input-group ">
					<!--<div class="input-group-addon">
							<i class="fa fa-book"></i>
					</div>
					<select name="annee" id="annee" class="form-control custom-select" style="width: 100%;" tabindex="-1" aria-hidden="true">
						<option selected="${etudiant.annee}">${etudiant.annee}</option>
						
						<option value="2017">2017</option>
						<option value="2016">2016</option>
						<option value="2015">2015</option>
					
					</select>-->
					<button name="confirmer" class="btn btn-primary" type="submit" value="1">Enregistrer</button>
				</div>	
			
	
			</div>
		</div>
          
		
	
			
 </div>
    	    
	
			<hr>

		<!--<button name="confirmer" class="btn btn-primary" type="submit" value="1">Enregistrer</button>-->
		
			</div>
		</div>

	</div>
	
	</div>


                    
      </div>
      <div class="modal-footer">
       <!--<button type="submit" class="btn btn-primary">Enregistrer</button>-->
       </form>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
       
      </div>
    </div>
  </div>
</div>



</c:forEach>

		




    
                    </div>
                  </div>
                </div>
           
                
              </div>
            </div>
          </section>
        
      
         
