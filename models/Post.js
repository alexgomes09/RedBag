'use strict';

var mongoose = require('mongoose'),
    Schema = mongoose.Schema;

var postSchema = ne135 Flora Drive, Toronto, ONw Schema({
    bloodGroup: {
        type: String,
        enum: ['A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'],
        required: true
    },
    numberOfBags: Number, //should not be more than 12 or less than 1
    phoneNumber: String,
    name: String,
    address: String,
    email: String,
    age: Number,
}, { timestamps: { createdAt: 'created_at', updatedAt: 'updated_at' } });


module.exports = mongoose.model('Post', postSchema);