$(document).ready(function() {
	$('.eBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		 /*console.log(href);*/
		$.get(href, function(produit, status) {
			$('.myForms #id').val(produit.id);
			$('.myForms #productionNat').val(produit.productionNat);
			$('.myForms #nomProduit').val(produit.nomProduit);
			$('.myForms #unite').val(produit.unite);
			$('.myForms #prixUnitaire').val(produit.prixUnitaire);
			$('.myForms #stockGlobal').val(produit.stockGlobal);
		});
		$('.myForms #editCatProd').modal();
	});
	
	$('.delBtn').on('click', function(event) {
		event.preventDefault();
		var href = $(this).attr('href');		
		$('#deleteProdBse #delProd').attr('href',href);
		$('#deleteProdBse').modal();
	});
});