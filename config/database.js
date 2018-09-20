var mongoose = require('mongoose');

var db = {
    production_db: process.env.PRODUCTION_DB,
    development_db: 'mongodb://localhost/redbag'
};

var databaseUrl = db.production_db || db.development_db; // change this in productions

var options = {
    autoReconnect: true,
    useNewUrlParser:true,
    loggerLevel: "error",
    keepAlive: 1
};
mongoose.Promise = global.Promise; //Without this you would get a warning on console when user gets saved to DB

mongoose.connect(databaseUrl, options);

// when the connection starts
mongoose.connection.on('connected', function () {
    console.log('Mongoose connection open to '+ databaseUrl);
});

// log error if error occurs
mongoose.connection.on('error', function (err) {
    console.log('Mongoose connection error: ' + err);
});

// when the connection ends
mongoose.connection.on('disconnected', function () {
    console.log('Mongoose connection to ' + db + ' is closed');
});

// close the connection if node process ends
process.on('SIGINT', function () {
    mongoose.connection.close(function () {
        console.log('Mongoose connection closed through app termination');
        process.exit(0);
    });
});