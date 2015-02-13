angular.module('datastories', ['ngRoute', 'angularMoment'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider
  .when('/', {
    templateUrl: 'templates/main.html',
    controller: 'MainCtrl'
  })
  .when('/projects', {
    templateUrl: 'templates/projects.html',
    controller: 'ProjectsCtrl'
  })
  .when('/projects/:name', {
    templateUrl: 'templates/project.html',
    controller: 'ProjectCtrl'
  })
  .when('/about', {
    templateUrl: 'templates/about.html',
    controller: 'AboutCtrl'
  })
  .when('/post/:project/:post', {
    templateUrl: function(urlparams) {
      return 'post/' + urlparams["project"] + "/" + urlparams["post"] + '.html';
    },
    controller: 'PostCtrl'
  })
  .otherwise( {
    redirectTo: '/'
  })
}])
.controller('MainCtrl', ['$scope', '$http', function($scope, $http) {
  $scope.posts = [];
  $scope.$on('$routeChangeSuccess', function(event, next, current) {
    $http.get('/recentposts')
    .success(function(data) {
      $scope.posts = data["posts"];
    })
    .error(function(data, status) {
      console.log(data);
    });
  });
}])
.controller('AboutCtrl', ['$scope', function($scope) {

}])
.controller('PostCtrl', ['$scope', function($scope) {

}])
.controller('ProjectsCtrl', ['$scope', '$http', function($scope, $http) {
  $scope.projects = [];
  $scope.$on('$routeChangeSuccess', function(event, next, current) {
    $http.get('/projects')
    .success(function(data) {
      $scope.projects = data["projects"];
    })
    .error(function(data, status) {
      console.log(data);
    });
  });
}])
.controller('ProjectCtrl', ['$scope', '$routeParams', '$http', function($scope, $routeParams, $http) {
  $scope.project = undefined;
  $scope.$on('$routeChangeSuccess', function(event, next, current) {
    $http.get('/projects/' + $routeParams["name"])
    .success(function(data) {
      $scope.project = data;
    })
    .error(function(data, status) {
      console.log(data);
    });
  });
}])

.directive('goClick', function ( $location ) {
  return function ( scope, element, attrs ) {
    var path;

    attrs.$observe( 'goClick', function (val) {
      path = val;
    });

    element.bind( 'click', function () {
      scope.$apply( function () {
        $location.path( path );
      });
    });
  };
})


;
