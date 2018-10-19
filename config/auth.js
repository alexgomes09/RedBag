var Donor = require("../models/Donor"),
    jwt = require('jsonwebtoken'),
    passport = require('passport'),
    LocalStrategy = require('passport-local').Strategy;
    // FacebookTokenStrategy = require('passport-facebook-token');

passport.serializeUser(function(donor, done) {
    done(null, donor);
});

passport.deserializeUser(function(donor, done) {
    done(null, donor);
});

//Local sign up strategy
passport.use('local', new LocalStrategy({
        usernameField: 'emailAddress',
        passwordField: 'password',
        passReqToCallback: true // allows us to pass back the entire request to the callback
    },
    function(req, emailAddress, password, done) {

        process.nextTick(function() {
            Donor.findOne({ 'emailAddress': emailAddress }, function(err, donor) {

                if (err) {
                    return done(err);
                }

                if (donor) {
                    return done(null, false, {
                        success: false,
                        message: "That email is already taken."
                    });
                } else {
                    var newDonor = new Donor();
                    newDonor.emailAddress = emailAddress;
                    newDonor.password = Donor.generateHash(password);
                    newDonor.bloodGroup = req.body.bloodGroup;
                    
                    newDonor.save(function(err, donor) {
                        if (err) {
                            console.log("Donor couldn't save", err);
                            done(err, null)
                        } else {
                            var token = createDonorToken({ _id: donor._id });
                            done(null, false, {
                                success: true,
                                message: "Success",
                                token: token,
                                donor: donor
                            })
                        }
                    });
                }
            });

        });
    }));


// passport.use(new FacebookTokenStrategy({
//     clientID: process.env.FACEBOOK_ID || '887033501444944',
//     clientSecret: process.env.FACEBOOK_SECRET || '0814da8f9b5d48781ee99c5468bbdc1e'
// }, function(accessToken, refreshToken, profile, done) {

//     process.nextTick(function() {

//         User.findOne({ 'facebookProfileID': profile.id }, function(err, user) {

//             if (err)
//                 return done(err);

//             if (user) {
//                 user.firstName = profile.name.givenName;
//                 user.lastName = profile.name.familyName;
//                 user.emailAddress = profile.emails[0].value;
//                 // save our user to the database
//                 user.save(function(err) {
//                     if (err)
//                         throw err;
//                     return done(null, user);
//                 });
//             } else {
//                 var newUser = new User();

//                 newUser.facebookProfileID = profile.id;
//                 newUser.firstName = profile.name.givenName;
//                 newUser.lastName = profile.name.familyName;
//                 newUser.emailAddress = profile.emails[0].value;

//                 // save our user to the database
//                 newUser.save(function(err) {
//                     if (err)
//                         throw err;
//                     return done(null, newUser);
//                 });
//             }
//         });
//     });
// }));

function createDonorToken(obj) {
    return jwt.sign(obj, process.env.JWT_SECRET || 'dev', { expiresIn: '1y' });
}

function isAuthenticated() {
    return function(req, res, next) {
        if (req.headers && req.headers.authorization) {
            jwt.verify(req.headers.authorization, process.env.JWT_SECRET || 'dev', function(err, decode) {
                if (err) {
                    req.decoded = undefined;
                    return res.json({ success: false, message: 'Failed to authenticate token.' })
                }
                req.decoded = decode;
                next();
            });
        } else {
            return res.status(403).send({
                success: false,
                message: 'No token provided.'
            });
        }
    };
}

function hasRole(requestedRole) {
    if (!requestedRole) throw new Error('Required role needs to be set');

    return function(req, res, next) {
        if (requestedRole === req.decoded.userType) {
            next()
        } else {
            return res.status(403).send({
                success: false,
                message: 'You don\'t have access.'
            });
        }
    };
}

exports.createDonorToken = createDonorToken;
exports.isAuthenticated = isAuthenticated;
exports.hasRole = hasRole;