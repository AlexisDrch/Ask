var express = require('express');
var router = express.Router();
var Request = require('../models/Request');
var User = require('../models/User');

var db = require('../db-connection');

router.get('/', function(req, res, next) {

  Request.getAllUnFilledRequests()
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved ALL unfilled request(s)'
        });
    })
    .catch(function (err) {
      return next(err);
    });
});


router.post('/login', function(req, res, next) {
console.log(req.body)
  User.getUserByEmailNameAndPassword(req.body)

    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Succesful login'
        });
    })
    .catch(function (err) {
      return next(err);
    });
});

module.exports = router;