
function changeSelect(name){
	var loc = '"' + window.location + '"';
	if(loc.indexOf("&name=") != -1)
		loc = loc.substring(0, loc.indexOf("&name="));
	loc = loc.replace(/"/g,"");
	window.location=loc + "&name="+name;
}

function changeType(type){
	window.location="?type="+type;
}

function updateStat(){
	var form = document.getElementById("time_form");
	var from = form.date_from.value;
	var to = form.date_to.value;
	var grouping = form.grouping.value;
	var loc = '"' + window.location + '"';
	if(loc.indexOf("&from=") != -1)
		loc = loc.substring(0, loc.indexOf("&from"));
	loc = loc.replace(/"/g,"");
	window.location=loc+"&from="+from+"&to="+to+"&grouping="+grouping;
}


function getImage(image, url){
	if(image.src != url){
		image.src = url;
		image.onload = function (){
			this.width = 800;
			this.height = 400;
		};
	}
}



