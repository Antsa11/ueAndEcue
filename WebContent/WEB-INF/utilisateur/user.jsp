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
                      <h3 class="h4">Utilisateurs</h3>
                    </div>
                    <div class="card-body">
                       <table class="table">
                        <thead>
                          <tr>
                            <th>Nom</th>
                            <th>Prénoms</th>
                            <th>Fonction</th>
                            <th></th>
                            <th></th>
                            
                          </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="personnel" items="${personnels}">
							<tr>
							<td>${personnel.nom}</td>
							<td>${personnel.prenoms}</td>
							<td>${personnel.fonction}</td>
							<td>
							<input id="activeUser_${personnel.id_utilisateur}" data-toggle="toggle" type="checkbox">
								
							</td>
							<td>
							<form method="post" action="delete_user">
							    <input value="${personnel.id_utilisateur}" name="id_utilisateur" type="hidden">
								<input type="submit" class="btn btn-danger" value="Supprimer"/>
										
								
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
            </div>
          </section>
        
<script>
<c:forEach var="personnel" items="${personnels}">
  $(function() {
    $('#activeUser_${personnel.id_utilisateur}').change(function() {
       alert($(this).prop('checked'))
    })
  })
 </c:forEach>
 
</script>
