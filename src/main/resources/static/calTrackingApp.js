(function () {
  'use strict';
  angular
      .module('CalTrackingApp',['ngMaterial', 'ngMessages'])
      .controller('CalTrackingSubmitCtrl', ['$scope', '$http', function($scope, $http) {
        $scope.calTracking = function () {
            console.log('Tracking: ' + $scope.calories + ' calories for ' + $scope.ctrl.selectedItem.value);

            var calTrackingPayload = {
				userId : 1,
				foodId : $scope.ctrl.selectedItem.value,
                calories : $scope.calories
            }

            var res = $http.put('http://localhost:8080/calTracking/public/trackCalorie',calTrackingPayload);
            
            res.success(function(data, status, headers, config) {
                $scope.message = data;
            });
            
            res.error(function(data, status, headers, config) {
                alert( "failure message: " + JSON.stringify({data: data}));
            });		
        }
      }])
      .controller('CalTrackingCtrl', CalTrackingCtrl);

  function CalTrackingCtrl ($timeout, $q, $log, $http) {
    var self = this;

    self.allFoods = null;
    self.simulateQuery = false;
    self.isDisabled    = false;

    self.querySearch   = querySearch;
    self.selectedItemChange = selectedItemChange;
    self.searchTextChange   = searchTextChange;

    // ******************************
    // Internal methods
    // ******************************

    function querySearch (query) {
        if (self.allFoods == null) {
            self.allFoods = [];
            $http.get('http://localhost:8080/calTracking/public/getFoods')
            .then(function (foods) {
                for (var i=0; i<foods.data.length; i++) {
                    var food = foods.data[i];
                    var f = {value: food.id, display: angular.uppercase(food.name) +  ' (' + food.calories + ')', valueForSearch: angular.lowercase(food.name) }
                    self.allFoods.push(f);
                }

                return self.allFoods;  
            });
        }

        var results = query ? self.allFoods.filter( createFilterFor(query) ) : self.allFoods,
          deferred;
        if (self.simulateQuery) {
            deferred = $q.defer();
            $timeout(function () { deferred.resolve( results ); }, Math.random() * 1000, false);
            return deferred.promise;
        } else {
            return results;
        }
    }

    function searchTextChange(text) {
      $log.info('Text changed to ' + text);
    }

    function selectedItemChange(item) {
      $log.info('Item changed to ' + JSON.stringify(item));
    }

    /**
     * Create filter function for a query string
     */
    function createFilterFor(query) {
      var lowercaseQuery = angular.lowercase(query);

      return function filterFn(food) {
        return (food.valueForSearch.indexOf(lowercaseQuery) === 0);
      };

    }
  }
})();