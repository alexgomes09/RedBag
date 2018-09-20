'use strict'

console.log("Router initialized");

var postRoutes = require('./postRoutes');
var userRoutes = require('./userRoutes');

function routes(app) {
    postRoutes(app);
    userRoutes(app);
}

module.exports = routes;