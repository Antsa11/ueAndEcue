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
								<a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a>
							</div>
						</div>
					</div>
					<div class="card-header d-flex align-items-center">
						<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ajoutCritere">Ajouter</button>
						<form method="post" action="reinit_selection">
							<button type="submit" class="btn btn-primary" style="margin-left:15px;">Réinitialiser</button>
							<input type="hidden" name="id_vague" value="${id_vague}"/>
						</form>
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
									<button type="button" class="btn btn-primary modaly" data-toggle="modal" data-target="#modifierCritere_${critere.id_critere}" id="modaly_${critere.id_critere}">Modifier</button>
								</td>
								<td>
									<form method="post" action="resultat_selection">
										<input type="hidden" name="id_criterePdf" value="${critere.id_critere}"/>
										<input type="submit" class="btn btn-warning" value="PDF"/>
									</form>
								</td>
								<td>
									<form method="post" action="reorientation">
									<input type="hidden" name="id_critere" value="${critere.id_critere}"/>
									<input type="hidden" name="id_vague" value="${id_vague}"/>
									<input type="submit" class="btn btn-success" value="Réorienter"/>
								</td>
								<td>
			 					</td>
								<td>
									<form method="post" action="delete_selection" id="deleteCri">
										<input type="hidden" name="id_critere" value="${critere.id_critere}" id="id_critere" />
										<input type="submit" class="btn btn-danger" value="Effacer" id="supprimer" />
									</form>
								</td>
								<td>
									<c:if test="${critere.isPublished==false}">
										<form method="post" action="valid_selection_from_critere">
											<input type="hidden" name="id_critere" value="${critere.id_critere}"/>
											<input type="hidden" name="id_vague" value="${id_vague}"/>
											<input type="submit" class="btn btn-success" value="Valider"/>
										</form>
									</c:if>
									<c:if test="${critere.isPublished==true}">
										<span class="btn btn-warning">Publié</span>
									</c:if>
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
											<textarea id="description" name="descriptionAdd" placeholder="description" rows="4" class="form-control"></textarea>
										</div>
		 						</div>

								<div class="form-group">
						<label>Critère:</label>
						<div class="row">
						 <div class="col-md-4">
							 <div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-book"></i>
									</div>

									<select name="portailAdd" id="portailAdd" class="form-control custom-select portail" style="width: 100%;" tabindex="-1" aria-hidden="true">
										<option selected="${critere.portail}">${critere.portail}</option>

										<option value="mi">MI</option>
										<option value="pc">PC</option>
										<option value="svt">SVT</option>
									</select>
								</div>
							 </div>
							 <div class="col-md-4">
								 <div class="input-group">
										<div class="input-group-addon">
											<i class="fa fa-book"></i>
										</div>
										<select name="id_vagueAdd" id="id_vagueAdd" class="form-control custom-select vague" style="width: 100%;" tabindex="-1" aria-hidden="true">
										<option value="0">Vague</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
									</select>
								</div>
							</div>
							<div class="col-md-2 ml-auto">
								<select name="opera1Add" id="opera1Add" class="class_opera1">
									<option value="AND">Et</option>
									<option value="OR">Ou</option>
								</select>
							</div>
						</div>
						<div class="row" style="margin-top:2%;">
					 	<div class="col-md-5">
							<div class="input-group">
								<div class="input-group-addon">
									<i class="fa fa-book"></i>
								</div>
										<select name="serieAdd" class="form-control custom-select class_serie" id="id_serieAdd">
											<option>Série</option>
											<option value="C">Série C</option>
											<option value="S">Série S</option>

										</select>
						</div>
						 </div>
						 <div class="col-md-3">
							 <select name="autreSerieAdd" id="autreSerieAdd" style="display:none;" class="class_autreSerie">
								 <option value="1">Autre série</option>
								 <option value="D">Série D</option>
							 </select>

						 </div>
						 <div class="col-md-2 ml-auto">
							 <select name="opera2Add" id="opera2Add" class="class_opera2">
								 <option value="AND">Et</option>
						<option value="OR">Ou</option>
							 </select>
						 </div>

					 </div>

			<div class="row" style="margin-top:3%;">
				<div class="col-md-9">
					<div class="form-group">
										<label>Critères :</label>
										<div class="input-group">

											<div class="input-group-addon">
													<i class="fa fa-book"></i>
											</div>

											<label>Année d'obtention du BACC</label>
											<select name="anne_baccAdd" id="anne_baccAdd" class="form-control custom-select anneBac" style="margin-left:4%;">
												<option value="2017">2017</option>
												<option value="2016">2016</option>
											</select>
										</div>

							 </div>

				</div>
				<div class="col-md-2 ml-auto">
					<select name="opera3Add" style="margin-top:2%;" id="opera3Add" class="class_opera3">
								 <option value="AND">Et</option>
						<option value="OR">Ou</option>
							 </select>
				</div>

					 </div>

					 <div class="row" style="margin-top:2%;">
						 <div class="col-md-10">
							 <div class="input-group">
								 <div class="input-group-addon">
									 <i class="fa fa-book"></i>
								 </div>
								 <label>Mention au BACC</label>
								 <select name="mentionAdd" class="form-control custom-select class_mention" id="mentionAdd">
									 <option value="TRÈS BIEN">Très bien</option>
									 <option value="BIEN">Bien</option>
									 <option value="ASSEZ BIEN">Assez bien</option>
									 <option value="PASSABLE">Passable</option>
								 </select>
							 </div>
						</div>
						<div class="col-md-2 ml-auto">
							<select name="opera4Add" style="margin-top:2%;" id="opera4Add" class="class_opera4">
									 <option value="AND">Et</option>
							<option value="OR">Ou</option>
								 </select>
						</div>
					</div>

					 <div class="row" style="margin-top:4%;">
						 <div class="col-md-7">
							 <div class="input-group">
								 <div class="input-group-addon">
												<i class="fa fa-id-card-o"></i>
										</div>
								 <label>Note de maths</label>
								 <input type="input" name="note_mathAdd" class="form-control noteMath" style="margin-left:4%;" id="id_noteMathAdd_${critere.id_critere}">
							 </div>
						 </div>

						 <div class="col-md-5">
							 <div class="input-group">
								 <label for="text">Note PC</label>
								 <input type="input" name="note_pcAdd" class="form-control notePc" style="margin-left:4%;" id="id_notePcAdd_${critere.id_critere}">
							 </div>

						 </div>
					 </div>

					 <div class="row" style="margin-top:3%;">

					<div class="col-md-6">
						<div class="input-group">
							<div class="input-group-addon">
													<i class="fa fa-id-card-o"></i>
											</div>
							<label for="text">Note SVT</label>
							<input type="input" name="note_svtAdd" class="form-control noteSvt" style="margin-left:4%;" id="id_noteSvt_${critere.id_critere}">
						</div>
					</div>

					<div class="col-md-4">
								 <div class="form-check">
							 <label class="form-check-label">
							<input class="form-check-input selection" type="checkbox" name="isSelected" id="chekboxAdd" value="false">IsSelected
							</label>
						</div>
					</div>

					<div class="col-md-2 ml-auto">
						<select name="opera5Add" id="opear5Add" class="class_opera5">
							<option value="AND">Et</option>
							<option value="OR">Ou</option>
						</select>
					</div>
