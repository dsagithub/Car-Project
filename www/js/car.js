var API_BASE_URL="http://localhost:8000/Car-api";
var name ="yifei";
var num=1;
var element;
var resultado=document.getElementById("result");


/*$("#posicion").click(function(e)
{
	e.preventDefault();
	getPosicion();
});*/

/*$("#crear_panel").click(function (e)
{
	e.preventDefault();
	crearPanel();
});*/

$("#panelholder").click(function (e)
{
	e.preventDefault();
	getPosicions(name);
});


/*$("#post_posicion").click(function (e)
{
	e.preventDefault();

	var newposicion = new Object();

	newposicion.username="yifei";
	newposicion.coordenadaX=lat;
	newposicion.coordenadaY=lon;
	console.log(newposicion.coordenadaX);
	console.log(newposicion.coordenadaY);
	newposicion.descripcion="Esto es una prueba desde cliente web";
	createPosicion(newposicion);

});*/

$("#get_last").click(function (e)
{
	e.preventDefault();
	getLast(name);
});

$("#info-posicion1").click(function (e)
{
	e.preventDefault();
	
	element=document.getElementById("link-addM1").id;

	console.log("el elemento es:"+"#"+element);

	console.log("el valor element fuera de la funcion es" +element);




	getAction(element,1);

    

	 
});

$("#info-posicion2").click(function (e)
{
	e.preventDefault();
	
	element=document.getElementById("link-addM2").id;

	console.log("el elemento es:"+"#"+element);

	console.log("el valor element fuera de la funcion es" +element);




	getAction(element,2);

    

	 
});


$("#info-posicion3").click(function (e)
{
	e.preventDefault();
	
	element=document.getElementById("link-addM3").id;

	console.log("el elemento es:"+"#"+element);

	console.log("el valor element fuera de la funcion es" +element);




	getAction(element,3);

    

	 
});


$("#info-posicion4").click(function (e)
{
	e.preventDefault();
	
	element=document.getElementById("link-addM4").id;

	console.log("el elemento es:"+"#"+element);

	console.log("el valor element fuera de la funcion es" +element);




	getAction(element,4);

    

	 
});

$("#info-posicion5").click(function (e)
{
	e.preventDefault();
	
	element=document.getElementById("link-addM5").id;

	console.log("el elemento es:"+"#"+element);

	console.log("el valor element fuera de la funcion es" +element);




	getAction(element,5);

    

	 
});


 $("#get-opinions").click(function (e)
 {
 	e.preventDefault();
 	getopinions();
 	document.getElementById("comments_result").style.display="block";

 });

 $("#update-opinion").click(function (e)
 {
 	e.preventDefault();
 	var newOpi=new Object();
 	var id=$("#InputID").val();
 	console.log(id);
 	newOpi.content=$("#Comment-Content").val();
 	window.alert($("#Comment-Content").val());
 	console.log(newOpi.content);

 	updateOpinion(newOpi,id);
 });










/*function crearPanel()
{



  //var panel="<div class="panel panel-default" id="panel-JS">";
    




	$('<div class="panel panel-default" id="panel-JS">').appendTo($('#panelJS'));
	$('<div class="panel-heading" id="panel-head-JS">').appendTo($('#panelJS'));
    $('<h4 class="panel-title">').appendTo($('#panelJS'));
    $('<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" id="mapa-JS">Mostrar mapa</a>').appendTo($('#panelJS'));
    $('</h4>').appendTo($('#panelJS'));
    $('</div>').appendTo($('#panelJS'));
    $('<div id="collapseOne" class="panel-collapse collapse in">').appendTo($('#panelJS'));
    $('<div class="panel-body" id="panel-body-JS">').appendTo($('#panelJS'));
    $('<p>Esto es una prueba para crear Panel por JavaScript').appendTo($('#panelJS'));
    $('</p>').appendTo($('#panelJS')); 
    $('</div>').appendTo($('#panelJS'));
    $('</div>').appendTo($('#panelJS'));
    $('</div>').appendTo($('#panelJS'));




}*/



