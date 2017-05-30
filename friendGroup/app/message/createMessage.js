'use strict';

angular.module('myApp.cm', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/createMessage', {
            templateUrl: 'message/createMessage.html',
            controller: 'CMCtrl'
        });
    }])
    .controller('CMCtrl', ['$scope', '$http',function($scope, $http) {
        $scope.stepsModel = [];
        $scope.message = {};
        $scope.photos = [];
        $scope.request = {};
        $scope.images = [];

        $scope.imageUpload = function(files){
            // var images = event.target.files; //images list object
            for (var i = 0; i < files.length; i++) {
                var image = files[i];
                console.log(image);
                var photo = {};
                photo.name = image.name;
                $scope.photos.push(photo);
                $scope.images.push(image);
                var reader = new FileReader();
                reader.onload = $scope.imageIsLoaded;
                reader.readAsDataURL(image);
            };
        };

        $scope.imageIsLoaded = function(e){
            $scope.$apply(function(){
                $scope.stepsModel.push(e.target.result);
            })
        };

        $scope.submit = function(){
            console.log("111111111111111111111");
            console.log($scope.images );
            $scope.message.content = $scope.content;
            $scope.request = {message:$scope.message,photos:$scope.photos,images:$scope.images};
            $http.post("http://localhost:9000/message/create",$scope.request);
        };
    }]);