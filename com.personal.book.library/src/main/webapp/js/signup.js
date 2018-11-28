var signupApplication = angular.module('SignupApplication', []);

signupApplication.controller('SignupCtrl', ['$scope', '$rootScope', '$location', '$http', '$window', function($scope, $rootScope, $location, $http, $window) {
    
	$scope.user = {};
	
	$scope.signup = function() {
		
		$http.post('http://localhost:8090/com.personal.book.library/rest/book/library/v1/user', $scope.user)
		 .then(function(response){
			 
			 if($scope.user.twoFactorAuthEnabled && response.data > 0) {
				 $window.location.href = '/com.personal.book.library/rest/book/library/v1/otp/qrcode/' + response.data + '.png';
			 }
			 
	     }).catch(function(response) {
	    	 	// TODO: Fill this code block!
	     });
	}
	
}]);
