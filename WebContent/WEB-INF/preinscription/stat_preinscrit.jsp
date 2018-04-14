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
                            <div class="number"><strong id="nbmi">${stat.mi}</strong><span>&nbsp;</span></div>
                            <div class="icon"><a href="${ base_url }/pdf_resultat_selection?choix=MI">pdf</a></div>
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
                            <div class="number"><strong id="nbpc">${stat.pc}</strong><span>&nbsp;</span></div>
                            <div class="icon"><a href="${ base_url }/pdf_resultat_selection?choix=PC">pdf</a></div>
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
                            <div class="number"><strong id="nbsvt">${stat.svt}</strong><span>&nbsp;</span></div>
                            <div class="icon"><a href="${ base_url }/pdf_resultat_selection?choix=SVT">pdf</a></div>
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
                            <div class="number"><strong id="totalStat">${stat.mi+stat.pc+stat.svt}</strong></div>
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
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-close">
                                <div class="dropdown">
                                    <button type="button" id="closeCard4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                                    <div aria-labelledby="closeCard4" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a><a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a></div>
                                </div>
                            </div>
                            <div class="card-header d-flex align-items-center">
                                <h3 class="h4">&Eacute;tudiants pr&eacute;-inscrits</h3>
                            </div>
                            <div class="card-body">
                                <form class="formsearch_preinscr-horizontal" method="post" action="search_preinscr">
                                    <div class="form-group row">    
                                        <div class="col-lg-3">
                                            <input id="search" type="text" name="search" placeholder="......">  
                                        </div>  
                                        <div class="col-lg-1"></div>  
                                        <div class="col-lg-2">
                                            <input type="submit" value="Rechercher" class="btn btn-primary">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- chart des etudiants preselectionnes -->
        <section class="charts">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="pie-chart-example card">
                            <div class="card-header d-flex align-items-center">
                                <h3 class="h4">Pr&eacute;l&eacute;ctionn&eacute;s en MI, PC et SVT</h3>
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
<script type="text/javascript" src="jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="bootstrap/js/mdb.min.js"></script>
<script type="text/javascript">
    //Chart de repartition MI-PC-SVT
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
                    data: [totalStat, totalStat, totalStat],
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