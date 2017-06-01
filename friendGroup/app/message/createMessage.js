'use strict';

angular.module('myApp.cm', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/createMessage', {
            templateUrl: 'message/createMessage.html',
            controller: 'CMCtrl'
        });
    }])
    .controller('CMCtrl', ['$scope', '$http', 'FileUploader', function($scope, $http, FileUploader) {
        $scope.stepsModel = [];
        $scope.message = {};
        $scope.photos = [];
        $scope.request = {};

        var uploader = $scope.uploader = new FileUploader({
            url: 'http://localhost:8080/message/savePhoto',
            formData:[{id:666}]
        });

        uploader.onBeforeUploadItem = function (item) {
            item.alias = "name";
            console.info('onBeforeUploadItem',item);
        };

        $scope.imageUpload = function(event){
            var images = event.target.files; //images list object
            for (var i = 0; i < images.length; i++) {
                var image = images[i];
                var photo = {};
                photo.name = image.name;
                $scope.photos.push(photo);
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
            $scope.message.content = $scope.content;
            $scope.request = {message:$scope.message,photos:$scope.photos};
            $http.post("http://localhost:8080/message/create",$scope.request);
            uploader.uploadAll();
        };
    }]);