</div>
		 </div>

	<hr>

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


<!--Modal Edit-->

<c:forEach var="critere" items="${criteres}">

<div class="modal fade" id="modifierCritere_${critere.id_critere}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="width:100%;height:100%;">
<div class="modal-dialog" role="document" >
<div class="modal-content">
	<div class="modal-header">
		<h5 class="modal-title" id="exampleModalLabel">Modification critère</h5>
		<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	</div>
		<div class="row"><div class="reponse" style="float:right;background-color:#67C0F1;" id="response_${critere.id_critere}"></div>


</div>
	<div class="modal-body">
		<div class="row">
<div class="col-md-12">
<div class="box box-danger">
				<div class="box-header">
					<h3 class="box-title" id=""></h3>
				</div>
				<div class="box-body">
		<form id="form" class="form-horizontal" accept-charset="utf-8" method="post" action="modifier_critere">
		<input type="hidden" name="id_critere" value="${critere.id_critere}"/>
	<div class="form-group">
						<label>Description:</label>

						<div class="input-group">
							<div class="input-group-addon">
								<i class="fa fa-id-card-o"></i>
							</div>
							<textarea id="description" name="description" placeholder="description" rows="4" class="form-control">${critere.description}</textarea>
						</div>
						<!-- /.input group!-->
		 </div>




