<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- Side Navbar -->
        <nav class="side-navbar">
          <!-- Sidebar Header-->
          <div class="sidebar-header d-flex align-items-center">
          <!--  <div class="avatar"><img src="img/avatar-1.jpg" alt="..." class="img-fluid rounded-circle"></div>
            <div class="title">
              <h1 class="h4">${ session.getAttribute("utilisateur") }</h1>
              <p>Web Designer</p>
            </div>-->
          </div>
          <!-- Sidebar Navidation Menus-->
          
          
          
         
         <!-- <span class="heading">Main</span>
          <ul class="list-unstyled">
            <li> <a href="index.html"><i class="icon-home"></i>Home</a></li>
            <li><a href="#dashvariants" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>Baccalauréat </a>
              <ul id="dashvariants" class="collapse list-unstyled">
                <li><a href="${ base_url }/result">Résultat</a></li>
                <li><a href="#">Page</a></li>
               
              </ul>
            </li>
            <li><a href="#import" aria-expanded="false" data-toggle="collapse"> <i class="icon-interface-windows"></i>Pré-inscription </a>
              <ul id="import" class="collapse list-unstyled">
                <li><a href="${ base_url }/import">Importer données</a></li>
                
              </ul>
            </li>
            <li class="active"><a href="tables.html"> <i class="icon-grid"></i>Tables </a></li>
            
          </ul>-->
          <span class="heading">Baccalauréat</span>
           <ul class="list-unstyled">
				<li class="active"><a href="${ base_url }/result"> <i class="icon-padnote"></i>Résultats 2016-2017</a></li>
				

           </ul>
           <span class="heading">Pré-inscription</span>
           <ul class="list-unstyled">
				<li class="active"><a href="${ base_url }/preinscription"> <i class="icon-grid"></i>Pré-inscrits - saisie</a></li>
				<li class="active"><a href="${ base_url }/preinscription_en_ligne"> <i class="icon-grid"></i>Pré-inscrits - en ligne</a></li>
				
				<li class="active"><a href="${ base_url }/anomalie"> <i class="icon-picture"></i>Anomalies</a></li>
			    <li class="active"><a href="${ base_url }/import"><i class="icon-interface-windows"></i>Importer données</a></li>
			    <li class="active"><a href="${ base_url }/selection"><i class="icon-flask"></i>Critères de sélection</a></li>
			    <li class="active"><a href="${ base_url }/resultat_preinscription"><i class="icon-grid"></i>Résultat sélection</a></li>
                <li class="active"><a href="${ base_url }/pdf_bac_etranger"><i class="icon-grid"></i>BAC ETRANGER</a></li>
				
       
           <span class="heading">Inscription</span>
           <ul class="list-unstyled">
				<li class="active"><a href="${ base_url }/confirmer_inscription"><i class="icon-grid"></i>Liste confirmation</a></li>
           </ul>
           
        </nav>
    
