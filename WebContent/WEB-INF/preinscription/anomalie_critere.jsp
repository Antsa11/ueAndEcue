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
                                <h3 class="h4">Liste des anomalies</h3>

                            </div>
                            <div class="card-body">

                                <form class="form-horizontal" method="post" action="anomalie_critere">

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
                                            <!--<select class="custom-select" name="obs">
<option selected>Observation</option>
<option value="BAC">Bac</option>
<option value="SANS">Sans dossier</option>
<option value="VIDE">Vide</option>
</select>-->
                                            <!--<input id="search" type="text" name="search" placeholder="Chercher" class="input-material">-->
                                            <input id="search" type="text" name="search" placeholder="Chercher">
                                        </div>

                                        <!-- <div class="col-sm-9 offset-sm-3">-->
                                        <div class="col-sm-3">
                                            <input type="submit" value="Valider" class="btn btn-primary">
                                        </div>

                                    </div>

                                </form>

                                <table class="table">
                                    <thead>
                                        <tr>

                                            <th>Nom et Prénoms</th>
                                            <th>Annee</th>
                                            <th>Choix</th>
                                            <th>Série saisie</th>
                                            <th>Tel</th>
                                            <th>Observation</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="etudiant" items="${etudiants}">
                                            <tr>

                                                <td>${etudiant.nom_prenom}</td>
                                                <td>${etudiant.annee}</td>
                                                <td>${etudiant.choix}</td>
                                                <td>${etudiant.serie_saisie}</td>
                                                <td>${etudiant.tel}</td>
                                                <td>${etudiant.obs}</td>
                                                <!--<td><a href="#" class="btn btn-primary">Editer</a></td>-->
                                                <td>
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modifierEtudiant_${etudiant.id_record}">
                                                        Modifier
                                                    </button>
                                                </td>
                                                <c:if test = "${status==\"Doyen\" && etudiant.isSelected==false}">
                                                    <td>
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#derogation_${etudiant.id_record}">
                                                            Dérogation
                                                        </button>
                                                    </td>
                                                </c:if>
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


        <!--Modal Edit-->

        <c:forEach var="etudiant" items="${etudiants}">


            <div class="modal fade bd-example-modal-lg" id="modifierEtudiant_${etudiant.id_record}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                                                        <label>Nom et prénons:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <input id="nom_prenom" name="nom_prenom" placeholder="Nom et Prénoms" class="form-control" type="text" value="${etudiant.nom_prenom}"/>
                                                        </div>

                                                    </div>
                                                    <div class="col-md-4">
                                                        <label>Moyenne:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <input id="moyenne" name="moyenne" placeholder="moyenne" class="form-control" type="text" value="${etudiant.moyenne}"/>
                                                        </div>


                                                    </div>

                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label>Nom:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <input id="nom" name="nom" placeholder="Nom" class="form-control" type="text" value="${etudiant.nom_prenom}"/>
                                                        </div>

                                                    </div>
                                                    <div class="col-md-6">
                                                        <label>Prénoms:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <input id="prenoms" name="prenoms" placeholder="Prénoms" class="form-control" type="text" value="${etudiant.nom_prenom}"/>
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
                                                        <label>NUM:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <input id="num_bacc" name="num_bacc" placeholder="num_bacc" class="form-control" type="text" value="${etudiant.num_bacc}"/>
                                                        </div>


                                                    </div>



                                                </div>
                                                </div>


                                            <div class="row">
                                                <div class="col-md-4">
                                                    <label>Série:</label>
                                                    <div class="input-group ">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-book"></i>
                                                        </div>
                                                        <select name="serie_saisie" id="serie_saisie" class="form-control custom-select" style="width: 100%;" tabindex="-1" aria-hidden="true">
                                                            <option selected="${etudiant.serie_saisie}">${etudiant.serie_saisie}</option>

                                                            <option value="C">C</option>
                                                            <option value="D">D</option>
                                                            <option value="S">S</option>
                                                            <option value="TI">TI</option>
                                                            <option value="TGC">TGC</option>
                                                        </select>
                                                    </div>	
                                                    <label>Choix:</label>
                                                    <div class="input-group ">
                                                        <div class="input-group-addon ">
                                                            <i class="fa fa-book"></i>
                                                        </div>
                                                        <select name="choix" id="choix" class="form-control custom-select " style="width: 100%;" tabindex="-1" aria-hidden="true">
                                                            <option selected="${etudiant.choix}">${etudiant.choix}</option>

                                                            <option value="mi">MI</option>
                                                            <option value="pc">PC</option>
                                                            <option value="svt">SVT</option>
                                                        </select>
                                                    </div>


                                                </div>


                                                <div class="col-md-4">
                                                    <label>Mathémathique:</label>

                                                    <div class="input-group ">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-id-card-o"></i>
                                                        </div>
                                                        <input id="math" name="math" placeholder="note de math" class="form-control" type="text" value="${etudiant.math}"/>
                                                    </div>
                                                    <label>Physique-Chimie:</label>

                                                    <div class="input-group ">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-id-card-o"></i>
                                                        </div>
                                                        <input id="pc" name="pc" placeholder="note de pc" class="form-control" type="text" value="${etudiant.pc}"/>
                                                    </div>


                                                </div>

                                                <div class="col-md-4">
                                                    <label>SVT:</label>

                                                    <div class="input-group">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-id-card-o"></i>
                                                        </div>
                                                        <input id="sn" name="sn" placeholder="note de svt" class="form-control" type="text" value="${etudiant.sn}"/>
                                                    </div>

                                                    <label>Centre:</label>

                                                    <div class="input-group">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-id-card-o"></i>
                                                        </div>
                                                        <input id="centre" name="centre" placeholder="Centre" class="form-control" type="text" value="${etudiant.centre}"/>
                                                    </div>

                                                </div>


                                                <button type="submit" class="btn btn-primary">Enregistrer</button>

                                            </div>


                                            <hr>

                                            <!--			<button name="confirmer" class="btn btn-primary" type="submit" value="1">Valider</button>-->

                                        </div>
                                    </div>

                                </div>

                            </div>



                        </div>
                        <div class="modal-footer">
                            <!--<button type="submit" class="btn btn-primary">Enregistrer</button>-->
                            </form>
                        <!--  <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>-->

                    </div>
                </div>
            
            </div>



        </c:forEach>

