const assert = require('chai').assert;
require('jsdom-global')();

require('angular/angular');
require('angular-mocks');

global.angular = window.angular;

global.app = require('../app1');
var login1 = require('../src/services/user.service.client');


describe('App11', function(){
    it('should return null', function () {
        let user1 = {"username" : "", "password" : "team"};
        let result = login(user1);
        assert.equal(result, 'null');
    });

    it('should return invalid user', function () {
        let user1 = {"username" : "team", "password" : "team"};
        let result = login(user1);
        assert.equal(result, 'invalidUser');
    });

    it('should return user', function () {
        let user1 = {"username" : "team21", "password" : "team21"};
        let result = login(user1);
        assert.equal(result, 'user');
    });

    it('should return type string', function () {
        let user1 = {"username" : "team21", "password" : "team21"};
        let result = login(user1);
        assert.typeOf(result, 'string');
    });
});