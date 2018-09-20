// modules =================================================
var express = require('express');
var app = express();
// var passport = require('passport');
var cors = require('cors');
var jwt = require('jsonwebtoken');
var bodyParser = require('body-parser');

//does database connection
require('./config/database');
// require('./config/auth');

var port = process.env.PORT || 8080;

app.use(bodyParser.json({limit: '50mb'}));
app.use(bodyParser.urlencoded({extended: true}));
// app.use(passport.initialize());
// app.use(passport.session());
// app.use(methodOverride('X-HTTP-Method-Override')); // override with the X-HTTP-Method-Override header in the request. simulate DELETE/PUT


// Allow cross origin requests(CORS) ======================================================================
app.use(function (err, req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE');
    res.header('Access-Control-Allow-Headers', 'Content-Type');
    res.header('Cache-Control', 'no-cache, private, no-store, must-revalidate, max-stale=0, post-check=0, pre-check=0');
    res.header('Pragma', 'no-cache');
    res.header('Expires', 'Fri, 31 Dec 2998 12:00:00 GMT');
    if (err) { // handle  server errors
        console.log('Internal Server Error: ', err.stack);
        res.status(500)
            .send({
                error: true,
                message: 'App encountered error. Kindly try again after a while.'
            })
    } else {
        next();
    }
});

app.use(cors());
app.use(express.static(__dirname + '/public')); // set the static files location /public/img will be /img for users
// app.get('/*', function(req, res) {
//     res.sendFile(process.cwd() + '/public/');
// });

app.trimString = function(val){
    return val.replace(/(^\s+|\s+$)/g,'')
};

// routes ======================================================================
require('./routes/index')(app); // pass our application into our routes


// launch ======================================================================
app.listen(port);
console.log('Magic happens on port ' + port);

exports = module.exports = app;