<!-- MODIFICATION DU LUNDI !-->
		 <div class="row">
			 <div class="col-md-4">
				 <div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-book"></i>
									</div>

					<select name="portail" id="portail" class="form-control custom-select portail" style="width: 100%;" tabindex="-1" aria-hidden="true">
						<option selected="${critere.portail}">${critere.portail}</option>

						<option value="mi">MI</option>
						<option value="pc">PC</option>
						<option value="svt">SVT</option>
					</select>
			</div>
				 </div>
		<div class="col-md-4">
			<div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-book"></i>
									</div>
					<select name="id_vague" id="id_vague" class="form-control custom-select vague" style="width: 100%;" tabindex="-1" aria-hidden="true">

						<option>Vague</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>

					</select>
				</div>

		</div>
		<div class="col-md-2 ml-auto">
			<select name="opera1" id="opera1" class="class_opera1">
						 <option value="AND">Et</option>
				<option value="OR">Ou</option>
					 </select>
		</div>


</div>

<div class="row" style="margin-top:2%;">
			 <div class="col-md-5">
				 <div class="input-group">
						 <div class="input-group-addon">
											<i class="fa fa-book"></i>
							</div>

							<select name="serie" class="form-control custom-select class_serie" id="id_serie">
								<option>Série</option>
								<option value="C">Série C</option>
								<option value="S">Série S</option>

							</select>
			</div>
			 </div>
			 <div class="col-md-3">
				 <select name="autreSerie" id="autreSerie" style="display:none;" class="class_autreSerie">
					 <option value="1">Autre série</option>
					 <option value="D">Série D</option>
				 </select>

			 </div>
			 <div class="col-md-2 ml-auto">
				 <select name="opera2" id="opera2" class="class_opera2">
					 <option value="AND">Et</option>
			<option value="OR">Ou</option>
				 </select>
			 </div>

		 </div>

<div class="row" style="margin-top:3%;">
	<div class="col-md-9">
		<div class="form-group">
							<label>Critères :</label>
							<div class="input-group">

								<div class="input-group-addon">
										<i class="fa fa-book"></i>
								</div>

								<label>Année d'obtention du BACC</label>
								<select name="anne_bacc" id="anne_bacc" class="form-control custom-select anneBac" style="margin-left:4%;">
									<option value="2017">2017</option>
									<option value="2016">2016</option>
								</select>
							</div>

				 </div>

	</div>
	<div class="col-md-2 ml-auto">
		<select name="opera3" style="margin-top:2%;" id="opera3" class="class_opera3">
					 <option value="AND">Et</option>
			<option value="OR">Ou</option>
				 </select>
	</div>

		 </div>

		 <div class="row" style="margin-top:2%;">
			 <div class="col-md-10">
				 <div class="input-group">
					 <div class="input-group-addon">
						 <i class="fa fa-book"></i>
					 </div>
					 <label>Mention au BACC</label>
					 <select name="mention" class="form-control custom-select class_mention" id="mention">
						 <option value="TRÈS BIEN">Très bien</option>
						 <option value="BIEN">Bien</option>
						 <option value="ASSEZ BIEN">Assez bien</option>
						 <option value="PASSABLE">Passable</option>
					 </select>
				 </div>
		</div>
		<div class="col-md-2 ml-auto">
			<select name="opera4" style="margin-top:2%;" id="opera4" class="class_opera4">
					 <option value="AND">Et</option>
			<option value="OR">Ou</option>
				 </select>
		</div>

		 </div>

		 <div class="row" style="margin-top:4%;">
			 <div class="col-md-7">
				 <div class="input-group">
					 <div class="input-group-addon">
									<i class="fa fa-id-card-o"></i>
							</div>
					 <label>Note de maths</label>
					 <input type="input" name="note_math" class="form-control noteMath" style="margin-left:4%;" id="id_noteMath_${critere.id_critere}">
				 </div>
			 </div>

			 <div class="col-md-5">
				 <div class="input-group">
					 <label for="text">Note PC</label>
					 <input type="input" name="note_pc" class="form-control notePc" style="margin-left:4%;" id="id_notePc_${critere.id_critere}">
				 </div>

			 </div>
		 </div>

		 <div class="row" style="margin-top:3%;">

		<div class="col-md-6">
			<div class="input-group">
				<div class="input-group-addon">
										<i class="fa fa-id-card-o"></i>
								</div>
				<label for="text">Note SVT</label>
				<input type="input" name="note_svt" class="form-control noteSvt" style="margin-left:4%;" id="id_noteSvt_${critere.id_critere}">
			</div>
		</div>

		<div class="col-md-4">
					 <div class="form-check">
				 <label class="form-check-label">
				<input class="form-check-input selection" type="checkbox" name="isSelected" id="chekbox" value="false">IsSelected
				</label>
			</div>
		</div>

		<div class="col-md-2 ml-auto">
			<select name="opera5" id="opear5" class="class_opera5">
				<option value="AND">Et</option>
				<option value="OR">Ou</option>
			</select>
		</div>
