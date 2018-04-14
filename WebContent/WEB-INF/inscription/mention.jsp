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
                      <a href="#" class="dropdown-item edit"><i class="fa fa-gear"></i>Edit</a>
                    </div>
                  </div>
                </div>
                <div class="card-header d-flex align-items-center">
                  <h3 class="h4">Liste des étudiants autorisés à s'inscrire - Mentions</h3>
                </div>
                <table class="table">
                  <thead>
                    <th>Nom Mention</th>
                    <th>Nombre d'étudiants autorisés à s'inscrire</th>
                    <th></th>
                  </thead>
                  <tboby>
                    <tr>
                        <c:forEach var="mention" items="${mention}">
                        <td><label>${mention.nom_mention}</label>
                        </td>
                        <!-- <td>${mention.nom_responsable}</td>
                        <td>${mention.tel}</td> -->
                        <td>${mention.n_etudiant}</td>
                        <td>
                            <form method="post" action="liste_parcours_data">
                                <input type="hidden" name="mention" value="${mention.id_mention}"/>
                                <input type="submit" class="btn btn-primary" value="Parcours" />
                        </form>
                        </td>
                    </tr>
                  </tbody>                        
                  </c:forEach> 
                </table>
                  </tr>
                </thead>
               </table>
            </div>
        </div>
    </div>
</div>
</section>
