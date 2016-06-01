var reEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
var reName = /^\w+$/;

$('#submitBtn').on('click', function(event) {
	if ($('#user').val().length < 5) {
		$('#massage').text('Name is too short, it must has 5 characters, at least!');
		return false;
	} else if (!reName.test($('#user').val())) {
		$('#massage').text('Username must contain only letters, numbers and underscores!');
		return false;
	} else if (!reEmail.test($('#mail').val())) {
		$('#massage').text('Email is not correct!!!');
		return false;
	} else if ($('#password').val().length < 8) {
		$('#massage').text('Password is too short, it must has 8 characters, at least!');	
		return false;
	} else if ($('#user').val() == $('#password').val()) {
		$('#massage').text('Password must be different from username!');
		return false;
	} else if ($('#password').val() != $('#passwordConf').val()) {
		$('#massage').text('Passwords do not match!!!');
		return false;
	} else{
		$('#massage').text('');
	}
});