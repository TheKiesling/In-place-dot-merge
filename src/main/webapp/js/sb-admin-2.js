(function($) {
  "use strict"; // Start of use strict

  // Toggle the side navigation
  $("#sidebarToggle, #sidebarToggleTop").on('click', function(e) {
    $("body").toggleClass("sidebar-toggled");
    $(".sidebar").toggleClass("toggled");
    if ($(".sidebar").hasClass("toggled")) {
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Close any open menu accordions when window is resized below 768px
  $(window).resize(function() {
    if ($(window).width() < 768) {
      $('.sidebar .collapse').collapse('hide');
    };
    
    // Toggle the side navigation when window is resized below 480px
    if ($(window).width() < 480 && !$(".sidebar").hasClass("toggled")) {
      $("body").addClass("sidebar-toggled");
      $(".sidebar").addClass("toggled");
      $('.sidebar .collapse').collapse('hide');
    };
  });

  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .sidebar').on('mousewheel DOMMouseScroll wheel', function(e) {
    if ($(window).width() > 768) {
      var e0 = e.originalEvent,
        delta = e0.wheelDelta || -e0.detail;
      this.scrollTop += (delta < 0 ? 1 : -1) * 30;
      e.preventDefault();
    }
  });

  // Scroll to top button appear
  $(document).on('scroll', function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(e) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    e.preventDefault();
  });
  
  //Evento del botón que me devuelve el listado de actores
  $("#btn-search-actors").click(function(){
		//alert("The button was clicked 1");
				
		$.ajax( {
			
			type: "GET",
			url: '/HelloWorld/HelloServlet',
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlActorsList = '<ul>';
				$.each(data.actores, function(i,item){
					  htmlActorsList += '<li>' + item + '</li>';
				});
				htmlActorsList += '</ul>';
				$('#div-listado-actores').html("");
				$('#div-listado-actores').append(htmlActorsList);
			}
		} );
		
		
	});
	
	//Evento del botón que me devuelve el listado de películas de un determinado actor
	$("#btn-search-movies-by-actor").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/HelloWorld/MoviesByActor?actor_name=' + $('#txt-actor').val(),
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlMovieList = '<ul>';
				$.each(data.peliculas, function(i,item){
					  htmlMovieList += '<li>' + item + '</li>';
				});
				htmlMovieList += '</ul>';
				$('#div-listado-actores').html("");
				$('#div-listado-actores').append(htmlMovieList);
			}
		} );
		
		
	});

	//Evento del botón que me devuelve el listado de películas de un determinado actor
	$("#btn-search-places").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/In/mergePlace?price_range=' + $('#price').val() + '&Addres=' + $('#addres').val() + '&Caracteristic=' + $('#caracteristics').val() + '&Categorie=' + $('#categorie').val(),
			success: function(data) {
				//alert("Result" + data.resultado);
				var htmlPlacesList ="";
				var cont = 0;
				$.each(data.lugares, function(i,item){
						if (cont == 0)
							htmlPlacesList +='<h3><b>' + item + '</h3></b><br>';
						if (cont == 1)
							htmlPlacesList +='<p><b>Ubicacion:</b>' + item + '<br>';
						if (cont == 2)
							htmlPlacesList +='<b>Precio:</b>' + item + '<br>';
						if (cont == 3)
							htmlPlacesList +='<b>Categoria:</b>' + item + '<br>';
						if (cont == 4){
							htmlPlacesList +='<b>Tipo:</b>' + item + '<br></p><br><br>';
							cont = 0;
							htmlPlacesList +='<br><br>';
						}
						else cont++;
						
				});
				$('#reco').html("");
				$('#reco').append(htmlPlacesList);
			}
		} );

		$.ajax( {
			
			type: "GET",
			url: '/In/mergePlace?price_range=' + $('#price').val() + '&Addres=' + $('#addres').val() + '&Caracteristic=' + $('#caracteristics').val() + '&Categorie=' + $('#categorie').val(),
			success: function(dataa) {
				//alert("Result" + data.resultado);
				var htmlPlacesList ="";
				var cont = 0;
				$.each(dataa.a, function(i,item){
						if (cont == 0)
							htmlPlacesList +='<h3><b>' + item + '</h3></b><br>';
						if (cont == 1)
							htmlPlacesList +='<p><b>Ubicacion:</b>' + item + '<br>';
						if (cont == 2)
							htmlPlacesList +='<b>Precio:</b>' + item + '<br>';
						if (cont == 3)
							htmlPlacesList +='<b>Categoria:</b>' + item + '<br>';
						if (cont == 4){
							htmlPlacesList +='<b>Tipo:</b>' + item + '<br></p><br><br>';
							cont = 0;
							htmlPlacesList +='<br><br>';
						}
						else cont++;
						
				});
				$('#recoa').html("");
				$('#recoa').append(htmlPlacesList);

			}
		} );
		

		$.ajax( {
			
			type: "GET",
			url: '/In/mergePlace?price_range=' + $('#price').val() + '&Addres=' + $('#addres').val() + '&Caracteristic=' + $('#caracteristics').val() + '&Categorie=' + $('#categorie').val(),
			success: function(datac) {
				//alert("Result" + data.resultado);
				var htmlPlacesList ="";
				var cont = 0;
				$.each(datac.c, function(i,item){
						if (cont == 0)
							htmlPlacesList +='<h3><b>' + item + '</h3></b><br>';
						if (cont == 1)
							htmlPlacesList +='<p><b>Ubicacion:</b>' + item + '<br>';
						if (cont == 2)
							htmlPlacesList +='<b>Precio:</b>' + item + '<br>';
						if (cont == 3)
							htmlPlacesList +='<b>Categoria:</b>' + item + '<br>';
						if (cont == 4){
							htmlPlacesList +='<b>Tipo:</b>' + item + '<br></p><br><br>';
							cont = 0;
							htmlPlacesList +='<br><br>';
						}
						else cont++;
						
				});
				$('#recoc').html("");
				$('#recoc').append(htmlPlacesList);
			}
		} );
		
	});

