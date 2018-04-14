<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!-- Dashboard Counts Section-->

        <!-- Dashboard Header Section    -->
        <section class="dashboard-header">
            <div class="container-fluid">
                <div class="row">
                    <!-- Statistics -->
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
                                <h3 class="h4">Préinscription</h3>
                            </div>
                            <div class="card-body">

                                <form class="form-horizontal" method="post" action="search_preinscr">

                                    <div class="form-group row">       


                                        <div class="col-sm-3">

                                            <input id="search" type="text" name="search" placeholder="......">  
                                        </div>    

                                        <div class="col-sm-3">
                                            <input type="submit" value="Rechercher" class="btn btn-primary">
                                        </div>

                                    </div>

                                </form>



                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Dossier n°</th>
                                            <th>Nom et Prénoms</th>
                                            <th>Série</th>
                                            <th>Choix</th>
                                            <th></th>
                                            <c:if test = "${status==\"Doyen\"}">
                                                <th></th>
                                            </c:if>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="etudiant" items="${etudiants}">
                                            <tr>
                                                <td>${etudiant.n_dossier}</td>
                                                <td>${etudiant.nom_prenom}</td>
                                                <td>${etudiant.serie}</td>
                                                <td>${etudiant.choix}</td>

                                                <td>
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#details_${etudiant.id_record}">
                                                        Détails
                                                    </button>
                                                </td>

                                                <c:if test = "${status==\"Doyen\" && etudiant.isSelected==false}">
                                                    <td>
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#derogation_${etudiant.id_record}">
                                                            Dérogation
                                                        </button>
                                                    </td>
                                                </c:if>
												<td>
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#orienter_${etudiant.id_record}">
                                                        Orienter
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
            </div>
        </section>




        <c:forEach var="etudiant" items="${etudiants}">
            <!--Modal Details-->
            <div class="modal fade bd-example-modal-md" id="details_${etudiant.id_record}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="exampleModalLabel">${etudiant.nom_prenom}<br>Choix : ${etudiant.choix}</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="row">
                                <div class="col-md-4">
                                    Père : ${etudiant.nom_pere}
                                </div>
                                <div class="col-md-4">
                                    Mère : ${etudiant.nom_mere}
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
                                <div class="col-md-3">
                                    Math : ${etudiant.math}
                                </div>
                                <div class="col-md-3">
                                    Pc : ${etudiant.pc}
                                </div>
                                <div class="col-md-3">
                                    Svt : ${etudiant.sn}
                                </div>
                                <div class="col-md-3">
                                    Moyenne : ${etudiant.moyenne}
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    Bac : ${etudiant.annee}
                                </div>
                                <div class="col-md-3">
                                    Série : ${etudiant.serie}
                                </div>
                                <div class="col-md-3">
                                    Mention : ${etudiant.mention}
                                </div>
                                <div class="col-md-3">
                                    Sélection : <b>${etudiant.isSelected}</b>
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
        
        
        
        <!--Modal Derogation-->

        <c:forEach var="etudiant" items="${etudiants}">


            <div class="modal fade bd-example-modal-md" id="derogation_${etudiant.id_record}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">

                <div class="modal-dialog modal-lg" role="document">


                    <div class="modal-content">
                        <form id="form" class="form-horizontal" accept-charset="utf-8" method="post" action="derogation">
                            <div class="modal-header">
                                <h3 class="modal-title" id="exampleModalLabel">${etudiant.nom_prenom}<br>Choix : ${etudiant.choix}</h3>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">

                                <input type="hidden" name="id_record" value="${etudiant.id_record}"/>
                                <input type="hidden" name="annee" value="${etudiant.annee}"/>

                                <div class="row">
                                    <div class="col-md-4">
                                        Père : ${etudiant.nom_pere}
                                    </div>
                                    <div class="col-md-4">
                                        Mère : ${etudiant.nom_mere}
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
                                    <div class="col-md-3">
                                        Math : ${etudiant.math}
                                    </div>
                                    <div class="col-md-3">
                                        Pc : ${etudiant.pc}
                                    </div>
                                    <div class="col-md-3">
                                        Svt : ${etudiant.sn}
                                    </div>
                                    <div class="col-md-3">
                                        Moyenne : ${etudiant.moyenne}
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-3">
                                        Bac : ${etudiant.annee}
                                    </div>
                                    <div class="col-md-3">
                                        Série : ${etudiant.serie}
                                    </div>
                                    <div class="col-md-3">
                                        Mention : ${etudiant.mention}
                                    </div>
                                    <div class="col-md-3">
                                        Sélection : <b>${etudiant.isSelected}</b>
                                    </div>

                                </div>

                                <div class="row">
                                    <div class="col-md-8">
                                        <label>Obsérvation:</label>

                                        <div class="input-group">
                                            <div class="input-group-addon">
                                                <i class="fa fa-id-card-o"></i>
                                            </div>
                                            <textarea name="obs" placeholder="....." rows="5" class="form-control"></textarea>
                                        </div>

                                    </div>
                                    <div class="col-md-2">
                                        <label>Liste:</label>

                                        <div class="input-group">
                                            <div class="input-group-addon">
                                                <i class="fa fa-id-card-o"></i>
                                            </div>
                                            <input name="vague" placeholder="...." class="form-control" type="text" value="2"/>
                                        </div>

                                    </div>


                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Enregistrer</button>

                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>

            </div>



        </c:forEach>



        <c:forEach var="etudiant" items="${etudiants}">
            <!--Modal Orientation-->
            <div class="modal fade bd-example-modal-md" id="orienter_${etudiant.id_record}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                    <form method="post" action="orienter_etudiant">
						<input type="hidden" name="id_record" value="${etudiant.id_record}">
                    
                        <div class="modal-header">
                            <h3 class="modal-title" id="exampleModalLabel">${etudiant.nom_prenom}<br>Choix : ${etudiant.choix}</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="row">
                                <div class="col-md-4">
                                    Père : ${etudiant.nom_pere}
                                </div>
                                <div class="col-md-4">
                                    Mère : ${etudiant.nom_mere}
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
                                <div class="col-md-3">
                                    Math : ${etudiant.math}
                                </div>
                                <div class="col-md-3">
                                    Pc : ${etudiant.pc}
                                </div>
                                <div class="col-md-3">
                                    Svt : ${etudiant.sn}
                                </div>
                                <div class="col-md-3">
                                    Moyenne : ${etudiant.moyenne}
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    Bac : ${etudiant.annee}
                                </div>
                                <div class="col-md-3">
                                    Série : ${etudiant.serie}
                                </div>
                                <div class="col-md-3">
                                    Mention : ${etudiant.mention}
                                </div>
                                <div class="col-md-3">
                                    Sélection : <b>${etudiant.isSelected}</b>
                                </div>

                            </div>
                            
                            <div class="row">
								<div class="col-md-4">

                                                 <label>Orienter vers:</label>
                                                    <div class="input-group ">
                                                        <div class="input-group-addon ">
                                                            <i class="fa fa-book"></i>
                                                        </div>
                                                        <select name="choix" id="choix" class="form-control custom-select " style="width: 100%;" tabindex="-1" aria-hidden="true">
															<option value="MI">MI</option>
                                                            <option value="PC">PC</option>
                                                            <option value="SVT">SVT</option>
                                                           
                                                        </select>
                                                    </div>
								</div>
                            </div>

                        </div>
                        <div class="modal-footer">
							<button type="submit" class="btn btn-primary">Enregistrer</button>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>




        </c:forEach>
