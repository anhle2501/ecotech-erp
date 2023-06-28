
$('.timer').prop('disabled', true);

setTimeout(function() { 
  $('.timer').prop('disabled', false)
}, 500);