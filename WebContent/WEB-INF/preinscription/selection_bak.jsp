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
                        <button type="button" id="closeCard1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard1" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a><a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a></div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">
			   
			                       <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ajoutCritere">
									Ajouter
									</button>
		  
                    </div>

                    <div class="card-body">
                      <table class="table">
                        <thead>
                          <tr>
                            <th>Portail</th>
                            <th>Description</th>
                            <th>Nombre</th>
                            <th></th>
                            <th></th>
                             <th></th>
                             <th></th>
                          </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="critere" items="${criteres}">
							<tr>
							
							<td>${critere.portail}</td>
							<td>${critere.description}</td>
							<td>${critere.countRecords}</td>
							<td>
							<!--<a href="#" class="btn btn-primary">Voir</a>-->
							<form method="post" action="resultat_critere">
								<input type="hidden" name="id_critere" value="${critere.id_critere}"/>
								<input type="submit" class="btn btn-primary" value="Consulter"/>
							</form>
							<td>
							<form method="post" action="resultat_selection">
								<input type="hidden" name="id_critere" value="${critere.id_critere}"/>
								<input type="submit" class="btn btn-success" value="PDF"/>
							</form>
							</td>
							<td>
							<form method="post" action="excel_selection">
								<input type="hidden" name="id_critere" value="${critere.id_critere}"/>
								<input type="submit" class="btn btn-warning" value="Excel"/>
							</form>
							</td>
							<td>
							<form method="post" action="delete_selection">
								<input type="hidden" name="id_critere" value="${critere.id_critere}"/>
								<input type="submit" class="btn btn-danger" value="Delete"/>
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
         
      <!--Modal-->
         <div class="modal fade" id="ajoutCritere" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Ajout d'un critère</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="row">
	<div class="col-md-12">
		<div class="box box-danger">
            <div class="box-header">
              <h3 class="box-title">Nouveau critère</h3>
            </div>
            <div class="box-body">
				<form id="form" class="form-horizontal" accept-charset="utf-8" method="post" action="ajout_critere">
		 <div class="form-group">
                <label>Description:</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-id-card-o"></i>
                  </div>
                  <textarea id="description" name="description" placeholder="description" class="form-control"></textarea>
                </div>
                <!-- /.input group -->
         </div>
         
          <div class="form-group">
                <label>Critère:</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-id-card-o"></i>
                  </div>
                  <textarea id="citere" name="critere" placeholder="Critère" rows="5" class="form-control"></textarea>
                </div>
                <!-- /.input group -->
         </div>
		 

		 <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-book"></i>
                  </div>
					<select name="portail" id="portail" class="form-control custom-select" style="width: 100%;" tabindex="-1" aria-hidden="true">
						<option selected="Portail">Portail</option>
						
						<option value="mi">MI</option>
						<option value="pc">PC</option>
						<option value="svt">SVT</option>
					</select>
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
 <!--Modal-->
