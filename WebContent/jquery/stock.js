
$(document).ready(function(){

  $(".ecueNbre").change(function(){

      var compte = 0;
      compte =parseFloat($(this).val());
      alert("Vous devez introduire" +compte + " ECUE(S)");
      // alert($(this).val());
      var statu = "true";

      for (var i = 0; i<compte; i++) {

        $(".nomEcue").append("<div class=\"row\" style=\"margin-top:3%;\"><div class=\"input-group\" style=\"margin-top:4%;margin-left:5%;\"><label for=\"text\" class=\"labe\" style=\"margin-left:6%;\">"+"Nom ECUE "+ "</label><input type=\"text\" name=\"nomEcue\" class=\"form-control ecue\" style=\"margin-top:3%;margin-left:7%;\">"+"</div></div>"); 
      }   
    
  });

  var ecue = document.getElementsByClassName('ecue');
  var xhr = new XMLHttpRequest();
  $("#enreg").click(function(){

    var donne =[];
    // donne[0]=$(".nomUe").val();
    for (var i =0; i<ecue.length; i++) {
      
      // alert(ecue[i].value);
      donne[i] = ecue[i].value;

    }
    //alert(donne);

    //envoie de donnée via ajax

    xhr.open("POST","/ajoutUe");
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send("donne="+donne);

    xhr.onreadystatechange=function(){
      if(xhr.readyState==4 && xhr.status==200){

          window.location="/liste_parcours";
      }else{
        alert("misy tsy mety ");
      }

    }

    $(".labe").remove();
    $(".ecue").remove();
    $(".ecueNbre").val() = "Ecue";
  });

  $(".ajout").click(function(){
    $(".ecueNbre").val() = "Ecue";
  });

});
  