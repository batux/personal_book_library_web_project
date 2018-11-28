var totpApplication = angular.module('SMSApplication', []);

totpApplication.controller('SMSCtrl', ['$scope', '$rootScope', '$location', '$http', '$window', function($scope, $rootScope, $location, $http, $window) {
    
	$scope.otpCode = '';
	
	$scope.validate = function() {
		
		$http.post('http://localhost:8090/com.personal.book.library/validate?sms_code=' + $scope.otpCode)
		.then(function(response){
			 
			// TODO: Refactor here!
			 if(response.status) {
				 var headers = response.headers();
				 
				 var redirectPageKey = headers['auth_redirect'];
				 var rootPath = 'http://localhost:8090/com.personal.book.library/';
				 
				 if(redirectPageKey == 'TOTP_AUTHENTICATION') {
					 rootPath += 'totp_auth.html';
				 }
				 else if(redirectPageKey == 'SMS_AUTHENTICATION') {
					 rootPath += 'sms_auth.html';
				 }
				 else if(redirectPageKey == 'AUTHENTICATION_COMPLETED') {
					rootPath += 'main.html';
				 }
				 
				 window.location.href = rootPath;
			 }
			 console.log(response);
	     });
	}
	
}]);
