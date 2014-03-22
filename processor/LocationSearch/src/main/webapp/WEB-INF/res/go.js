// Fonction pour virer le formulaire quand on scrolle
$(function(){ 
    var targets = $("#container");
    
    $(window).scroll(function(){
        var pos = $(window).scrollTop();
        if(pos > 200){
            targets.fadeOut("slow" );
			$(".choix").fadeOut("slow");
			$("#normal1").fadeOut("slow");
			} else {
            targets.fadeIn("slow");
			$(".choix").fadeIn("slow");
			$("#normal1").fadeIn("slow");

        }
    });

});

document.getElementById('search').focus(); //-->

// Fonction "initiatrice" pour changeSite()
test= 7;
 $('#'+test).animate({opacity: 1},'slow');
section= document.getElementById("7").alt;

$("#"+section).fadeIn("slow");
menu= 'theme';
// Fonction pour changer le menu utilisateur
function changeMenu(id)
{
$("#theme").fadeOut("fast");
document.getElementById("imgtheme").src = 'rsc/img/ui/flecheGris.png';
$("#compte").fadeOut("fast");
document.getElementById("imgcompte").src = 'rsc/img/ui/flecheGris.png';
$("#moteur").fadeOut("fast");
document.getElementById("imgmoteur").src = 'rsc/img/ui/flecheGris.png';
menu=id;

document.getElementById("img"+id).src = 'rsc/img/ui/flecheGrisFonce.png';
$("#"+id).delay(205).fadeIn("slow");

}

// Fonction pour changer le menu utilisateur
function changeMenuFleche(id)
{
document.getElementById("imgtheme").src = 'rsc/img/ui/flecheGris.png';
document.getElementById("imgcompte").src = 'rsc/img/ui/flecheGris.png';
document.getElementById("imgmoteur").src = 'rsc/img/ui/flecheGris.png';
document.getElementById("img"+id).src = 'rsc/img/ui/flecheGrisFonce.png';

}

function changeMenuFlecheOut()
{
document.getElementById("imgtheme").src = 'rsc/img/ui/flecheGris.png';
document.getElementById("imgcompte").src = 'rsc/img/ui/flecheGris.png';
document.getElementById("imgmoteur").src = 'rsc/img/ui/flecheGris.png';
document.getElementById("img"+menu).src = 'rsc/img/ui/flecheGrisFonce.png';

}

// Scrolltop au niveau des IDs [À CHANGER]
$('a[href^="#"]').click(function(){
	var the_id = $(this).attr("href");
	$('html, body').animate({
		scrollTop:$(the_id).offset().top
	}, 'slow');
	return false;
});

// Scrolltop au niveau des IDs [À CHANGER]
$('a[href^="#authentification"]').click(function(){
	
	var the_id = $(this).attr("href");
	$('html, body').animate({
		scrollTop:$('#haut').offset().top
	}, 'slow');
	return false;
	
});



// Changement des sections (images, maps, blabla)
function changeSection(name,site)
{
$('#'+site+' a.categorie-active').removeClass('categorie-active').addClass('categorie');
$('#'+site+' a[name="'+name+'"]').addClass('categorie-active').removeClass('categorie');
$('#search').focus();  
$('form[name="recherche"] input[name="section"]').val(name);
}

// Changement moteur
function changeSite(alt,site,id)
{

$('#search').focus();  
document.forms["recherche"].elements["site"].value= alt;
test= id;
$("#"+section).fadeOut("fast");
$("#"+alt).delay(200).fadeIn("slow");
section= alt;
$('form[name="recherche"] input[name="section"]').val('1');
$('#'+alt+' a.categorie-active').removeClass('categorie-active').addClass('categorie');
$('#'+alt+' a:first-of-type').removeClass('categorie').addClass('categorie-active');
}

// "OnClick" sur les images de moteurs
$('div.container div.choix img').click(function(event) {
	alt = $(this).attr('alt');
	site = $(this).attr('src');
	id = $(this).attr('id');
	changeSite(alt,site,id);
});






// Animations des logos
var id = 7;




