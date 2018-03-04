var express = require('express');
var router = express.Router();

var db = require('../db-connection');

// shows all the requests
function getAllRequests(req, res, next) {
  db.any('select * from request')
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved ALL requests'
        });
    })
    .catch(function (err) {
      return next(err);
    });
}


router.get('/', getAllRequests);
module.exports = router;