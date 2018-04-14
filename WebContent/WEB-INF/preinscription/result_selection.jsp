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
                        <div role="progressbar" style="width: ${(count_mi*100)/(count_mi+count_pc+count_svt)}%; height: 4px;" aria-valuenow="{#val.value}" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-violet"></div>
                      </div>
                    </div>
                    <div class="number"><strong id="nbmi">${count_mi}</strong><span>&nbsp;</span></div>
                    <div class="icon">
                    <c:if test = "${count_mi > 0}">
						<a href="${ base_url }/pdf_resultat_selection?choix=MI&v=${id_vague}">pdf</a>
						 </c:if>
                    </div>
                   
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center">
                    <div class="icon bg-red"><i class="icon-padnote"></i></div>
                    <div class="title"><span>PC</span>
                      <div class="progress">
                        <div role="progressbar" style="width: ${(count_pc*100)/(count_mi+count_pc+count_svt)}%; height: 4px;" aria-valuenow="{#val.value}" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div>
                      </div>
                    </div>
                    <div class="number"><strong id="nbpc">${count_pc}</strong><span>&nbsp;</span></div>
                    <div class="icon">
                    <c:if test = "${count_pc > 0}">
                    <a href="${ base_url }/pdf_resultat_selection?choix=PC&v=${id_vague}">pdf</a>
                    </c:if>
                    </div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center">
                    <div class="icon bg-green"><i class="icon-bill"></i></div>
                    <div class="title"><span>SVT</span>
                      <div class="progress">
                        <div role="progressbar" style="width: ${(count_svt*100)/(count_mi+count_pc+count_svt)}%; height: 4px;" aria-valuenow="{#val.value}" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-green"></div>
                      </div>
                    </div>
                    <div class="number"><strong id="nbsvt">${count_svt}</strong><span>&nbsp;</span></div>
                     <div class="icon">
                     <c:if test = "${count_svt > 0}">
						<a href="${ base_url }/pdf_resultat_selection?choix=SVT&v=${id_vague}">pdf</a>
                     </c:if>
                     </div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center">
                    <div class="icon bg-green"><i class="icon-bill"></i></div>
                    <div class="title"><span>Total</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 100%; height: 4px;" aria-valuenow="{#val.value}" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-green"></div>
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
                <div class="col-lg-12 col-sm-12 col-md-12">
                  <div class="card">
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard4" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a><a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a></div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">Liste des étudiants sélectionnés</h3>
                    </div>
                    <div class="card-body">
                      <form class="form-horizontal" method="post" action="resultat_search">  
                        <div class="form-group row">
                          <div class="col-lg-3">
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
                          <div class="col-lg-3">
                            <select class="custom-select" name="choix">
                              <option selected>Choix</option>
                              <option value="MI">MI</option>
                              <option value="PC">PC</option>
                              <option value="SVT">SVT</option>
                            </select>
                          </div>
                          <div class="col-lg-2">
                            <input id="search" type="text" name="search" placeholder="Chercher">
                          </div>
                          <div class="col-lg-2"></div>
                          <div class="col-lg-2">
                              <input type="submit" value="Afficher" class="btn btn-primary">
                          </div>    
                        </div>    
                      </form>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </section>

          <!-- Charts -->
          <section class="charts">
            <div class="container-fluid">
              <div class="row">
                <div class="col-lg-6">
                  <div class="pie-chart-example card">
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">Premi&egrave;re liste en MI, PC et SVT</h3>
                    </div>
                    <div class="card-body">
                      <canvas id="doughnutChart"></canvas>       
                    </div>
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard2" class="dropdown-menu dropdown-menu-right has-shadow">
                          <a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
                        </div>
                      </div>
                    </div>
                  </div>         
                </div> 
              </div>   
            </div>
          </section>
          
          
<c:forEach var="etudiant" items="${etudiants}">
<div class="modal fade bd-example-modal-lg" id="edit_obs_${etudiant.id_record}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
				<form id="form" class="form-horizontal" accept-charset="utf-8" method="post" action="modifier_obs_etudiant">
				<input type="hidden" name="id_record" value="${etudiant.id_record}"/>
		    
		
        <div class="row">
			<div class="col-md-8">
			<label>Date de naissance:</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-id-card-o"></i>
                  </div>
                   <input id="ddn" name="ddn" placeholder="......." class="form-control" type="text" value="${etudiant.ddn}"/>
                </div>
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



<!--Modal confirmation-->

<div class="modal fade bd-example-modal-md" id="confirmer_${etudiant.id_record}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-md" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="exampleModalLabel">Confirmation inscription: <br> ${etudiant.nom_prenom} <br> Choix:${etudiant.choix}</h3>
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
				<form id="form" class="form-horizontal" accept-charset="utf-8" method="post" action="confirmer_inscription">
				<input type="hidden" name="id_record" value="${etudiant.id_record}"/>
				<input type="hidden" name="choix" value="${etudiant.choix}"/>
				
		
        <div class="row">
			<div class="col-md-8">
			<label>Numéro du reçu de banque:</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-id-card-o"></i>
                  </div>
                   <input id="num_recu" name="num_recu" placeholder="......." class="form-control" type="text"/>
                </div>
				
			<label>Observation:</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-id-card-o"></i>
                  </div>
                   <textarea id="obs" name="obs" placeholder="......." class="form-control"></textarea>
                </div>

			</div>
			<div class="col-md-4">
			<label></label>
				<div class="input-group ">
					
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
        <!--<button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>-->
       
      </div>
    </div>
  </div>
</div>


</c:forEach>
<script type="text/javascript" src="jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/mdb.min.js"></script>
<script type="text/javascript">
    var statMath = parseInt(document.getElementById("nbmi").innerHTML);
    var statPC = parseInt(document.getElementById("nbpc").innerHTML);
    var statSVT = parseInt(document.getElementById("nbsvt").innerHTML);
    var totalStat = statMath+statPC+statSVT;
    var ctxD = document.getElementById("doughnutChart").getContext('2d');
    var myLineChart = new Chart(ctxD, {
        type: 'doughnut',
        data: {
            labels: ["Maths : " + statMath, "PC : " + statPC, "SVT : " + statSVT],
            datasets: [
                {
                    data : [totalStat, totalStat, totalStat],
                    backgroundColor: ["#796AEE", "#FF7676", "#54E69D"],
                    hoverBackgroundColor: ["#796AEE", "#FF7676", "#54E69D"]
                }
            ]
        },
        options: {
            responsive: true
        }    
    });

</script>
