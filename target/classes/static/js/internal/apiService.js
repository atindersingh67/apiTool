/**
 * apiService : dataservice factory 
 * 
 * @author Atinder
 * @version 1.0
 * @since 2018-08-31
 */
angular
    .module('apiApp')
    .factory('apiService', apiService);

apiService.$inject = ['$http', '$log'];

function apiService($http, $log) {
    return {
    	generateAPI:generateAPI
    };

  
    
    function generateAPI(obj) {
        return $http.post('/create',obj)
            .then(generateAPIComplete)
            .catch(generateAPIFailed);
        
        function generateAPIComplete(response) {
       	 return response.data;
       }
        function generateAPIFailed(error) {
        	$log.error('XHR Failed for generateAPI.' + error.data);
        }
       
   }
    
   
   
}