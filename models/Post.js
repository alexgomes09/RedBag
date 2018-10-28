'use strict';

var mongoose = require('mongoose'),
    Schema = mongoose.Schema;

var postSchema = new Schema({
    bloodGroup: {
        type: String,
        enum: ['A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'],
        required: true
    },
    numberOfBags: Number, //should not be more than 12 or less than 1
    phoneNumber: String,
    name: String,
    address: String,
    emailAddress: String,
    age: Number,
    location: {
        type: { type: String },
        coordinates: []
    },
}, { timestamps: { createdAt: 'created_at', updatedAt: 'updated_at' } });

postSchema.index({ "location": "2dsphere" });

module.exports = mongoose.model('Post', postSchema);