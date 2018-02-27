var promise = require('bluebird');

var options = {
  // Initialization Options
  promiseLib: promise
};

var pgp = require('pg-promise')(options);
var connectionString = 'postgres://localhost:5432/ask';
var db = pgp(connectionString);

// add query functions
function getAllItems(req, res, next) {
  db.any('select * from item')
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved ALL items'
        });
    })
    .catch(function (err) {
      return next(err);
    });
}

module.exports = {
  getAllItems: getAllItems
};