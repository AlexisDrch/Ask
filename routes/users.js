var express = require('express');
var router = express.Router();
var User = require('../models/User.js');



/* adding user(s)
	- POST
	_ body : json user
*/
router.post('/',function(req, res, next) {

	console.log(JSON.stringify(req.body,null,2));

	User.addUser(req.body)
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Posted new users ' + JSON.stringify(req.body)
        });
    })
    .catch(function (err) {
      return next(err);
    });
}); 


/* listing user(s)
	- GET
	_ optional : /user_id
*/
router.get('/:user_id?', function(req, res, next) {

	// by user_id
	if (req.params.user_id) {

		User.getUserByUserId(req.params.user_id)
	    .then(function (data) {
	      res.status(200)
	        .json({
	          status: 'success',
	          data: data,
	          message: 'Retrieved user ' + JSON.stringify(req.params.user_id)
	        });
	    })
	    .catch(function (err) {
	      return next(err);
	    });

	} else {
		// all users
		User.getAllUsers()
	    .then(function (data) {
	      res.status(200)
	        .json({
	          status: 'success',
	          data: data,
	          message: 'Retrieved ALL users'
	        });
	    })
	    .catch(function (err) {
	      return next(err);
	    });
	}

});

module.exports = router;