function affichageOn() 
{
	if(id=='1'){
	$('#1').show();
	$('#1').animate({ 
        width: "13%",
        marginLeft: "85px",
		marginTop: "-30px",
		opacity: 1,
      }, 150 );
	}
	else{
	$('#1').show();
	$('#1').animate({ 
        width: "13%",
        marginLeft: "85px",
		marginTop: "-30px",
		opacity: 0.4,
      }, 150 );
	  }
	
	if(id=='2'){
	$('#2').show();
	$('#2').animate({ 
        width: "13%",
        marginLeft: "215px",
		marginTop: "-30px",
		opacity: 1,
      }, 200 );
	  }
	 else{
	 $('#2').show();
	$('#2').animate({ 
        width: "13%",
        marginLeft: "215px",
		marginTop: "-30px",
		opacity: 0.4,
      }, 200 );
	  }
	  
	  
	if(id=='3'){
	$('#3').show();
	$('#3').animate({ 
        width: "13%",
        marginLeft: "350px",
		marginTop: "-30px",
		opacity: 1,
      }, 210 );
	  }
	  else{
	  $('#3').show();
	$('#3').animate({ 
        width: "13%",
        marginLeft: "350px",
		marginTop: "-30px",
		opacity: 0.4,
      }, 210 );
	  }
	  
	  
	if(id=='4'){
	$('#4').show();
	$('#4').animate({ 
        width: "13%",
        marginLeft: "470px",
		marginTop: "-30px",
		opacity: 1,
      }, 210 );
	  }
	 else{
	 	$('#4').show();
	$('#4').animate({ 
        width: "13%",
        marginLeft: "470px",
		marginTop: "-30px",
		opacity: 0.4,
      }, 210 );
	  }

	 if(id=='5'){
	$('#5').show();
	$('#5').animate({ 
        width: "13%",
        marginLeft: "605px",
		marginTop: "-30px",
		opacity: 1,
      }, 220 );
	}
	else{
	$('#5').show();
	$('#5').animate({ 
        width: "13%",
        marginLeft: "605px",
		marginTop: "-30px",
		opacity: 0.4,
      }, 220 );
	  }
	  
	if(id=='6'){
	$('#6').show();
	$('#6').animate({ 
        width: "13%",
        marginLeft: "755px",
		marginTop: "-30px",
		opacity: 1,
      }, 200 );
	  }
	  else{
	  $('#6').show();
	$('#6').animate({ 
        width: "13%",
        marginLeft: "755px",
		marginTop: "-30px",
		opacity: 0.4,
      }, 200 );
	  }


	if(id=='7'){
	$('#7').show();
	$('#7').animate({ 
        width: "13%",
        marginLeft: "85px",
		marginTop: "50px",
		opacity: 1,
      }, 150 );
	  }
	  else{
	  $('#7').show();
	$('#7').animate({ 
        width: "13%",
        marginLeft: "85px",
		marginTop: "50px",
		opacity: 0.4,
      }, 150 );
	  }
	  
	 
	if(id=='8'){
	$('#8').show();
	$('#8').animate({ 
        width: "13%",
        marginLeft: "215px",
		marginTop: "50px",
		opacity: 1,
      }, 200 );
	  }
	 else{
	 $('#8').show();
	$('#8').animate({ 
        width: "13%",
        marginLeft: "215px",
		marginTop: "50px",
		opacity: 0.4,
      }, 200 );
	  }
	  
	  
	if(id=='9'){ 
	$('#9').show();
	$('#9').animate({ 
        width: "13%",
        marginLeft: "350px",
		marginTop: "50px",
		opacity: 1,
      }, 210 );
	  }
	 else{
	 $('#9').show();
	$('#9').animate({ 
        width: "13%",
        marginLeft: "350px",
		marginTop: "50px",
		opacity: 0.4,
      }, 210 );
	  }
	 
	 
	if(id=='10'){
	$('#10').show();
	$('#10').animate({ 
        width: "13%",
        marginLeft: "470px",
		marginTop: "50px",
		opacity: 1,
      }, 210 );
	  }
	  else{
	  $('#10').show();
	$('#10').animate({ 
        width: "13%",
        marginLeft: "470px",
		marginTop: "50px",
		opacity: 0.4,
      }, 210 );
	  }
	  
	  
	if(id=='11'){
	$('#11').show();
	$('#11').animate({ 
        width: "13%",
        marginLeft: "605px",
		marginTop: "50px",
		opacity: 1,
      }, 220 );
	  }
	 else{
	 $('#11').show();
	$('#11').animate({ 
        width: "13%",
        marginLeft: "605px",
		marginTop: "50px",
		opacity: 0.4,
      }, 220 );
	  }
	
	if(id=='12'){
	$('#12').show();
	$('#12').animate({ 
        width: "13%",
        marginLeft: "755px",
		marginTop: "50px",
		opacity: 1,
      }, 200 );
	  }
	  else{
	  $('#12').show();
	$('#12').animate({ 
        width: "13%",
        marginLeft: "755px",
		marginTop: "50px",
		opacity: 0.4,
      }, 200 );
	  }

}


