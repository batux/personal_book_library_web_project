var authenticationApplication =  angular.module('AuthenticationApplication', ['vcRecaptcha']);

authenticationApplication.controller('AuthenticationCtrl', ['$scope', '$rootScope', '$location', '$http', '$window', 'vcRecaptchaService', function($scope, $rootScope, $location, $http, vcRecaptchaService) {
    
	$scope.username = '';
	$scope.password = '';
	$scope.recaptchaResponse = '';
	$scope.captchaSiteKey = '6LcB4XgUAAAAAHIwxZZcBp4Uzg68G1qQYgGhqlut';
	
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
		
		if($scope.recaptchaResponse === '') {
            alert("Please resolve the captcha and submit!");
            return;
        }
		
		var authenticationRequest = 'username=' + $scope.username + '&password=' + $scope.password + '&g-recaptcha-response=' + $scope.recaptchaResponse;
		
		$http.post('http://localhost:8090/com.personal.book.library/login', authenticationRequest, {headers : headers})
		 .then(function(response){
			 
			 if(response.status == 200) {
				 var redirectUrl = 'http://localhost:8090/com.personal.book.library/main.html';
				 window.location.href = redirectUrl;
			 }
	     }).catch(function(response) {
	    	 	$scope.unsuccessfullAuthenticationWarningVisibility = true;
	    	 	$scope.captchaExpirationCallback();
	     });
	}
	
	$scope.setCaptchaResponse = function (response) {
         console.info('Response available');
         $scope.recaptchaResponse = response;
    }
     
    $scope.setWidgetId = function (widgetId) {
         console.info('Created widget ID: %s', widgetId);
         $scope.widgetId = widgetId;
    }
     
    $scope.captchaExpirationCallback = function() {
         console.info('Captcha expired. Resetting response object');
         vcRecaptchaService.reload($scope.widgetId);
         $scope.recaptchaResponse = null;
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
