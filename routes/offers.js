var express = require('express');
var router = express.Router();
var Offer = require('../models/Offer.js');


router.post('/',function(req, res, next) {

	console.log(JSON.stringify(req.body,null,2));

	Offer.makeOffer(req.body)
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Posted new offer(s) ' + JSON.stringify(req.body)
        });
    })
    .catch(function (err) {
      return next(err);
    });
}); 


router.get('/:request_id', function(req, res, next) {

	Offer.getAllOffers(req.params.request_id)
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved request ' + JSON.stringify(req.params.request_id)
        });
    })
    .catch(function (err) {
      return next(err);
    });
});

module.exports = router;
