<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- Side Navbar -->
        <nav class="side-navbar">
          <!-- Sidebar Header-->
          <!--  <div class="sidebar-header d-flex align-items-center">
          <div class="avatar"><img src="img/avatar-1.jpg" alt="..." class="img-fluid rounded-circle"></div>
            <div class="title">
              <h1 class="h4">${ request.getAttribute("utilisateur") }</h1>
              <p>Web Designer</p>
            </div>
          </div>-->
          <!-- Sidebar Navidation Menus-->

          <span class="heading">Baccalauréat</span>
           <ul class="list-unstyled">
				<li class="active"><a href="${ base_url }/result"> <i class="fa fa-address-book-o"></i>Résultats  2016-2017</a></li>


           </ul>
           <span class="heading">Pré-inscription</span>
           <ul class="list-unstyled">
				<li class="active"><a href="${ base_url }/preinscription"> <i class="fa fa-check-square"></i>Pré-inscrits</a></li>
				<li class="active"><a href="${ base_url }/preinscription_en_ligne"> <i class="fa fa-check-square-o"></i>Pré-inscrits - en ligne</a></li>
        <li class="active"><a href="${ base_url }/liste_mention"> <i class="fa fa-archive"></i>Liste Mention</a></li>
				<li class="active"><a href="${ base_url }/anomalie"> <i class="fa fa-warning"></i>Anomalies</a></li>
			    <li class="active"><a href="${ base_url }/import"><i class="fa fa-file"></i>Importer données</a></li>
			    <li class="active"><a href="${ base_url }/pdf_bac_etranger"><i class="fa fa-globe"></i>BAC ETRANGER</a></li>

           </ul>
           <span class="heading">Sélection</span>
           <ul class="list-unstyled">
				<li class="active"><a href="${ base_url }/selection?v=1"><i class="fa fa-paper-plane"></i>Vague 1</a></li>
				<li class="active"><a href="${ base_url }/selection?v=2"><i class="fa fa-paper-plane-o"></i>Vague 2</a></li>
			    <li class="active"><a href="${ base_url }/resultat_preinscription?v=1"><i class="fa fa-tag"></i>Sélection 1</a></li>
			     <li class="active"><a href="${ base_url }/resultat_preinscription?v=2"><i class="fa fa-tags"></i>Sélection 2</a></li>

           </ul>

           <span class="heading">Inscription</span>
           <ul class="list-unstyled">
				<li class="active"><a href="${ base_url }/confirmer_inscription"><i class="fa fa-flag-o"></i>Liste confirmation</a></li>
               <c:if test = "${status==\"Doyen\"}">
                   <li class="active"><a href="${ base_url }/derogation"><i class="fa fa-handshake-o"></i>Liste dérogation</a></li>
                   <li class="active"><a href="${ base_url }/etudiant_inscr_anomalie"><i class="fa fa-warning"></i>Anomalie inscription</a></li>
                   <li class="active"><a href="${ base_url }/liste_mention_data"><i class="fa fa-hand-peace-o"></i>Autorisés à s&apos;inscrire </a></li>
                </c:if>
           </ul>

           <c:if test = "${status==\"Enseignant|Admin\"}">

				<span class="heading">Administration</span>
					<ul class="list-unstyled">
						<li class="active"><a href="${ base_url }/utilisateur"><i class="icon-grid"></i>Utilisateurs</a></li>
					</ul>
           </c:if>
        </nav>
