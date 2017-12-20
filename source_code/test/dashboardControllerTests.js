/**
 * Created by rosyp on 11/21/2017.
 */
const assert = require('chai').assert;
require('jsdom-global')();

require('angular/angular');
require('angular-mocks');

global.angular = window.angular;

global.app = require('../app1');
let init = require('../src/views/controllers/dashboardController');

describe('App11', function(){
    it('should return type string', function () {
        let result = init();
        assert.typeOf(result, boolean);
    });
    
    it('should return false', function () {
        let result = init();
        assert.equal(result, false);
    });
});
