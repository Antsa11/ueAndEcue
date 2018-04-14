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
                  <h2> Résultat de la sélection en L1 MI-PC-SVT</h2>
                  
                </div>
              </div>
            </div>
            <!-- Form Panel    -->
            <div class="col-lg-6 bg-white">
              <div class="form d-flex align-items-center">
                <div class="content">
                  <form id="login-form" method="post" action="result_selection">
                    <div class="form-group">
                    <label for="choix" class="label-material">Choisir un portail</label>
                      <select class="custom-select" name="choix">
								<option value="MI">MI</option>
								<option value="PC">PC</option>
								<option value="SVT">SVT</option>
				
							</select>
							
                      
                    </div> 
                    
                    <div class="form-group">
                    <label for="choix" class="label-material">Résultat</label>
                      <select class="custom-select" name="id_vague">
								<option value="1">Première liste</option>
								<!--<option value="2">Deuxième liste</option>
								<option value="3">Troisième liste</option>-->
				
							</select>
							
                      
                    </div>
                    
                    <input type="submit" class="btn btn-primary" value="Valider" id="login"/>
                  
                  </form>
                  <!--<a href="#" class="forgot-pass">Forgot Password?</a><br><small>Do not have an account? </small><a href="register.html" class="signup">Signup</a>-->
                <c:if test="${ !empty error }">
					<h4> <font color="red">${ error }</font></h4>
				  </c:if>
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
