var express = require('express');
var router = express.Router();

var db = require('../queries');


router.get('/api/items', db.getAllItems);
module.exports = router;