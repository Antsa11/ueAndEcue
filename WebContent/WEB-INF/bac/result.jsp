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
                                <h3 class="h4">Liste des bacheliers</h3>
                            </div>

                            <div class="card-body">    
                                <form class="form-horizontal" method="post" action="bac_search">
                                    <div class="form-group row">       
                                        <div class="col-sm-2">
                                            <div class="col-sm-3">
                                                <input id="search" type="text" name="search" placeholder="Chercher">  <input type="submit" value="Rechercher" class="btn btn-primary">
                                            </div>    
                                            <div class="col-sm-3">
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Num</th>
                                            <th>Nom et Prénoms</th>
                                            <th>Série</th>
                                            <th>Mention</th>
                                            <th>Année</th>
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
                                                <td>${etudiant.annee}</td>
                                                <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#details_${etudiant.id_candidat}">
                                                    Détails
                                                    </button></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

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
                                        <a class="page-link" href="${ base_url }/result?page=1">Début</a>
                                        </li>
                                    <c:choose>
                                        <c:when test="${currentPage eq 1}">
                                            <li class="page-item disabled">
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item">
                                        </c:otherwise>
                                    </c:choose>
                                    <a class="page-link" href="${ base_url }/result?page=${currentPage-1}" aria-label="Previous">
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
                                            <li class="page-item"><a class="page-link" href="${ base_url }/result?page=${i}">${i}</a></li>

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
                                <a class="page-link" href="${ base_url }/result?page=${currentPage+1}" aria-label="Next">
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
                            <a class="page-link" href="${ base_url }/result?page=${noOfPages}">Fin</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
</section>

<!--Modal Details-->
<c:forEach var="etudiant" items="${etudiants}">
    <div class="modal fade bd-example-modal-md" id="details_${etudiant.id_candidat}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title" id="exampleModalLabel">${etudiant.nom_prenom} </h3>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
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
                            Centre : <b>${etudiant.centre}</b>
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
