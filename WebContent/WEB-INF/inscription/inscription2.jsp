<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Faculté des Sciences|Université d'Antananarivo</title>
  <meta name="description" content="Faculté des Sciences d'Antananarivo">
  <meta name="keywords" content="Faculté des Sciences d'Antananarivo, Inscription, Université d'Antananarivo">

  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Open+Sans|Candal|Alegreya+Sans">

  <!--<link rel="stylesheet" type="text/css" href="Mentor/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="Mentor/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="Mentor/css/imagehover.min.css">-->
  <link rel="stylesheet" href="assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="assets/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="assets/bower_components/Ionicons/css/ionicons.min.css">
  
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="assets/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <link rel="stylesheet" href="assets/bower_components/select2/dist/css/select2.min.css">

  
  <!-- Theme style -->
  <link rel="stylesheet" href="assets/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="assets/dist/css/skins/_all-skins.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  
  
  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

  
  <!--<link rel="stylesheet" type="text/css" href=" Mentor/css/style.css">-->
  
  
</head>

<body>
	<div class="wrapper">
  <!--Navigation bar-->
  <nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href=""><span> Faculté des Sciences d'Antananarivo</span></a>
      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#actualite">Actualités</a></li>
         <!-- <li><a href="#" data-target="#preinscription" data-toggle="modal">Pré-inscription</a></li>
          <li><a href="#courses">Informations</a></li>
          <li><a href="#pricing">Contact</a></li>-->
          <li><a href="#" data-target="#login" data-toggle="modal">Se connecter</a></li>
        
          <li class="btn-trial"><a href="#contact">Contact</a></li>
        </ul>
      </div>
    </div>
  </nav>
  <!--/ Navigation bar-->
  <!--Modal box-->
  <div class="modal fade" id="login" role="dialog">
    <div class="modal-dialog modal-sm">

      <!-- Modal content no 1-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
         <!-- <h4 class="modal-title text-center form-title">Login</h4>-->
          <div class="login-logo">
			<!--<a href="../../index2.html"><b>Admin</b>LTE</a>-->
			<!--<img src=" assets/dist/img/logo-fac.jpeg"/>-->
		<img src=" assets/dist/img/logo-fac.jpeg" />
		</div>
  
        </div>
        <div class="modal-body padtrbl">

          <div class="login-box-body">
            <p class="login-box-msg">Accés reservé à l'administration</p>
            <div class="form-group">
		
				
              <form name="" id="loginForm" method="post" action="">
                <div class="form-group has-feedback">
                  <!----- username -------------->
                  <input name ="user" class="form-control" placeholder="Identifiant" id="loginid" type="text" autocomplete="off" />
                  <span style="display:none;font-weight:bold; position:absolute;color: red;position: absolute;padding:4px;font-size: 11px;background-color:rgba(128, 128, 128, 0.26);z-index: 17;  right: 27px; top: 5px;" id="span_loginid"></span>
                  <!---Alredy exists  ! -->
                  <span class="glyphicon glyphicon-user form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                  <!----- password -------------->
                  <input name="password" class="form-control" placeholder="Password" id="loginpsw" type="password" autocomplete="off" />
                  <span style="display:none;font-weight:bold; position:absolute;color: grey;position: absolute;padding:4px;font-size: 11px;background-color:rgba(128, 128, 128, 0.26);z-index: 17;  right: 27px; top: 5px;" id="span_loginpsw"></span>
                  <!---Alredy exists  ! -->
                  <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <div class="row">
                  <div class="col-xs-12">
                    <button type="submit" name="subLogin" class="btn btn-green btn-block btn-flat" onclick="userlogin()">Valider</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
  <!--/ Modal box-->
  
   <div class="content-wrapper">
  <section class="content-header">
	  <h1>
        Faculté des Sciences d'Antananarivo
      </h1>
       <ol class="breadcrumb">
        <li><a href=""><i class="fa fa-dashboard"></i> Accueil</a></li>
        <li><a href=""></a></li>
        <li class="active"></li>
      </ol>
  </section>
  <!--Banner-->
  <section class="content">
  <div class="row">
    <div class="col-md-12">
	
	<div></div>
