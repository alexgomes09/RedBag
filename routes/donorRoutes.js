'use strict';

var Donor = require('../models/Donor'),
    auth = require('../config/auth');

function donorRoutes(app, passport) {

    //Register With Email
    app.post('/registerWithEmail', function(req, res, next) {
        console.log('/registerWithEmail');

        if (!req.body.emailAddress) {
            return res.status(403).send({
                success: false,
                message: "You must provide email address"
            })
        }

        if (!req.body.password) {
            return res.status(403).send({
                success: false,
                message: "You must provide a password"
            })
        }

        if (!req.body.bloodGroup) {
            return res.status(403).send({
                success: false,
                message: "You must provide your blood group"
            })
        }

        passport.authenticate('local', function(err, user, info) {
            var error = err || info;
            if (error) return res.status(202).json(error);
            if (!user) return res.status(404).json({ message: 'Something went wrong, please try again.' });
        })(req, res, next)

        // var newDonor = {};

        // if (req.body.phoneNumber) {
        //     newDonor.phoneNumber = req.body.phoneNumber
        // }

        // if (req.body.email) {
        //     newDonor.email = req.body.email
        // }

        // newDonor.deviceId = req.body.deviceId;
        // newDonor.name = req.body.name;
        // newDonor.age = req.body.age;
        // newDonor.bloodGroup = req.body.bloodGroup

        // Donor.findOneAndUpdate({
        //     'deviceId': req.body.deviceId
        // }, newDonor, { upsert: true, new: true }, function(err, donor) {

        //     if (err) {
        //         res.status(401).send({
        //             success: true,
        //             message: "Error occured",
        //             err: err
        //         })
        //     } else {
        //         res.status(200).send({
        //             success: true,
        //             message: "Success",
        //             donor: donor
        //         })
        //     }
        // })
    })

    //LOGIN
    app.post('/loginWithEmail', function(req, res) {
        console.log("/loginWithEmail");

        var data = {
            emailAddress: req.body.emailAddress,
            password: req.body.password
        };

        if (!data.emailAddress) {
            res.status(202).send({
                success: false,
                message: 'You must provide an email address.'
            })
        } else if (!data.password) {
            res.status(202).send({
                success: false,
                message: 'You must provide a password'
            })
        } else {
            Donor.findOne({
                    emailAddress: data.emailAddress
                })
                .then(function(donor) {
                    if (!donor || donor === {} || donor === []) {
                        res.status(202).send({
                            success: false,
                            message: "Invalid email address or password, kindly try again"
                        })
                    } else {
                        donor.comparePassword(data.password, function(err, isMatch) {
                            if (err) {
                                console.log('Password Verification Error: ', err);
                                res.status(202).send({
                                    success: false,
                                    message: 'Encountered error while processing request, kindly try again',
                                    error: err
                                })
                            } else if (!isMatch) {
                                res.status(202).send({
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