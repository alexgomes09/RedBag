'use strict';

var mongoose = require('mongoose'),
    Schema = mongoose.Schema,
    bcrypt = require('bcrypt'),
    SALT_WORK_FACTOR = 8;

var donorSchema = new Schema({
    emailAddress: {
        type: String,
        unique: true,
        lowercase: true,
        trim: true
    },
    password: {
        type: String,
        require: true,
        trim: true
    },
    name: {
        type: String,
        trim: true
    },
    bloodGroup: {
        type: String,
        enum: ['A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-'],
        required: true
    },
    age: Number,
    ipAddress: Number,
    phoneNumber: String,
}, { timestamps: { createdAt: 'created_at', updatedAt: 'updated_at' } });


// generating a hash
donorSchema.statics.generateHash = function(password) {
    return bcrypt.hashSync(password, bcrypt.genSaltSync(SALT_WORK_FACTOR), null);
};

// checking if password is valid
donorSchema.methods.comparePassword = function(userPassword, callBack) {
    bcrypt.compare(userPassword, this.password, function(err, isMatch) {
        if (err) return callBack(err);
        callBack(null, isMatch);
    });
};

var Donor = mongoose.model('Donor', donorSchema);

module.exports = Donor;