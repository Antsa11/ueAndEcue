<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="tables">
            <div class="container-fluid">
              <div class="row">
                <div class="col-lg-12">

  <c:if test="${ !empty fichier }"><p><c:out value="Le fichier ${ fichier }  a été uploadé !" /></p></c:if>
  <c:if test="${ !empty message }"><p><c:out value="Message: ${ message } " /></p></c:if>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-6">
           <h1 style="font-size:30px;">Bacc</h4>
            <form method="post" action="import" enctype="multipart/form-data">
                <div class="form-group row">
                    <label for="inputFile" class="col-sm-2 col-form-label">Parcourir un fichier:</label>
                        <input type="file" id="file" name="fichier">

                    </label>
                </div>
                <div class="form-group row">
                    <label for="inputSelect" class="col-sm-2 col-form-label">Importation de données</label>
                    <select class="custom-select" name="type_import" style="margin-left:5%;">
                        <option selected>Choisir le type de données</option>
                        <option value="1">Résultats BACC</option>
                        <option value="2">Données de préinscription</option>
                    </select>
                </div>

                <div class="form-group row">
                        <label for="inputSelect" class="col-sm-2 col-form-label">Année</label>
                        <select class="custom-select" name="annee">
                            <option value="2017">2017</option>
                            <option value="2016">2016</option>
                            <option value="2015">2015</option>
                    </select>
                </div>

                <div class="form-group row">
                   <label for="inputSubmit" class="col-sm-2 col-form-label"></label>
                   <input type="submit" value="Envoyer" class="btn btn-primary"/>
                </div>
            </form>
        </div>

<!-- Pour la réinscription des étudiants qui ont passé ou redoublé !-->

         <div class="col-md-6">
            <h1 style="font-size:30px;">Inscription des étudiants</h4>
            <form method="post" action="loadInscriptionFromFile" enctype="multipart/form-data">
                <div class="form-group row">
                    <label for="inputFile" class="col-sm-2 col-form-label">Parcourir un fichier:</label>
                        <input type="file" id="fileinscr" name="fichier">

                    </label>
                </div>

                <div class="form-inline " style="margin-top:5%;">
                        <label for="text"> Année univérsitaire</label>
                        <input type="text" name="annee_u" class="form-control" style="margin-left:4%;">
                </div>
                <div class="form-group row">
                    <label for="inputSelect" class="col-sm-2 col-form-label">Importation de données</label>
                    <select class="custom-select" name="type_importation" style="margin-left:5%;">
                        <option selected>Choisir le type de données</option>
                        <option value="1">Inscription</option>
                        <option value="2">Résultat examen</option>
                    </select>
                </div>
                <div class="form-group row" style="margin-top:5%;">

                        <label for="inputSelect" class="col-sm-2 col-form-label">Niveau</label>
                        <select class="custom-select" name="niveau" style="margin-left:19%;">
                            <option value="L1">L1</option>
                            <option value="L2">L2</option>
                            <option value="L3">L3</option>
                            <option value="M1">M1</option>
                            <option value="M2">M2</option>
                        </select>


                </div>

                <div class="form-group row">
                   <label for="inputSubmit" class="col-sm-2 col-form-label"></label>
                   <input type="submit" value="Envoyer" class="btn btn-primary"/ style="margin-left:5%;">
                </div>
            </form>
        </div>

    </div>
</div>





</div>
</div>
</div>
