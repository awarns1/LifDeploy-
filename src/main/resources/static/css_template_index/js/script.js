jQuery(document).ready(function($) {
	'use strict';

	$('.progression-studios-slider').flexslider({
		slideshow: true,
		slideshowSpeed: 4000,
		animation: 'fade',
		animationSpeed: 400,
		directionNav: true,
		controlNav: true,
		prevText: '',
		nextText: ''
	});

	//Initialize Swiper
	var swiper = new Swiper('.swiper-container', {
		grabCursor: true,
		centeredSlides: true,
		pagination: {
			el: '.swiper-pagination',
			clickable: true
		},
		navigation: {
			nextEl: '.swiper-button-next',
			prevEl: '.swiper-button-prev'
		},
		autoplay: {
			delay: 5000,
			disableOnInteraction: false
		}
	});
});
