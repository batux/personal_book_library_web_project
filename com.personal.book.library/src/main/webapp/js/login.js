var creditApplication =  angular.module('AuthenticationApplication', []);

creditApplication.controller('AuthenticationCtrl', ['$scope', '$rootScope', '$location', '$http', '$window', function($scope, $rootScope, $location, $http) {
    
	$scope.username = '';
	$scope.password = '';
	
	$scope.unsuccessfullAuthenticationWarningVisibility = false;
	$scope.usernameWarningVisibility = false;
	$scope.passwordWarningVisibility = false;
	
	var headers = {'Content-Type': 'application/x-www-form-urlencoded'};
	
	$scope.login = function() {
		
		$scope.unsuccessfullAuthenticationWarningVisibility = false;
		
		var validForm = validateLoginForm();
		if(!validForm) {
			return;
		}
		
		var authenticationRequest = 'username=' + $scope.username + '&password=' + $scope.password;
		
		$http.post('http://localhost:8090/com.personal.book.library/login', authenticationRequest, {headers : headers})
		 .then(function(response){
			 
			 if(response.status == 200) {
				 var redirectUrl = 'http://localhost:8090/com.personal.book.library/main.html';
				 window.location.href = redirectUrl;
			 }
	     }).catch(function(response) {
	    	 	$scope.unsuccessfullAuthenticationWarningVisibility = true;
	     });
	}
	
	var validateLoginForm = function() {
		
		var validForm = true;
		
		if(!$scope.username) {
			$scope.usernameWarningVisibility = true;
			validForm = false;
		}
		if(!$scope.password) {
			$scope.passwordWarningVisibility = true;
			validForm = false;
		}
		
		return validForm;
	}
	
}]);
