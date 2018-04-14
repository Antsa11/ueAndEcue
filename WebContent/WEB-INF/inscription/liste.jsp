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
            <h3 class="h4">Etudiants autorisés à s'inscrire</h3>
          </div>
            <table class="table">
              <thead>
                  <tr>
                      <th>Nom</th>
                      <th>Prenom</th>
                    <!--   <th>Date de naissance</th> -->
                      <th>Mention</th>
                     <!--  <th>Parcours</th> -->
                      <th>Grade</th>
                      <th>Sexe</th>
                      <th>Niveau</th>
                  </tr>
              </thead>
              <tbody>
                <c:forEach var="student" items="${student}">
                  <tr>
                    <td><label>${student.nom_etu}</label></td>
                    <td><label>${student.prenom_etu}</label></td>
                   <!--  <td><label>${student.ddn}</label></td> -->
                    <td><label>${student.mention}</label></td>
                   <!--  <td><label>${student.parcours}</label></td> -->
                    <td><label>${student.grade}</label></td>
                    <td><label>${student.sexe}</label></td>
                    <td><label>${student.niveau}</label></td>
                  </tr>
                </c:forEach>
                <%-- <tr>
                  <td>a</td>
                  <td>b</td>
                  <td>c</td>
                </tr> --%>
              </tbody>
            </table>
        </div>
      </div>
    </div>
  </div>
</section>
