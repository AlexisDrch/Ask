var express = require('express');
var router = express.Router();
var Request = require('../models/Request.js');
var User = require('../models/User.js');



/* adding request(s)
	- POST
	_ body : json request
*/
router.post('/',function(req, res, next) {

	console.log(JSON.stringify(req.body,null,2));

	// Get requester infos
	User.getUserByUserId(req.body.requester_id)
	.then(array_users => Request.addRequest(array_users[0], req.body))
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Posted new request(s) ' + JSON.stringify(req.body)
        });
    })
    .catch(function (err) {
      return next(err);
    });
}); 


/* listing request(s)
	- GET
	_ optional : /request_id
*/
router.get('/:request_id?', function(req, res, next) {

	// by request_id
	if (req.params.request_id) {

		Request.getRequestsByRequestId(req.params.request_id)
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

	} else {
		// all unfilled request(s)
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
	}
});

/* listing request(s)
	- GET
	_ optional : /request_id
*/
router.get('/by/:requester_id?', function(req, res, next) {

	// by requester_id
	Request.getRequestsByRequesterId(req.params.requester_id)
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved all requests done by ' + JSON.stringify(req.params.requester_id)
        });
    })
    .catch(function (err) {
      return next(err);
    });
 
});



module.exports = router;
