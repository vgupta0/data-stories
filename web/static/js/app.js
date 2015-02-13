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
  .when('/post/:name', {
    templateUrl: function(urlparams) { return 'posts/'+urlparams["name"]+'.html';},
    controller: 'PostCtrl'
  })
  .otherwise( {
    redirectTo: '/'
  })
}])
.controller('MainCtrl', ['$scope', function($scope) {
  $scope.posts = [
    {title: "Sample Post Title",
     author: "Vishesh Gupta",
     tags: ["test-category", "test-category2"],
     summary: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla eu metus ac lectus lobortis placerat eu sed urna. Aenean blandit arcu non est aliquet, quis molestie erat pellentesque. Aenean dapibus vel neque sodales tristique. Praesent in placerat quam, ac placerat nibh. Nunc sit amet hendrerit lacus, eu efficitur sem. Fusce sed condimentum sem. Nulla facilisi. Vivamus luctus nec erat ut consectetur. ",
     images: undefined
    }
  ]
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
