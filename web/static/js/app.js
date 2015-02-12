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
.controller('ProjectsCtrl', ['$scope', function($scope) {
  $scope.projects = [{'description': 'A project exploring some colors found in a color thesaurus.', 'author': 'Vishesh Gupta', 'started': '31 January 2015 14:32:00', 'path': 'colors', 'title': 'Colors!', 'tags': ['grouping']}, {'description': 'Which majors are popular at Stanford by gender?', 'author': 'Vishesh Gupta', 'started': '21 January 2015 14:38:00', 'path': 'majors', 'title': 'Stanford Majors', 'tags': ['trends']}]
}])
.conroller('ProjectCtrl', ['$scope', '$routeParams', function($scope, $routeParams) {
  $scope.project = {
    "title": "Colors!",
    "path" : "colors",
    "author": "Vishesh Gupta",
    "tags": ["grouping"],
    "started": "31 January 2015 14:32:00",
    "description" : "A project exploring some colors found in a color thesaurus.",
    "posts": [
      {"title":"About the Dataset",
       "author":"Vishesh Gupta",
       "tags":["colors"],
       "summary":"Check out this data set of color values taken from an author's color thesarus. The main takeaways - how do you group colors? How do you determine if there are missing/superfluous colors in the data set?",
       "created":"2015 February 12 00:48:22",
       "url": "/posts/colors/about-the-dataset"},
      {"url": "/posts/colors/colors"}
    ]
  }
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
