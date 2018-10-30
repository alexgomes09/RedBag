'use strict';

var Post = require('../models/Post');
var querystring = require('querystring');

function postRoutes(app) {

    app.post('/postBloodRequest', function(req, res) {
        console.log("/postBloodRequest");

        var bloodGroup = app.trimString(req.body.bloodGroup)

        if (!bloodGroup) {
            return res.status(401).send({
                success: false,
                message: "Please provide blood type"
            })
        }

        if (!Post.schema.path('bloodGroup').enumValues.includes(bloodGroup)) {
            return res.status(401).send({
                success: false,
                message: "Blood group doesn't exist"
            })
        }

        var newPost = new Post();
        newPost.bloodGroup = bloodGroup;
        newPost.numberOfBags = req.body.numberOfBags;
        newPost.phoneNumber = req.body.phoneNumber;
        newPost.name = app.trimString(req.body.name);
        newPost.address = app.trimString(req.body.address);
        newPost.email = req.body.email;
        newPost.age = req.body.age;

        if (typeof(req.body.longitude) == 'number' && typeof(req.body.latitude) == 'number') {
            newPost.location = {
                type: "Point",
                coordinates: [req.body.longitude, req.body.latitude]
            }
        }

        console.log(JSON.stringify(newPost, null, 2))

        newPost.save(function(err, post) {
            if (err) {
                res.status(401).send({
                    success: false,
                    message: "Error saving the post:",
                    err: err
                })
            } else {
                res.status(200).send({
                    success: true,
                    message: "Successfully created post",
                    post: post
                })
            }
        })
    });

    app.post('/getBloodRequestList', function(req, res) {
        console.log('/getBloodRequestList');

        //// skip
        var skip = 0;

        // check if skip is number
        if (/^\d+$/.test(req.query.skip)) {
            skip = Number(req.query.skip)
        }

        //// Query
        var query = {}

        var sort = {
            "updated_at": req.query.sort //recent to old
        }

        if (req.query.bloodGroup) {
            query.bloodGroup = { $in: req.query.bloodGroup }
        }

        // query.location = {
        //     '$nearSphere': {
        //         '$maxDistance': req.query.maxDistance * 1000, //10km
        //         '$geometry': {
        //             type: 'Point',
        //             coordinates: [req.query.longitude, req.query.latitude]
        //         }
        //     }
        // }

        console.log(JSON.stringify(query, null, 2))

        Post
            .find(query)
            .sort(sort)
            .limit(20)
            .skip(skip)
            .exec(function(err, posts) {
                if (err) {
                    res.status(401).send({
                        success: false,
                        message: "Error retrieving all blood request:",
                        err: err
                    })
                } else {
                    res.status(200).send({
                        success: true,
                        message: "",
                        posts: posts
                    })
                }
            })
    })
}


module.exports = postRoutes;