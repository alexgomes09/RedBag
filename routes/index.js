'use strict'

console.log("Router initialized");

var postRoutes = require('./postRoutes');
var donorRoutes = require('./donorRoutes');

function routes(app, passport) {
    postRoutes(app);
    donorRoutes(app, passport);
}

module.exports = routes;