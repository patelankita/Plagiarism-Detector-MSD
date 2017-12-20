/**
 * Created by rosyp on 11/20/2017.
 */
'use strict';
angular.module('app');
app.factory('UserService', UserService);
let bcrypt = require("bcrypt-nodejs");

function UserService($q) {

    let api = {
        login: login
    };
    return api;

    // Checks for registered users credentials
    function login(user) {
        let filename = "loginCredentials.txt";
        let auth_user = JSON.parse(fs.readFileSync(filename, "utf8"));
        const deferred = $q.defer();

        // checks if input username or password or both are empty.
        if (user.username === null || user.password === null) {
            deferred.reject(null);
            return deferred.promise;
        }

        // checks if given username and password match the registered users.
        else if (user.username === auth_user.username && bcrypt.compareSync(user.password, auth_user.password)) {
            deferred.resolve(1);
            return deferred.promise;
        }

        // checks if given username and password does not match the registered users.
        else {
            deferred.reject("invalidUser");
            return deferred.promise;
        }
    }
}