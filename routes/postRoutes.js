'use strict';

var Post = require('../models/Post');
var querystring = require('querystring');

function postRoutes(app) {

    app.post('/postBloodRequest', function(req, res) {
        console.log("/postBloodRequest");

        var bloodGroup = app.trimString(req.body.bloodGroup)

        if (!bloodGroup) {
            return res.status(202).send({
                success: false,
                message: "Please provide blood type"
            })
        }

        if (!Post.schema.path('bloodGroup').enumValues.includes(bloodGroup)) {
            return res.status(202).send({
                success: false,
                message: "Blood type doesn't exist"
            })
        }


        var newPost = new Post();
        newPost.bloodGroup = bloodGroup;
        newPost.numberOfBags = req.body.numberOfBags;
        newPost.contactNumber = req.body.contactNumber;
        newPost.name = app.trimString(req.body.name);
        newPost.address = app.trimString(req.body.address);
        newPost.contactEmail = req.body.contactEmail;
        newPost.city = app.trimString(req.body.city);
        newPost.ageOfPatient = req.body.ageOfPatient;

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



    app.get('/getBloodRequest', function(req, res) {
        console.log('/getBloodRequest');

        var query = {}


        console.log(req);


        if (req.query.bloodGroup) {
            query.bloodGroup = req.query.bloodGroup;
        }

        Post.find(query).exec(function(err, posts) {
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