function affichageOff(id) 
{

	if(id=='1'){
	$('#1').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 150 );
	 }
	 else{
	 $('#1').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 150 );
	  }
	 
	 

	if(id=='2'){
	$('#2').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 200 );
	  }
	  else{
	  $('#2').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 200 );
	  }
	  
	  
	  
	 if(id=='3'){ 
	$('#3').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 210 );
	}
	else{
	$('#3').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 210 ); 
	  }
	
	if(id=='4'){
	$('#4').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 210 );
	  }
	  else{
	  	$('#4').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 210 );
	  }
	
	
	if(id=='5'){
	$('#5').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 200 );
	  }
	 else{
	 $('#5').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 200 );
	  }
	  
	 
	if(id=='6'){ 
	$('#6').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 150 );
	}
	else{
	$('#6').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 150 );
	}
	
	
	if(id=='7'){
	$('#7').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 150 );
	  
	 }
	 else{
	$('#7').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 150 );
	  }
	  
	
	if(id=='8'){
	$('#8').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 200 );
	  }
	else{ 
	$('#8').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 200 );
	} 
	
	
	if(id=='9'){
	$('#9').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 210 );
	 }
	else{
	$('#9').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 210 );
	}
	

	if(id=='10'){
	$('#10').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 210 );
	 }
	 else{
	 $('#10').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 210 );
	  }
	 
	if(id=='11'){ 
	$('#11').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 200 );
	  }
	  else{
	  $('#11').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 200 );
	  }
	  
	if(id=='12'){ 
	$('#12').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 1,
      }, 150 );
	}
	else{
	$('#12').animate({ 
        width: "22%",
        marginLeft: "38%",
		marginTop: "0px",
		opacity: 0,
      }, 150 );
	  }
	
		


}


$('#1').click( function() { 
	id=1;
	$('#1').animate({ 
			opacity: 1,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#3').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 0.4,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#6').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#7').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#11').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 0.4,
		  }, 300 );		  
		  
 } );
 
 
 $('#2').click( function() { 
	id=2;
	$('#1').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 1,
		  }, 300 );

	$('#3').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 0.4,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#6').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#7').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#11').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 0.4,
		  }, 300 );		  
		  
 } );


 $('#3').click( function() { 
	id=3;
	$('#1').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#3').animate({ 
			opacity: 1,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 0.4,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#6').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#7').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#11').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 0.4,
		  }, 300 );		  
		  
 } );
 
 
  $('#4').click( function() { 
	id=4;
	$('#1').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#3').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 1,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#6').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#7').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#11').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 0.4,
		  }, 300 );		  
		  
 } );
 
 
$('#5').click( function() { 
	id=5;
	$('#1').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#3').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 0.4,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 1,
		  }, 300 );

	$('#6').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#7').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#11').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 0.4,
		  }, 300 );		  
		  
 } );



$('#6').click( function() { 
	id=6;
	$('#1').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#3').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 0.4,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#6').animate({ 
			opacity: 1,
		  }, 300 );

	$('#7').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#11').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 0.4,
		  }, 300 );		  
		  
 } );


$('#7').click( function() { 
	id=7;	
	$('#1').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#3').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 0.4,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#6').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#7').animate({ 
			opacity: 1,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#11').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 0.4,
		  }, 300 );		  
		  
 } );


$('#8').click( function() { 
	id=8;
	$('#1').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#3').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 0.4,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#6').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#7').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 1,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#11').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 0.4,
		  }, 300 );		  
		  
 } );
 
 
 
 $('#9').click( function() { 
	id=9;
	$('#1').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#3').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 0.4,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#6').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#7').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 1,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#11').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 0.4,
		  }, 300 );		  
		  
 } );
 
 
$('#10').click( function() { 
	id=10;
	$('#1').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#3').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 0.4,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#6').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#7').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 1,
		  }, 300 );

	$('#11').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 0.4,
		  }, 300 );		  
		  
 } );
 


$('#11').click( function() { 
	id=11;
	$('#1').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#3').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 0.4,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#6').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#7').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#11').animate({ 
			opacity: 1,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 0.4,
		  }, 300 );		  
		  
 } );
 
 
 $('#12').click( function() { 
	id=12;
	$('#1').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
		  
	$('#2').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#3').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#4').animate({ 
			opacity: 0.4,
		  }, 300 );		

	$('#5').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#6').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#7').animate({ 
			opacity: 0.4,
		  }, 300 );
		  
	$('#8').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#9').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#10').animate({ 
			opacity: 0.4,
		  }, 300 );

	$('#11').animate({ 
			opacity: 0.4,
		  }, 300 );	

	$('#12').animate({ 
			opacity: 1,
		  }, 300 );		  
		  
 } );


$('#7').show();
$(".choix").mouseenter(function(){
      affichageOn();
    }).mouseleave(function(){
      affichageOff(id);
    });



