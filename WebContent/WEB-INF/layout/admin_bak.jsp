<%@ include file="tag_jstl.jsp"
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title> <c:out value="${ title }"/></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="${ base_url }/bootstrap/vendor/bootstrap/css/bootstrap.min.css">
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="${ base_url }/bootstrap/css/fontastic.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="${ base_url }/bootstrap/vendor/font-awesome/css/font-awesome.min.css">
    <!-- Google fonts - Poppins -->
    <!--<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">-->
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="${ base_url }/bootstrap/css/style.sciences.css" id="theme-stylesheet">
    <!-- Custom stylesheet - for your changes-->
    <link rel="stylesheet" href="${ base_url }/bootstrap/css/custom.css">
    <!-- Favicon-->
    <link rel="shortcut icon" href="${ base_url }/bootstrap/favicon.png">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
  </head>
  <body>
    <div class="page charts-page">
      <!-- Main Navbar-->
      <%@ include file="header_bar.jsp"
		%>
      <div class="page-content d-flex align-items-stretch"> 
        <!-- Side Navbar -->
        <jsp:include page ="${menu}"/>
        <div class="content-inner">
          <!-- Page Header-->
          <!-- Breadcrumb-->
		<%@ include file="page_header.jsp"
		%>
		
		<!--content-->
		 <jsp:include page ="${content}"/>
          <!-- Page Footer-->
         
         </div>
      </div>
    </div>
    <!-- Javascript files-->
   <%@ include file="script.jsp"
	%>
  </body>
</html>
