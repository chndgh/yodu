'use strict';

angular.module('myApp.cm', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/createMessage', {
            templateUrl: 'message/createMessage.html'
        });
    }])
    .controller('CMCtrl', ['$scope', '$http', 'FileUploader', function($scope, $http, FileUploader) {

        var uploader = $scope.uploader = new FileUploader({
            url: 'http://localhost:9000/message/postNewMessage'
        });

        uploader.onBeforeUploadItem = function (item) {
            item.alias = item.file.name;
            item.formData=[ {id:666,content:$scope.content}];
        };

        uploader.onAfterAddingAll = function(addedFileItems) {
            angular.forEach(addedFileItems,function (item) {
                console.log(item.file.name);
                item.alias = item.file.name;
            });
            console.log(uploader.formData);
            console.info('onAfterAddingAll', addedFileItems);
        };

        $scope.submit = function(){
            // $scope.request = {message:$scope.message,photos:$scope.photos};
            // $http.post("http://localhost:8080/message/create",$scope.request);
            uploader.uploadAll();
        };


        // $scope.imageUpload = function(event){
        //     var images = event.target.files; //images list object
        //     for (var i = 0; i < images.length; i++) {
        //         var image = images[i];
        //         var photo = {};
        //         photo.name = image.name;
        //         $scope.photos.push(photo);
        //         var reader = new FileReader();
        //         reader.onload = $scope.imageIsLoaded;
        //         reader.readAsDataURL(image);
        //     };
        // };
        //
        // $scope.imageIsLoaded = function(e){
        //     $scope.$apply(function(){
        //         $scope.stepsModel.push(e.target.result);
        //     })
        // };
    }]);