</div>

<div class="row" style="margin-top:3%;">
			 <div class="col-md-7">
		<label for="text">Moyenne</label>
		<select name="moy" id="moyenne_${critere.id_critere}" class="moyen">
			<option value="0">Moyennes</option>
			 <option value="1" class="mapc" id="mp_${critere.id_critere}">Maths et PC</option>
			 <option value="2" class="masn" id="ms_${critere.id_critere}">Maths et SVT</option>
			 <option value="3" class="pcsn" id="ps_${critere.id_critere}">PC et SVT</option>
			 <option value="4" class="tout" id="all_${critere.id_critere}">MI ,PC et SVT</option>
		</select>
	</div>
	<div class="col-md-4">
		<button class="btn btn-primary">Moyenne</button>
		<input type="hidden" name="momapc" class="numcri" value="${critere.id_critere}" id="numero">
	</div>

		 </div>

<!-- MODIFICATION DU LUNDI !-->
	<hr>

<!--			<button name="confirmer" class="btn btn-primary" type="submit" value="1">Valider</button>-->

<!-- 	</div> -->
</div>

</div>

</div>



	</div>
	<div class="modal-footer">
	 <button type="submit" class="btn btn-primary">Enregistrer</button>
<!-- 	 </form>
 -->		<button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>

	</div>
</div>
</div>
</div>

</c:forEach>
<script type="text/javascript" src="jquery/jquery-3.2.1.js"></script>
<%-- <script type="text/javascript" src="jquery/scriptsa.js"></script> --%>
<script type="text/javascript">
$(document).ready(function(){
	$('.class_serie').change(function(){
		if(confirm("est-ce que vous voulez ajouter d'autre serie")){
			$('.class_autreSerie').css("display","block");
			$('.class_autreSerie').change(function(){
				$('.class_autreSerie').css("display","none");
			});
		}else{
			$('.class_autreSerie').css("display","none");
		}
	});
});

alert ("o");
var numcri = document.getElementsByClassName('numcri');
// var modaly = document.getElementsByClassName('modaly');
// // var indice = [];
// for (var i = 0;i< numcri.length; i++) {
//
// 	var indice[i] = numcri[i].value;
// 	//alert(indice[i]);
// 	modaly[i].addEventListener('click',function(){
//
// 		var notema = "id_noteMath_"+indice[i];
// 		var notepc = "id_notePc_"+indice[i];
// 		var notesn = "id_noteSvt_"+indice[i];
// 		var notemoyen = "moyenne_"+indice[i];
// 		var valiny = "response_"+indice[i];
// 		var mapc = "mp_"+indice[i];
// 		var masn = "ms_"+indice[i];
// 		var ps = "ps_"+indice[i];
// 		var noteMath = document.getElementsByClassName('note_math');
//
// 		var mat = document.getElementById(notema);
// 		var phy = document.getElementById(notepc);
// 		var sn = document.getElementById(notesn);
// 		var moyen = document.getElementById(notemoyen);
// 		var answer = document.getElementById(valiny);
// 		var choixmapc = document.getElementById(mapc);
// 		var choixmasn = document.getElementById(masn);
// 		var choixps = document.getElementById(ps);
// 		alert( $("#moyenne_"+indice[i]));
// 	});


// 
// }

</script>
