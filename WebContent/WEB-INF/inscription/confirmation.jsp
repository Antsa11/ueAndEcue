<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Faculté des sciences | Authentification</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="bootstrap/vendor/bootstrap/css/bootstrap.min.css">
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="bootstrap/css/fontastic.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="bootstrap/vendor/font-awesome/css/font-awesome.min.css">
    <!-- Google fonts - Poppins -->
    <!--<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">-->
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="bootstrap/css/style.sciences.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="bootstrap/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="bootstrap/favicon.png" type="image/x-icon">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
  </head>
  <body>
    <div class="page login-page">
      <div class="container d-flex align-items-center">
        <div class="form-holder has-shadow">
          <div class="row">
            <!-- Logo & Information Panel-->
            <div class="col-lg-6">
              <div class="info d-flex align-items-center">
                <div class="content">
                  <div class="logo">
                    <h1>Faculté des Sciences</h1>
                  </div>
                  <p> Espace dédié à la pré-inscription en ligne pour les portails MI-PC-SVT</p>
                  <c:if test="${ !empty error }">
					<p> <font color="red">${ error }</font></p>
				  </c:if>
                </div>
              </div>
            </div>
            <!-- Form Panel    -->
            <div class="col-lg-6 bg-white">
              <div class="form d-flex align-items-center">
                <div class="content">
					
                  <form id="login-form" method="post" action="validate_inscription">
                  <input type="hidden" name="num_bacc" value="${etudiant.num_bacc}"/>
                  <input type="hidden" name="annee" value="${etudiant.annee}"/>
                  <input type="hidden" name="nom_prenom" value="${etudiant.nom_prenom}"/>
                    <div class="form-group">
                    <label for="nom" class="label-material">NOM: </label>
                    <h5>${etudiant.nom_prenom}</h5>
							
                      
                    </div> 
                    <div class="form-group">
                      <input id="tel" type="text" name="tel" required class="input-material">
                      <label for="tel" class="label-material">Téléphone</label>
                    </div>
                    <div class="form-group">
                    <label for="choix" class="label-material">Choisir un portail</label>
                      <select class="custom-select" name="choix">
								<option selected="">Choix</option>
								<option value="MI">MI</option>
								<option value="PC">PC</option>
								<option value="SVT">SVT</option>
				
							</select>
							
                      
                    </div> 
                    
                     
                    
                    
                    <input type="submit" class="btn btn-primary" value="Confirmer" id="login"/>
                  
                  </form>
                 
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
     <!-- <div class="copyrights text-center">
        <p>Design by <a href="http://fac-sce.univ-antananarivo.mg" class="external">Faculté des Sciences</a></p>
      </div>
	-->	
    </div>
    <!-- Javascript files-->
    <script src="jquery/jquery-3.2.1.min.js"></script>
    <script src="bootstrap/vendor/popper.js/umd/popper.min.js"> </script>
    <script src="bootstrap/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="bootstrap/vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="bootstrap/vendor/jquery-validation/jquery.validate.min.js"></script>
    <script src="bootstrap/js/front.js"></script>
  </body>
</html>