//Evento del botón que me devuelve el listado de películas de un determinado actor
	$(document).ready(function(){
					
		$.ajax( {
			
			type: "GET",
			url: '/In/FavPlaces',
			success: function(data) {
				//alert("Result" + data.resultado);
				var htmlPlacesList ="";
				var cont = 0;
				$.each(data.lugares, function(i,item){
						if (cont == 0)
							htmlPlacesList +='<h3><b>' + item + '</h3></b><br>';
						if (cont == 1)
							htmlPlacesList +='<p><b>Ubicacion:</b>' + item + '<br>';
						if (cont == 2)
							htmlPlacesList +='<b>Precio:</b>' + item + '<br>';
						if (cont == 3)
							htmlPlacesList +='<b>Categoria:</b>' + item + '<br>';
						if (cont == 4){
							htmlPlacesList +='<b>Tipo:</b>' + item + '<br></p><br><br>';
							cont = 0;
						}
						else cont++;
						
				});
				$('#favs').html("");
				$('#favs').append(htmlPlacesList);
			}
		} );
	
	
	});
  //Evento del botón que me devuelve el listado de actores
  $("#btn-places").click(function(){
		//alert("The button was clicked 1");
				
		$.ajax( {
			
			type: "GET",
			url: '/HelloWorld/Places',
			success: function(data) {
				//alert("Result" + data.resultado);
			    var htmlActorsList = '<ul>';
				$.each(data.actores, function(i,item){
					  htmlActorsList += '<li>' + item + '</li>';
				});
				htmlActorsList += '</ul>';
				$('#div-listado-actores').html("");
				$('#div-listado-actores').append(htmlActorsList);
			}
		} );
		
		
	});


	//Evento del botón que creara una nueva pelicula
	$("#btn-insert-movie").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/HelloWorld/SaveMovieServlet?title=' + $('#txt-movie-title').val() + '&release_year=' + $('#txt-movie-release').val() + '&tagline=' + $('#txt-movie-tagline').val() ,
			success: function(data) {
			    alert("Resultado: " + data.resultado);
			}
		} );
		
		
	});

	//Evento del botón que creara un nuevo lugar
	$("#btn-insert-place").click(function(){
				
		$.ajax( {
			
			type: "GET",
			url: '/In/SavePlaceServlet?name=' + $('#place').val() + '&price_range=' + $('#price').val() + '&Addres=' + $('#addres').val() + '&Caracteristic=' + $('#caracteristics').val() + '&Categorie=' + $('#categorie').val() + '&Rating=' + $('#rating').val(),
			success: function(data) {
			    alert("Resultado: " + data.resultado);
			}
		} );
		
		
	});
})(jQuery); // End of use strict
