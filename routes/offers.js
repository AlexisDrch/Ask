var express = require('express');
var router = express.Router();
var Offer = require('../models/Offer.js');
var Request = require('../models/Request.js');
var User = require('../models/User.js');


router.post('/',function(req, res, next) {

	console.log(JSON.stringify(req.body,null,2));

	User.getUserByUserId(req.body.provider_id)
    .then(array_users => Offer.makeOffer(array_users[0], req.body))
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

router.post('/accept/:requester_id',function(req, res, next) {

  console.log(JSON.stringify(req.body,null,2));

  Request.acceptOffer(req.body, req.params.requester_id)
    //*note that we may think about keeping the others offers if there is an issue with the accepted offer *
    .then(Offer.deleteOffersByRequestId(req.body.request_id))
    .then(Request.updateUnFilledRequestToPending(req.body.request_id))
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: '1. Accepted offer + update request ' + JSON.stringify(req.body) +
          '\n2. Delete all offers for request ' + req.body.request_id
        });
    })
    .catch(function (err) {
      return next(err);
    });
}); 


router.get('/for/:request_id', function(req, res, next) {

	Offer.getOffersByRequestId(req.params.request_id)
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


router.get('/by/:provider_id', function(req, res, next) {

  Offer.getOffersByProviderId(req.params.provider_id)
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved offers ' + JSON.stringify(req.params.provider_id)
        });
    })
    .catch(function (err) {
      return next(err);
    });
});

module.exports = router;