/*function getPosicion()
{
	
	if(navigator.geolocation)
	{
		navigator.geolocation.getCurrentPosition(showPosition,showError);
	}
	else
	{
		resultado.innerHTML="El navegador no soporta el Geolocalizacion";

	}

}

function showPosition(position)
{
	lat=position.coords.latitude;
	lon=position.coords.longitude;
	$("#coordenadasX").text(lat+",");
	$("#coordenadasY").text(lon);

	latlon=new google.maps.LatLng(lat,lon)
	mapholder=document.getElementById('mapholder')
	mapholder.style.height='500px';
	mapholder.style.width='500px';

	var myOptions=
	{
		center:latlon,zoom:14,
		mayTypeId:google.maps.MapTypeId.ROADMAP,
		mapTypeControl:false,
		navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
	}

	var map=new google.maps.Map(document.getElementById("mapholder"),myOptions);
	var marker = new google.maps.Marker({position:latlon,map:map,title:"You are here"});
}

function showError(error)
{
	switch(error.code)
	{
		case error.PERMISSION_DENIED:
		resultado.innerHTML="El usuario no ha aceptado la petition de Geolocalizacion"
		break;
		case error.POSITION_UNAVAILABLE:
		resultado.innerHTML="La localizacion no esta disponible"
		break;
		case error.TIMEOUT:
		resultado.innerHTML="La petition de Geolocalizacion no ha podido establecer por TIME OUT"
		break;
		case error.UNKNOW_ERROR:
		resultado.innerHTML="Ha ocurrido un error desconocido"
		break;

	}
}*/


function initialize() 
{
  mapholder=document.getElementById('mapholder')
	mapholder.style.height='500px';
	mapholder.style.width='350px';

	var myOptions=
	{
		zoom: 7,
        center: new google.maps.LatLng(41.385063900000,2.173403499999949400),
		mayTypeId:google.maps.MapTypeId.ROADMAP,
		mapTypeControl:false,
		navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
	}

	var map=new google.maps.Map(document.getElementById("mapholder"),myOptions);
}





/*function createPosicion(posicion)
{
	var url=API_BASE_URL+'/posicion';
	var data=JSON.stringify(posicion);

	$("#posicion_result").text('');

	$.ajax
	({

		url:url,
		type:'POST',
		contentType: "application/vnd.Car_api.posicion+json",
		crossDomain:true,
		dataType:'json',
		data:data,
	}).done(function(data,status,jqxhr)
	{
		$('<div class="alert alert-success"> <strong>Ok!</strong> Posicion enviado</div>').appendTo($("#posicion_result"));	
	}).fail(function()
	{
		$('<div class="alert alert-danger"> <strong>Oh!</strong> Ha occurido un error</div>').appendTo($("#posicion_result"));	
	});
}*/



function getLast(name)
{
	var url= API_BASE_URL+'/posicion/last?username='+name;
	//console.log(url);


	//$("#last_result").text('');

    $.ajax
    ({
    	url:url,
    	type:'GET',
    	crossDomain:true,
    	dataType:'json',
    }).done(function(data,status,jqxhr)
    {
    	var pos=data;
    	//console.log(pos);
    	//console.log(pos.idposicion);

    	$("#panel1").text(pos.idposicion);
    	$('<h4> ID de la posicion:'+ pos.idposicion+'</h4>').appendTo($('#panel-idposicion'+i));
    	$('<p>').appendTo($('#panel-body'+i));
    	$('<strong> Latitude:'+pos.coordenadaX+'</strong></br>').appendTo($('#panel-coordenadasX'+i));
    	$('<strong> Longitude:'+pos.coordenadaY+'</strong></br>').appendTo($('#panel-coordenadasY'+i));
    	$('<strong> Descripcion:'+pos.descripcion+'</strong><br>').appendTo($('#panel-descripcion'+i));
    	$('<strong> Fecha:'+pos.fecha+'</strong></br>').appendTo($('#panel-fecha'+i));
    	$('</p>').appendTo($('#panel-body'+i));
    }).fail(function()
    {
    	$('<div class="alert alert-danger"> <strong>Oh!</strong> Ha occurido un error</div>').appendTo($('#panel-body'+i));

    });
 }


