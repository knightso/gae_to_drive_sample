(function() {
	angular.module("demo", []);
})();

(function() {
	function UploadController($http) {
		var self = this;
		
		this.uploadReady = false;
		
		this.setUploadUrl = function() {
			$http.get("/blob/upload").then(function(response) {
				self.uploadUrl = response.data.uploadUrl;
				self.uploadReady = true;
			}, function(response) {
				console.log(response);
			});
		}
	}
	
	angular.module("demo").controller("UploadController", ["$http", UploadController]);
})();