<!--Modal Derogation-->

 <c:forEach var="etudiant" items="${etudiants}">


            <div class="modal fade bd-example-modal-lg" id="derogation_${etudiant.id_record}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="exampleModalLabel">Dérogation - Etudiant</h3>
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
                                            <form id="form" class="form-horizontal" accept-charset="utf-8" method="post" action="derogation">
                                                <input type="hidden" name="id_record" value="${etudiant.id_record}"/>




                                                <div class="row">
                                                    <div class="col-md-8">
                                                        <label>Nom et prénons:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <input id="nom_prenom" name="nom_prenom" placeholder="Nom et Prénoms" class="form-control" type="text" value="${etudiant.nom_prenom}"/>
                                                        </div>

                                                    </div>
                                                    <div class="col-md-4">
                                                        <label>Moyenne:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <input id="moyenne" name="moyenne" placeholder="moyenne" class="form-control" type="text" value="${etudiant.moyenne}"/>
                                                        </div>


                                                    </div>

                                                </div>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label>Nom:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <input id="nom" name="nom" placeholder="Nom" class="form-control" type="text" value="${etudiant.nom}"/>
                                                        </div>

                                                    </div>
                                                    <div class="col-md-6">
                                                        <label>Prénoms:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <input id="prenoms" name="prenoms" placeholder="Prénoms" class="form-control" type="text" value="${etudiant.prenoms}"/>
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
                                                        <label>NUM:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <input id="num_bacc" name="num_bacc" placeholder="num_bacc" class="form-control" type="text" value="${etudiant.num_bacc}"/>
                                                        </div>


                                                    </div>



                                                </div>
                                                </div>


                                            <div class="row">
                                                <div class="col-md-4">
                                                    <label>Série:</label>
                                                    <div class="input-group ">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-book"></i>
                                                        </div>
                                                        <select name="serie_saisie" id="serie_saisie" class="form-control custom-select" style="width: 100%;" tabindex="-1" aria-hidden="true">
                                                            <option selected="${etudiant.serie_saisie}">${etudiant.serie_saisie}</option>

                                                            <option value="C">C</option>
                                                            <option value="D">D</option>
                                                            <option value="S">S</option>
                                                            <option value="TI">TI</option>
                                                            <option value="TGC">TGC</option>
                                                        </select>
                                                    </div>	
                                                    <label>Choix:</label>
                                                    <div class="input-group ">
                                                        <div class="input-group-addon ">
                                                            <i class="fa fa-book"></i>
                                                        </div>
                                                        <select name="choix" id="choix" class="form-control custom-select " style="width: 100%;" tabindex="-1" aria-hidden="true">
                                                            <option selected="${etudiant.choix}">${etudiant.choix}</option>

                                                            <option value="mi">MI</option>
                                                            <option value="pc">PC</option>
                                                            <option value="svt">SVT</option>
                                                        </select>
                                                    </div>


                                                </div>


                                                <div class="col-md-4">
                                                    <label>Mathémathique:</label>

                                                    <div class="input-group ">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-id-card-o"></i>
                                                        </div>
                                                        <input id="math" name="math" placeholder="note de math" class="form-control" type="text" value="${etudiant.math}"/>
                                                    </div>
                                                    <label>Physique-Chimie:</label>

                                                    <div class="input-group ">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-id-card-o"></i>
                                                        </div>
                                                        <input id="pc" name="pc" placeholder="note de pc" class="form-control" type="text" value="${etudiant.pc}"/>
                                                    </div>


                                                </div>

                                                <div class="col-md-4">
                                                    <label>SVT:</label>

                                                    <div class="input-group">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-id-card-o"></i>
                                                        </div>
                                                        <input id="sn" name="sn" placeholder="note de svt" class="form-control" type="text" value="${etudiant.sn}"/>
                                                    </div>

                                                    <label>Centre:</label>

                                                    <div class="input-group">
                                                        <div class="input-group-addon">
                                                            <i class="fa fa-id-card-o"></i>
                                                        </div>
                                                        <input id="centre" name="centre" placeholder="Centre" class="form-control" type="text" value="${etudiant.centre}"/>
                                                    </div>

                                                </div>


                                                <button type="submit" class="btn btn-primary">Enregistrer</button>

                                            </div>


                                            <hr>

                                            <!--			<button name="confirmer" class="btn btn-primary" type="submit" value="1">Valider</button>-->

                                        </div>
                                    </div>

                                </div>

                            </div>



                        </div>
                        <div class="modal-footer">
                            <!--<button type="submit" class="btn btn-primary">Enregistrer</button>-->
                            </form>
                        <!--  <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>-->

                    </div>
                </div>
            
            </div>



        </c:forEach>
