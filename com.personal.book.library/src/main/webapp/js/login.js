var authenticationApplication =  angular.module('AuthenticationApplication', ['vcRecaptcha']);

authenticationApplication.controller('AuthenticationCtrl', ['$scope', '$rootScope', '$location', '$http', '$window', 'vcRecaptchaService', function($scope, $rootScope, $location, $http, vcRecaptchaService) {
    
	$scope.username = '';
	$scope.password = '';
	$scope.recaptchaResponse = '';
	$scope.captchaSiteKey = '6LcB4XgUAAAAAHIwxZZcBp4Uzg68G1qQYgGhqlut';
	$scope.otpKey = '';
	
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
		
		$scope.attemptLogin();
	}
	
	
	$scope.attemptLogin = function() {
		
		var authenticationRequest = 'username=' + $scope.username + '&password=' + $scope.password + '&g-recaptcha-response=' + $scope.recaptchaResponse;
		
		$http.post('http://localhost:8090/com.personal.book.library/login', authenticationRequest, {headers : headers})
		 .then(function(response){
			 
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
         if(vcRecaptchaService) {
        	 vcRecaptchaService.reload($scope.widgetId);
         }
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