</div>
</div>
  <div class="row">

	<div class="col-md-4">
		<div class="box box-danger">
            <div class="box-header">
              <h3 class="box-title">Bac série C, D, TGI</h3>
            </div>
            <div class="box-body">
				<?php echo form_open('#'); 
		//echo form_group_input('num_bac','', 'Numéro du bac', '000000');?>
		 <div class="form-group">
                <label>Numéro du bac:</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-id-card-o"></i>
                  </div>
                  <input type="text" name ='num_bac' placeholder='0000000' class="form-control" data-inputmask='"mask": "9999999"' data-mask>
                </div>
                <!-- /.input group -->
              </div>
		 
		 <?php
		//echo form_group_input('date_naissance','', 'Date de naissance', 'AAAA-MM-JJ'); 
		
		?>
	    	<div class="form-group">
                <label>Date de naissance:</label>

                <div class="input-group date">
                  <div class="input-group-addon">
                    <i class="fa fa-calendar"></i>
                  </div>
                  <input class="form-control pull-right" id="datepicker" name="date_naissance" data-inputmask="'alias': 'dd-mm-yyyy'" data-mask="" type="text">
                </div>
                <!-- /.input group -->
              </div>
	
			 <div class="form-group">
                <label>Numéro facture Banque:</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-bank"></i>
                  </div>
                  <input type="text" name ='num_fact' placeholder='0000000' class="form-control" data-inputmask='"mask": "9999999"' data-mask>
                </div>
                <!-- /.input group -->
              </div>
		 
	
	
			<div class='form-group'>
				<label for='portail'>Portail</label>
				<div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-book"></i>
                  </div>
					<select name='portail' id='disabledSelect' class='form-control select2' style="width: 100%;">
						<option selected> Choisir un portail</option>
						<option value='1'> Chimie</option>
						<option value='2'> Mathématiques - Informatique</option>
						<option value='3'> Physique</option>
						<option value='4'> SVT</option>
						</select>
				 </div>
			</div>

			<hr/>

			<?php echo form_submit('confirmer','Valider'); ?>

		<?php echo form_close(); ?>

			</div>
		</div>

	</div>
	
	<div class="col-md-8">

		<!--<legend>Bac série S</legend>-->

		
      <div class="box box-info">
        <div class="box-header with-border">
          <h3 class="box-title">Bac série S (spécialité « mathématiques » ou « sciences physiques »</h3>

          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-window-close-o"></i></button>
          </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
          <div class="row">
            <div class="col-md-6">
            <?php echo form_open('#'); ?>
			
			<?php echo form_group_input('num_bac','', 'Numéro du bac', '000000'); ?>
			<?php echo form_group_input('nom_prenom','', 'Nom et prénoms', 'Nom complet'); ?>
				<?php //echo form_group_input('date_naissance','', 'Date de naissance', 'AAAA-MM-JJ'); ?>
			<div class="form-group">
                <label>Numéro facture Banque:</label>

                <div class="input-group">
                  <div class="input-group-addon">
                    <i class="fa fa-bank"></i>
                  </div>
                  <input type="text" name ='num_fact' placeholder='0000000' class="form-control" data-inputmask='"mask": "9999999"' data-mask>
                </div>
                <!-- /.input group -->
              </div>
		 
	             
			<div class='form-group'>
				<label for='portail'>Portail</label>
				<select name='portail' id='disabledSelect' class='form-control select2' style="width: 100%;">
				<option selected> Choisir un portail</option>
				<option value='1'> Chimie</option>
				<option value='2'> Mathématiques - Informatique</option>
				<option value='3'> Physique</option>
				<option value='4'> SVT</option>
				</select>
			</div>

			<hr/>


            </div>
            <!-- /.col -->
            <div class="col-md-6">
            <?php echo form_group_input('moyenne','', 'Moyenne', 'Moyenne/20'); ?>
			<?php echo form_group_input('math','', 'Note Math', 'Note math avec coefficient'); ?>
			<?php echo form_group_input('pc','', 'Note PC', 'Note PC avec coefficient'); ?>
            <?php echo form_group_input('sn','', 'Note SN', 'Note SN avec coefficient'); ?>
            
            </div>
            			
          </div>
          <div class="row">
			<div class="col-md-6">
			<?php echo form_submit('bacS','Valider'); ?>

			<?php echo form_close(); ?>

			</div>
          </div>
          <!-- /.row -->
        </div>
        <!-- /.box-body -->
        
      </div>
      <!-- /.box -->
	</div>

</div>
  
  </section>
  </div>
</div>
  <script src="Mentor/js/jquery.easing.min.js"></script>
  <script src="Mentor/js/custom.js"></script>
 <!-- <script src="Mentor/contactform/contactform.js"></script>-->
  <!-- jQuery 3 -->
<script src=" assets/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src=" assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Select2 -->
<script src=" assets/bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- InputMask -->
<script src=" assets/plugins/input-mask/jquery.inputmask.js"></script>
<script src=" assets/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src=" assets/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src=" assets/bower_components/moment/min/moment.min.js"></script>
<script src=" assets/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src=" assets/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<script src=" assets/bower_components/bootstrap-datepicker/dist/locales/bootstrap-datepicker.fr.min.js"></script>

<!-- bootstrap color picker -->
<script src=" assets/bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src=" assets/plugins/timepicker/bootstrap-timepicker.min.js"></script>
<!-- SlimScroll -->
<script src=" assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- iCheck 1.0.1 -->
<script src=" assets/plugins/iCheck/icheck.min.js"></script>
<!-- FastClick -->
<script src=" assets/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src=" assets/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src=" assets/dist/js/demo.js"></script>
<!-- Page script -->
<script type="application/javascript">
  $(function () {
    //Initialize Select2 Elements
    $('.select2').select2()

    //Datemask dd/mm/yyyy
    $('#datemask').inputmask('dd/mm/yyyy', { 'placeholder': 'dd/mm/yyyy' })
    //Datemask2 mm/dd/yyyy
    $('#datemask2').inputmask('mm/dd/yyyy', { 'placeholder': 'mm/dd/yyyy' })
    //Money Euro
    $('[data-mask]').inputmask()

    //Date range picker
    $('#reservation').daterangepicker()
    //Date range picker with time picker
    $('#reservationtime').daterangepicker({ timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A' })
    //Date range as a button
    $('#daterange-btn').daterangepicker(
      {
        ranges   : {
          'Today'       : [moment(), moment()],
          'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
          'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
          'Last 30 Days': [moment().subtract(29, 'days'), moment()],
          'This Month'  : [moment().startOf('month'), moment().endOf('month')],
          'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: moment().subtract(29, 'days'),
        endDate  : moment()
      },
      function (start, end) {
        $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
      }
    )

    //Date picker
    $('#datepicker').datepicker({
      autoclose: true,
      language: 'fr',
      //format: 'dd/mm/yyyy' 
    })

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass   : 'iradio_minimal-blue'
    })
    //Red color scheme for iCheck
    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
      checkboxClass: 'icheckbox_minimal-red',
      radioClass   : 'iradio_minimal-red'
    })
    //Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
      checkboxClass: 'icheckbox_flat-green',
      radioClass   : 'iradio_flat-green'
    })

    //Colorpicker
    $('.my-colorpicker1').colorpicker()
    //color picker with addon
    $('.my-colorpicker2').colorpicker()

    //Timepicker
    $('.timepicker').timepicker({
      showInputs: false
    })
    $('#portailSelect').change(function(){
            $('.critere').hide();
            $('#table' + $(this).val()).show();
            $('.n_inscrit').hide();
            //alert('#etudiant' + $(this).val());
            $('#etudiant' + $(this).val()).show();
        })
  })
</script>

</body>

</html>
