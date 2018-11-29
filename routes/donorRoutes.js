'use strict';

var Donor = require('../models/Donor'),
    auth = require('../config/auth');

function donorRoutes(app, passport) {

    //Register With Email
    app.post('/registerWithEmail', function(req, res, next) {
        console.log('/registerWithEmail');

        if (!req.body.emailAddress) {
            return res.status(400).send({
                success: false,
                message: "You must provide email address"
            })
        }

        if (!req.body.password) {
            return res.status(400).send({
                success: false,
                message: "You must provide a password"
            })
        }

        if (!req.body.bloodGroup) {
            return res.status(400).send({
                success: false,
                message: "You must provide your blood group"
            })
        }

        passport.authenticate('local', function(err, user, info) {
            if (err) return res.status(401).json(error);
            if (!user) return res.status(404).json({ message: 'Something went wrong, please try again.' });
            if (user) return res.status(200).json(info)
        })(req, res, next)
    })

    //LOGIN
    app.post('/loginWithEmail', function(req, res) {
        console.log("/loginWithEmail");

        var data = {
            emailAddress: req.body.emailAddress,
            password: req.body.password
        };

        if (!data.emailAddress) {
            res.status(400).send({
                success: false,
                message: 'You must provide an email address.'
            })
        } else if (!data.password) {
            res.status(400).send({
                success: false,
                message: 'You must provide a password'
            })
        } else {
            Donor.findOne({
                    emailAddress: data.emailAddress
                })
                .then(function(donor) {
                    if (!donor || donor === {} || donor === []) {
                        res.status(401).send({
                            success: false,
                            message: "Invalid email address or password, kindly try again"
                        })
                    } else {
                        donor.comparePassword(data.password, function(err, isMatch) {
                            if (err) {
                                console.log('Password Verification Error: ', err);
                                res.status(401).send({
                                    success: false,
                                    message: 'Encountered error while processing request, kindly try again',
                                    error: err
                                })
                            } else if (!isMatch) {
                                res.status(401).send({
                                    success: false,
                                    message: 'Invalid email address or password, kindly try again'
                                })
                            } else {
                                var token = auth.createDonorToken({ _id: donor._id });
                                res.status(200).send({
                                    success: true,
                                    message: 'Login Successful',
                                    token: token,
                                    donor: donor
                                })
                            }
                        })
                    }
                })
                .catch(function(err) {
                    console.log('Login Error, Error Finding user: ', err.stack);
                    res.status(202).send({
                        success: false,
                        message: 'Encountered error while processing request, kindly try again',
                        error: err
                    })
                })
        }
    });
}

module.exports = donorRoutes;