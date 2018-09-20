'use strict';

var mongoose = require('mongoose'),
    Schema = mongoose.Schema;

var userSchema = new Schema({
    deviceId: String,
    name: String,
    age: Number,
    bloodGroup: {
        type: String,
        enum: ['A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'],
        required: true
    },
    phoneNumber:String,
    email:String,
}, { timestamps: { createdAt: 'created_at', updatedAt: 'updated_at' } });


module.exports = mongoose.model('User', userSchema);