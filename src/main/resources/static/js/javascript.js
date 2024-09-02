
// disable button a while
$('.timer').prop('disabled', true);

setTimeout(function() { 
  $('.timer').prop('disabled', false)
}, 500);


// select2
$(document).ready(function() {
    $('.customer-select-search').select2({
		theme: 'bootstrap-5'
	});
});

$(document).ready(function() {
    $('.product-select-search').select2({
		theme: 'bootstrap-5'
	});
});

$(document).ready(function() {
    $('.payment-type-select-search').select2({
		theme: 'bootstrap-5'
	});
});

$(document).ready(function() {
	$('.customer-select-search').select2({
		theme: 'bootstrap-5'
	});
});

$(document).ready(function() {
	$('.region-select-search').select2({
		theme: 'bootstrap-5'
	});
});

$(document).ready(function() {
	$('.role-select-search').select2({
		theme: 'bootstrap-5'
	});
});
// add button new customer in new order

	function handleAddButtonNewOrder(e){
		/*$('.option-product-new-order').chosen();*/
		/*window.location.href="/"*/
		event.preventDefault();
		console.log("test");
	}
  

