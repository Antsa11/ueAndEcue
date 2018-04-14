var id = document.getElementsByClassName("btn");

for(var i=0; i<id.length; i++)
{
	id[i].addEventListener('click', modifier, false);
}

function modifier()
{
	var id_etudiant = this.parentNode.childNodes[1].value;
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "cursus", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send('id_etudiant=' + id_etudiant);	
	// xhr.open('GET', 'list_etudiant?id_etudiant=' + id_etudiant);
	// xhr.send(null);
	xhr.addEventListener('readystatechange', function fonction(){
		if(xhr.readyState===XMLHttpRequest.DONE && xhr.status == 200){
			alert('DONE');
		}
	});
}
