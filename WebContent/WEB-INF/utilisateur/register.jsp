<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Faculté des Sciences | Gestion utilisateur</title>
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
    
    <link rel="stylesheet" href="bootstrap/css/style.sciences.css" id="theme-stylesheet">
    
    <link rel="stylesheet" href="bootstrap/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="bootstrap/favicon.png">
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
                   <p>Espace réservé pour l'administration</p>
                </div>
              </div>
            </div>
            <!-- Form Panel    -->
            <div class="col-lg-6 bg-white">
              <div class="form d-flex align-items-center">
                <div class="content">
                  <form id="registerForm" method="post">
                    <div class="form-group">
                      <input id="nom" type="text" name="nom" required class="input-material">
                      <label for="nom" class="label-material">Nom</label>
                    </div>
                    <div class="form-group">
                      <input id="prenoms" type="text" name="prenoms" required class="input-material">
                      <label for="prenoms" class="label-material">Prénoms</label>
                    </div>
                    <div class="form-group">
                      <input id="username" type="text" name="username" required class="input-material">
                      <label for="username" class="label-material">Identifiant</label>
                    </div>
                    <div class="form-group">
                      <input id="password1" type="password" name="password1" required class="input-material">
                      <label for="password1" class="label-material">Mot de passe </label>
                    </div>
                    <div class="form-group">
                      <input id="password2" type="password" name="password2" required class="input-material">
                      <label for="password2" class="label-material">Retaper votre mot de passe </label>
                    </div>
                    <div class="form-group">
                    <label for="fonction" class="label-material">Fonction</label>
                      <select class="custom-select" name="fonction">
								<option value="Pat">PAT</option>
								<option value="Enseignant">Enseignant</option>
								<option value="Doyen">Doyen/Vice-Doyen</option>
				
					  </select>
							
                      
                    </div> 
                    
                    
                    <input id="register" type="button" value="Enregistrer" class="btn btn-primary">
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
     
    </div>
    <!-- Javascript files-->
    <script src="jquery/jquery-3.2.1.min.js"></script>
    <script src="bootstrap/vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="bootstrap/vendor/jquery-validation/jquery.validate.min.js"></script>
    <script src="bootstrap/js/front.js"></script>
    <script src="bootstrap/js/sciences.js"></script>
    
    
    
  </body>
</html>
