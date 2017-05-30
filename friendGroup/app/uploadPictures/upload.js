'use strict';

angular.module('myApp.upload', ['ngRoute'])

    .config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/upload', {
            templateUrl: 'uploadPictures/upload.html',
            controller: 'UploadCtrl'
        });
    }])
    .controller('UploadCtrl', [function() {

    }]);