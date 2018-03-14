var express = require('express');
var router = express.Router();
var Request = require('../models/Request')

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

module.exports = router;