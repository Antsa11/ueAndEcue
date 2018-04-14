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
                        </div>

                        <div aria-labelledby="closeCard1" class="dropdown-menu dropdown-menu-right has-shadow">
                            <a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a>
                            <a href="#" class="dropdown-item edit"><i class="fa fa-gear"></i>Edit</a>
                        </div>
                    </div>

                        <div class="card-header d-flex align-items-center">
                            <h3 class="h4">Liste des étudiants autorisés à s'inscrire - parcours</h3>
                        </div>
                        <div class="table">
                            <table>
                                <thead>
                                    <th>Nom Parcours</th>
                                    <th>Niveau</th>
                                    <th>Nombre d'éudiants autorisés à sinscrire</th>
                                    <th></th>
                                </thead>
                                <tbody>
                                    <c:forEach var="parcours" items="${parcours}">
                                        <tr>
                                            <td><label>${parcours.nom_parcours}</label></td>
                                            <td>${parcours.niveau}</td>
                                            <td>${parcours.n_etudiant}</td>
                                            <td>
                                                <form method="post" action="liste_etudiant_data">
                                                    <input type="hidden" name="id_parcours" value="${parcours.id_parcours}"/>
                                                    <input type="hidden" name="niveau" value="${parcours.niveau}"/>
                                                    <input type="submit" class="btn btn-primary" value="Student" />
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        <!-- </div> -->
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>