window.document.getElementById('panelholder').addEventListener('load',getPosicions);

 function getPosicions(name)
 {
 	var url=API_BASE_URL+'/posicion?username='+name+'&'+'pag=1';
 	//console.log(url);

 	$.ajax
 	({
 		url:url,
 		type:'GET',
 		crossDomain:true,
 		dataType:'json',
 	}).done(function(data,status,jqxhr)
 	
 	{
 		var pos=data;
 		//console.log(pos);

        <!-- super importante pos.posiciones para poder pasar los datos -->

 		$.each(pos.posiciones,function(index,value)
 		{
 			//console.log("El valor de pos posiciones"+pos.posiciones)
 			var posi=value;
 			//console.log(value);
 			//console.log("El valor de value posiciones"+value.posiciones)

    	$('<h4> ID de la posicion:'+ value.idposicion+'</h4>').appendTo($('#panel-idposicion'+num));
    	//$('<p>').appendTo($('#panel-body'+num));
    	$('<strong> Latitude:'+value.coordenadaX+'</strong></br>').appendTo($('#panel-coordenadasX'+num));
    	$('<strong> Longitude:'+value.coordenadaY+'</strong></br>').appendTo($('#panel-coordenadasY'+num));
    	$('<strong> Descripcion:'+value.descripcion+'</strong><br>').appendTo($('#panel-descripcion'+num));
    	$('<strong> Fecha:'+value.fecha+'</strong></br>').appendTo($('#panel-fecha'+num));
    	//$('</p>').appendTo($('#panel-body'+num));
    	num=num+1;
 		});

        //latlon=new google.maps.LatLng(lat,lon)
 		//var marker = new google.maps.Marker({position:latlon,map:map,title:"You are here"});

 	}).fail(function()
 	{
 		$('<div class="alert alert-danger"> <strong>Oh!</strong> Ha occurido un error</div>').appendTo($("#posicion_result"));

 	});
 }




 function getAction(element,id)
 {

 	$("#"+element).click(function (e)	
    {
	 e.preventDefault();
	 
	 addMarker(id); // aqui paso el id de los panales

    });

 }


 function addMarker(id)
 {

 	//console.log("La coordenadaX es"+$("#panel-coordenadasX1").text());
 	//var element=document.getElementById("panel-coordenadasX1").id;
 	//console.log("La coordenadaX es:"+"#"+element);
 	var lati=$("#panel-coordenadasX"+id).text(); //Tiene que ser generica
    console.log("valor latitude:"+lati);
    var reslat=lati.split(":");
    console.log("valor split latitude:"+reslat);
    var splilat=reslat[1];
    console.log("valor split latitude:"+splilat);


    var longi=$("#panel-coordenadasY"+id).text(); //Tiene que ser generica
    console.log("valor longitude:"+longi);
    var reslong=longi.split(":");
    console.log("valor split longitude:"+reslong);
    var splilong=reslong[1];
    console.log("valor split longitude:"+splilong);

    lat=parseFloat(splilat);
    lon=parseFloat(splilong);
 	//lat=$("#panel-coordenadasX1").text());
    //lon=$("panel-coordenadasY1").text());
    latlon=new google.maps.LatLng(lat,lon);



    var myOptions=
	{
		zoom: 19,
        center: new google.maps.LatLng(lat,lon),
		mayTypeId:google.maps.MapTypeId.ROADMAP,
		mapTypeControl:false,
		navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
	}

	var map=new google.maps.Map(document.getElementById("mapholder"),myOptions);

    var marker = new google.maps.Marker({position:latlon,map:map,title:"You are here"});

 }


 function getopinions()
 {
 	var url=API_BASE_URL+'/opinion?username='+name;
 	$.ajax
 	({
 		url:url,
 		type:'GET',
 		crossDomain:true,
 		dataType:'json',
 	}).done(function(data,status,jqxhr)
 	{
 		var ops=data;
 		//console.log(pos);

        <!-- super importante pos.posiciones para poder pasar los datos -->

 		$.each(ops.opiniones,function(index,value)
 		{
 			//console.log("El valor de pos posiciones"+pos.posiciones)
 			var opins=value;
 			//console.log(value);
 			//console.log("El valor de value posiciones"+value.posiciones)

    	$('<h4> Content:'+ value.content+'    '+'<a href="#comments-area"><i class="fa fa-pencil-square-o"></i></a>'+'</h4>').appendTo($('#panel-opinion'));
    	$('<p>').appendTo($('#panel-opinion'));
    	$('<strong> Fecha:'+value.fecha+'</strong></br>').appendTo($('#panel-opinion'));
    	$('<strong> Idopinion:'+value.idopinion+'</strong></br>').appendTo($('#panel-opinion'));
    	$('<strong> Idposicion:'+value.idposicion+'</strong><br>').appendTo($('#panel-opinion'));
    	$('</p>').appendTo($('#panel-opinion'));
    	$('<li class="divider"></li>').appendTo($('#panel-opinion'));
 		});

        //latlon=new google.maps.LatLng(lat,lon)
 		//var marker = new google.maps.Marker({position:latlon,map:map,title:"You are here"});

 	}).fail(function()
 	{
 		$('<div class="alert alert-danger"> <strong>Oh!</strong> Ha occurido un error</div>').appendTo($("#posicion_result"));

 	});
 }

 function updateOpinion(opinion,id)
 {
 	var url=API_BASE_URL+'/opinion/'+id;
 	var data=JSON.stringify(opinion);

 	$.ajax
 	({
 		url:url,
 		type : 'PUT',
 		contentType: "application/vnd.Car.api.opinion+json",
		crossDomain : true,
		dataType : 'json',
		data : data,
		statusCode: 
		{
			404: function() {$('<div class="alert alert-danger"> <strong>Oh!</strong> Ha occurido un error</div>').appendTo($("#posicion_result"));}
		}

 	}).done(function(data,status,jqxhr)
 	{
 		alert("Datos actualizado");
 	}).fail(function() 
  	{
		alert("Ha ocurrido un error");
	});

 }






 google.maps.event.addDomListener(window, 'load', initialize);
































