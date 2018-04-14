$(document).ready(function(){
	$('.class_serie').change(function(){
		if(confirm("est-ce que vous voulez ajouter d'autre serie")){
			$('.class_autreSerie').css("display","block");
			$('.class_autreSerie').change(function(){
				$('.class_autreSerie').css("display","none");
			});
		}else{
			$('.class_autreSerie').css("display","none");
		}
	});
});

alert ("o");
var numcri = document.getElementsByClassName('numcri');
var modaly = document.getElementsByClassName('modaly');
// var indice = [];
for (var i = 0;i< numcri.length; i++) {

	var indice[i] = numcri[i].value;
	//alert(indice[i]);
	modaly[i].addEventListener('click',function(){

		var notema = "id_noteMath_"+indice[i];
		var notepc = "id_notePc_"+indice[i];
		var notesn = "id_noteSvt_"+indice[i];
		var notemoyen = "moyenne_"+indice[i];
		var valiny = "response_"+indice[i];
		var mapc = "mp_"+indice[i];
		var masn = "ms_"+indice[i];
		var ps = "ps_"+indice[i];
		var noteMath = document.getElementsByClassName('note_math');

		var mat = document.getElementById(notema);
		var phy = document.getElementById(notepc);
		var sn = document.getElementById(notesn);
		var moyen = document.getElementById(notemoyen);
		var answer = document.getElementById(valiny);
		var choixmapc = document.getElementById(mapc);
		var choixmasn = document.getElementById(masn);
		var choixps = document.getElementById(ps);
		alert( $("#moyenne_"+indice[i]));
	});



}
