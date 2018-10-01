'use strict';

var Donor = require('../models/Donor');

function donorRoutes(app) {


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

        var newDonor = {};

        if (req.body.phoneNumber) {
            newDonor.phoneNumber = req.body.phoneNumber
        }

        if (req.body.email) {
            newDonor.email = req.body.email
        }

        newDonor.deviceId = req.body.deviceId;
        newDonor.name = req.body.name;
        newDonor.age = req.body.age;
        newDonor.bloodGroup = req.body.bloodGroup

        Donor.findOneAndUpdate({
            'deviceId': req.body.deviceId
        }, newDonor, { upsert: true, new: true }, function(err, donor) {

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
                    donor: donor
                })
            }
        })
    })
}


module.exports = donorRoutes;