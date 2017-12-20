/**
 * Created by rosyp on 11/20/2017.
 */

// adding express module to code sever side
var express = require('express');
var app = express();

// install, load, and configure body parser module
var bodyParser = require('body-parser');
app.use(bodyParser.json());
//app.use(bodyParser.urlencoded({ extended: true }));

// configure a public directory to host static content
app.use(express.static(__dirname + '/views'));

// adding configurations for client and server side
require("./server/app")(app);

// configure port for running the project
var port = process.env.PORT || 3000;
app.listen(port);