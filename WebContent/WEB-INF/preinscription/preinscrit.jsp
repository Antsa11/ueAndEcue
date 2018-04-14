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
			    <h3 class="h4">Préinscription</h3>
		  
                    </div>
                    <div class="card-body">
                      <table class="table">
                        <thead>
                          <tr>
                            <th>Dossier n°</th>
                            <th>Nom et Prénoms</th>
                            <th>Série</th>
                            <th>Choix</th>
                          </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="etudiant" items="${etudiants}">
							<tr>
							<td>${etudiant.n_dossier}</td>
							<td>${etudiant.nom_prenom}</td>
							<td>${etudiant.serie}</td>
							<td>${etudiant.choix}</td>
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
      <a class="page-link" href="${ base_url }/preinscription?page=1">Début</a>
    </li>
    <c:choose>
     <c:when test="${currentPage eq 1}">
     <li class="page-item disabled">
     </c:when>
      <c:otherwise>
      <li class="page-item">
      </c:otherwise>
      </c:choose>
      <a class="page-link" href="${ base_url }/preinscription?page=${currentPage-1}" aria-label="Previous">
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
                    <li class="page-item"><a class="page-link" href="${ base_url }/preinscription?page=${i}">${i}</a></li>
                        
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
      <a class="page-link" href="${ base_url }/preinscription?page=${currentPage+1}" aria-label="Next">
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
      <a class="page-link" href="${ base_url }/preinscription?page=${noOfPages}">Fin</a>
    </li>
  </ul>
</nav>
    
                    </div>
                  </div>
                </div>
                
              </div>
            </div>
          </section>
         
