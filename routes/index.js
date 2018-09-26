'use strict'

console.log("Router initialized");

var postRoutes = require('./postRoutes');
var donorRoutes = require('./donorRoutes');

function routes(app) {
    postRoutes(app);
    donorRoutes(app);
}

module.exports = routes;