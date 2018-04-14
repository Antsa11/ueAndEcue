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
            <!-- Logo & Information Panel -->
            <div class="col-lg-6">
              <div class="info d-flex align-items-center">
                <div class="content">
                  <div class="logo">
                    <h1>Faculté des Sciences</h1>
                  </div>
                  <p>Espace réservé pour l'administration</p>
                  <c:if test="${ !empty error }">
					<p>Erreur de login</p>
				  </c:if>
                </div>
              </div>
            </div>
            <!-- Form Panel -->
            <div class="col-lg-6 bg-white">
              <div class="form d-flex align-items-center">
                <div class="content">
                  <form id="login-form" method="post" action="login">
                    <div class="form-group">
                      <input id="login-username" type="text" name="loginUsername" required="" class="input-material" autofocus="">
                      <label for="login-username" class="label-material">Identifiant</label>
                    </div>
                    <div class="form-group">
                      <input id="login-password" type="password" name="loginPassword" required="" class="input-material">
                      <label for="login-password" class="label-material">Mot de passe</label>
                    </div>
                    <!--<a id="login" href="index.html" class="btn btn-primary">Login</a>-->
                    <input type="submit" class="btn btn-primary" value="Login" id="login"/>
                    <!-- This should be submit button but I replaced it with <a> for demo purposes-->
                  </form>
                  <!--<a href="#" class="forgot-pass">Forgot Password?</a><br><small>Do not have an account? </small><a href="register.html" class="signup">Signup</a>-->
					<a href="add_user" class="forgot-pass">S'inscrire</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
     <!-- <div class="copyrights text-center">
        <p>Design by <a href="http://fac-scs.univ-antananarivo.mg" class="external">Faculté des Sciences</a></p>
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
