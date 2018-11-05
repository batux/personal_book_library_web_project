var bookLibraryApplication =  angular.module('BookLibraryApp', []);

bookLibraryApplication.controller('BookLibraryCtlr', ['$scope', '$rootScope', '$location', '$http', '$interval', function($scope, $rootScope, $location, $http, $interval) {
    
	$scope.submitted = false;
	
	$scope.book = {};
	
	$scope.userBooks = [];
	
	$scope.categoryList = [];
	
	$scope.likeDegreeList = [];
	
	
	$scope.saveBook = function(isValid) {
		
		$scope.submitted = true;
		
		if(isValid) {
			
			setCategoryToBook();
			
			$http.post('http://localhost:8090/com.personal.book.library/rest/book/library/v1/book', $scope.book)
			 .then(function(response){
				 
				 if(response) {
					 clearBookRegisterForm();
					 loadSavedBooksOfUser();
					 $('#successMessageModal').modal('show');
					 $scope.submitted = false;
				 }
		     })
		     .catch(function(response) {
		    	 	$('#successErrorModal').modal('show');
		     });
		
		}
	}
	
	var clearBookRegisterForm = function() {
		$scope.book = {};
	}
	
	var setCategoryToBook = function() {
		
		for(var i=0; i < $scope.categoryList.length; i++) {
			if($scope.categoryList[i].id == $scope.book.category) {
				$scope.book.category = $scope.categoryList[i];
				break;
			}
		}
	}
	
	var saveBookDraft = function() {
		
		var categoryId = $scope.book.category;
		setCategoryToBook();
		$http.post('http://localhost:8090/com.personal.book.library/rest/book/library/v1/book/draft', angular.copy($scope.book));
		$scope.book.category = categoryId;
	}
	
	var loadBookDraftIfExist = function() {
		
		$http.get('http://localhost:8090/com.personal.book.library/rest/book/library/v1/book/draft').then(function(response){
			$scope.book = response.data || {};
			if($scope.book.category) {
				$scope.book.category = $scope.book.category.id;
			}
	    });
	}
	loadBookDraftIfExist();
	
	var loadSavedBooksOfUser = function() {
		
		$http.get('http://localhost:8090/com.personal.book.library/rest/book/library/v1/book/list').then(function(response){
			$scope.userBooks = response.data || [];
	    });
	}
	
	loadSavedBooksOfUser();
	
	var loadLikeDegreeList = function() {

		$http.get('http://localhost:8090/com.personal.book.library/rest/book/library/v1/likedegree/list').then(function(response){
			$scope.likeDegreeList = response.data || [];
	    });
	}
	
	loadLikeDegreeList();
	
	var loadCategoryList = function() {

		$http.get('http://localhost:8090/com.personal.book.library/rest/book/library/v1/categories').then(function(response){
			$scope.categoryList = response.data || [];
	    });
	}
	
	loadCategoryList();
	
	
	var loadUserSummary = function() {
		
		$http.get('http://localhost:8090/com.personal.book.library/rest/book/library/v1/user/summary').then(function(response){
			$scope.userSummary = response.data || {};
	    });
	}
	
	loadUserSummary();
	
	$interval(saveBookDraft, 30000);
	
}]);



