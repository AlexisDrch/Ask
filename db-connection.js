var promise = require('bluebird');

var options = {
  // Initialization Options
  promiseLib: promise
};

const LOCALHOST_URL = 'postgres://localhost:5432/ask'
const DEPLOYMENT_URL = 'postgres://ggqwespmozubup:547149f9860bb143956959fe6fbb22bcf43857b9a2de9e8312049bb3ba82190e@ec2-174-129-26-203.compute-1.amazonaws.com:5432/daqoghiq44sdjd:5432/ask'

var pgp = require('pg-promise')(options);
var connectionString = DEPLOYMENT_URL;
var db = pgp(connectionString);


module.exports = db;