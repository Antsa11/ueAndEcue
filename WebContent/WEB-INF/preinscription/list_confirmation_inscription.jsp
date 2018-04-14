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
                            <div class="number"><strong>${count_mi}</strong><span>&nbsp;</span></div>
                            <div class="icon"><a href="${ base_url }/pdf_resultat_confirmation?choix=MI">pdf</a></div>
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
                            <div class="number"><strong>${count_pc}</strong><span>&nbsp;</span></div>
                            <div class="icon"><a href="${ base_url }/pdf_resultat_confirmation?choix=PC">pdf</a></div>
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
                            <div class="number"><strong>${count_svt}</strong><span>&nbsp;</span></div>
                            <div class="icon"><a href="${ base_url }/pdf_resultat_confirmation?choix=SVT">pdf</a></div>
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
                    <!-- Statistics -->
                    <!-- <div class="statistics col-lg-3 col-12">
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
</div>-->
                    <!-- Line Chart   -->
                    <div class="col-lg-12 col-sm-12 col-md-12">
                        <div class="card">
                            <div class="card-close">
                                <div class="dropdown">
                                    <button type="button" id="closeCard4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                                    <div aria-labelledby="closeCard4" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a><a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a></div>
                                </div>
                            </div>
                            <div class="card-header d-flex align-items-center">
                                <h3 class="h4">Confirmation inscription</h3>
                            </div>
                            <div class="card-body">
                                <form class="form-horizontal" method="post" action="search_confirm">

                                    <div class="form-group row">       

                                        <div class="col-sm-3">
                                            <select class="custom-select" name="annee">
                                                <option selected>Année-Universitaire</option>
                                                <option value="2017-2018">2017-2018</option>
                                                <option value="2018-2019">2018-2019</option>
                                                <option value="2019-2020">2019-2020</option>

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
                                            <th>Nom</th>
                                            <th>Prénoms</th>


                                            <th>Portail</th>

                                            <th>Obsérvation</th>
                                            <th></th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="etudiant" items="${etudiants}">
                                            <tr>

                                                <td>${etudiant.nom}</td>
                                                <td>${etudiant.prenoms}</td>
                                                <td>${etudiant.portail}</td>
                                                <td>${etudiant.obs}</td>
                                                <td>
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#details_etudiant_${etudiant.id_inscription}">
                                                        Détails
                                                    </button>
                                                </td>
                                                <td>
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#inscrire_etudiant_${etudiant.id_inscription}">
                                                        Inscrire
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


        <!--Modal Details-->

        <c:forEach var="etudiant" items="${etudiants}">


            <div class="modal fade bd-example-modal-lg" id="details_etudiant_${etudiant.id_inscription}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="ModalLabel">${etudiant.nom} ${etudiant.prenoms}<br>Portail : ${etudiant.portail}</h3>
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



                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        </div>
                    </div>
                </div>




            </div>




            <!--Modal Inscription-->

            <div class="modal fade bd-example-modal-md" id="inscrire_etudiant_${etudiant.id_inscription}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-md" role="document">
                    <div class="modal-content">
                        <form id="form" class="form-horizontal" accept-charset="utf-8" method="post" action="valider_inscription">
                            <div class="modal-header">
                                <h3 class="modal-title" id="ModalLabel">Inscription administrative de: <br> ${etudiant.nom} ${etudiant.prenoms} <br> L1:${etudiant.portail}</h3>
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
                                                <input type="hidden" name="id_record" value="${etudiant.id_inscription}"/>
                                                <input type="hidden" name="choix" value="${etudiant.portail}"/>


                                                <div class="row">
                                                    <div class="col-md-8">
                                                        <label>Numéro du reçu de banque:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <input id="num_recu" name="num_recu" placeholder="......." class="form-control" type="text" value="${etudiant.num_recu}"/>
                                                        </div>

                                                        <label>Observation:</label>

                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-id-card-o"></i>
                                                            </div>
                                                            <textarea id="obs" name="obs"  class="form-control">${etudiant.obs}</textarea>
                                                        </div>

                                                    </div>
                                                </div>




                                            </div>




                                            <!--<button name="confirmer" class="btn btn-primary" type="submit" value="1">Enregistrer</button>-->

                                        </div>
                                    </div>

                                </div>

                            </div>                
                            <div class="modal-footer">
                                <button name="confirmer" value="1" type="submit" class="btn btn-primary">Enregistrer</button>

                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>

                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>



