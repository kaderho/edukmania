
function main() {

function submitForm(){
  var niveau = $(':radio[name="btnRadio"]:checked').val();
  var clas
}
(function () {
   'use strict';
   
   $('#niveauForm').submit(function(event){
    //annulation de soumission du formulaire
    event.preventDefault();
    submitForm();
   })

  	$('a.page-scroll').click(function() {
        if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname) {
          var target = $(this.hash);
          target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
          if (target.length) {
            $('html,body').animate({
              scrollTop: target.offset().top - 40
            }, 900);
            return false;
          }
        }
      });

    // vue du menu par rapport à la taille
    $(window).bind('scroll', function() {
        var navHeight = $(window).height() - 500;
        if ($(window).scrollTop() > navHeight) {
            $('.navbar-default').addClass('on');
        } else {
            $('.navbar-default').removeClass('on');
        }
    });

    $('body').scrollspy({ 
        target: '.navbar-default',
        offset: 80
    });
	
	
	// Hide nav on click
  $(".navbar-nav li a").click(function (event) {
    // check if window is small enough so dropdown is created
    var toggle = $(".navbar-toggle").is(":visible");
    if (toggle) {
      $(".navbar-collapse").collapse('hide');
    }
  });
	//popup quand uncun bouton n'est coché
  var inputs = document.getElementsByTagName('input');
  var inputsLength = inputs.length;
       for (var i = 0 ; i < inputsLength ; i++){
        if (inputs[i].type == 'radio' && (inputs[0].checked==false&&inputs[1].checked==false&&inputs[2].checked==false&&inputs[3].checked==false&&inputs[4].checked==false)) {
            $(function (){
            $("#erreur").popover({placement:'top', trigger:''});
            });
        }
       }
//test de button radio
        function check() {

     var primaire = document.getElementById('primaire');
      var college = document.getElementById('college');
      var lycee = document.getElementById('lycee');
      var superieur = document.getElementById('superieur');
      var autre = document.getElementById('autre');
      var inputs = document.getElementsByTagName('input'),
      inputsLength = inputs.length;

      for (var i = 0 ; i < inputsLength ; i++) {
      if (inputs[i].type == 'radio' && inputs[i].checked==true && i == 0) {
          primaire.style.display = 'block';
          college.style.display = 'none';
          lycee.style.display = 'none';
          superieur.style.display = 'none';
          autre.style.display = 'none';
      }
       if (inputs[i].type == 'radio' && inputs[i].checked==true && i == 1) {
          primaire.style.display = 'none';
          college.style.display = 'block';        
          lycee.style.display = 'none';
          superieur.style.display = 'none';
          autre.style.display = 'none';
      }
       if (inputs[i].type == 'radio' && inputs[i].checked ==true && i == 2) {
          primaire.style.display = 'none';
          college.style.display = 'none';
          lycee.style.display = 'block';
          superieur.style.display = 'none';
          autre.style.display = 'none';
      }
      if (inputs[i].type == 'radio' && inputs[i].checked ==true && i == 3) {
          primaire.style.display = 'none';
          college.style.display = 'none';
          lycee.style.display = 'none';
          superieur.style.display = 'block';
          autre.style.display = 'none';
      }
      if (inputs[i].type == 'radio' && inputs[i].checked ==true && i == 4) {
          primaire.style.display = 'none';
          college.style.display = 'none';
          lycee.style.display = 'none';
          superieur.style.display = 'none';
          autre.style.display = 'block';
      }
    }
  }
  	// Pretty Photo
	$("a[rel^='prettyPhoto']").prettyPhoto({
		social_tools: false
	});	

}());


}
main();