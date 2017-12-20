/**
 * Created by vishruthkrishnaprasad.
 */
'use strict';
angular.module('app');
app.controller('HomeController', HomeController);

 function HomeController($state, UserService) {
     let vm = this;
     vm.login = login;

     /**
      * Authenticates user to Dashboard.
      * @param {JSON} user - Contains username and password.
      */

     function login(user) {

         // If user has not entered any text in the form
         if (!user) {
             vm.log = "Please enter username and password";
         }
         else {
             UserService.login(user)
                 .then(function success() {
                     // Authentication successful; takes the user to dashboard
                         $state.go('dashboard');
                     },
                     function error() {
                     // Incorrect Credentials entered
                         vm.log = "Invalid Credentials";
                     });
         }
     }
 }

