'use strict';

var User = require('../models/User');

function userRoutes(app) {


    app.post('/createProfile', function(req, res) {
        console.log('/createProfile');

        if (!req.body.deviceId) {
            return res.status(403).send({
                success: false,
                message: "Device id needed"
            })
        }

        if (!req.body.name) {
            return res.status(403).send({
                success: false,
                message: "Name field is required"
            })
        }

        if (!req.body.bloodGroup) {
            return res.status(403).send({
                success: false,
                message: "Blood group field is required"
            })
        }

        if (!req.body.phoneNumber && !req.body.email) {
            return res.status(403).send({
                success: false,
                message: "Atleast one contact (phone / email) information is required"
            })
        }

        var newUser = {};

        if (req.body.phoneNumber) {
            newUser.phoneNumber = req.body.phoneNumber
        }

        if (req.body.email) {
            newUser.email = req.body.email
        }

        newUser.deviceId = req.body.deviceId;
        newUser.name = req.body.name;
        newUser.age = req.body.age;
        newUser.bloodGroup = req.body.bloodGroup

        User.findOneAndUpdate({
            'deviceId': req.body.deviceId
        }, newUser, { upsert: true, new: true }, function(err, user) {

            if (err) {
                res.status(401).send({
                    success: true,
                    message: "Error occured",
                    err: err
                })
            } else {
                res.status(200).send({
                    success: true,
                    message: "Success",
                    user: user
                })
            }
        })
    })
}


module.exports = userRoutes;