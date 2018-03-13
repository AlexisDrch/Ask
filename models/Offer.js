var db = require('../db-connection'); //reference of dbconnection.js

// Convert Javascript date to Pg YYYY MM DD HH MI SS

function pgFormatDate(date) {
  /* Via http://stackoverflow.com/questions/3605214/javascript-add-leading-zeroes-to-date */
  return String(date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()); 
}

var Offer = {

	/* Get all offers made for a request
	params:
		- request_id (id of the request)
	use-case:
		A requester clicks on his request and sees all the offers
	*/
	getAllOffers:function(request_id){
		return db.any('select * from "offer" where request_id = $1',request_id);
	},

	/* Post a new offer
	params:
		- offer (id of the request)
	use-case:
		A provider clicks on a request, fills his offer, and presses on match
	*/
	makeOffer:function(offer){
		offer.begin_date = new Date('December 17, 2018');
		offer.end_date = new Date('December 31, 2018');
		offer.request_id = parseInt(offer.request_id);
		offer.provider_id = parseInt(offer.provider_id);
		offer.belonging_id = parseInt(offer.belonging_id);
		offer.begin_date = pgFormatDate(offer.begin_date);
		offer.end_date = pgFormatDate(offer.end_date);
		offer.lon = parseFloat(offer.lon);
		offer.lat = parseFloat(offer.lat);
		return db.any(
			' Insert into "offer"' +
			' (belonging_id, request_id, provider_id, begin_date, end_date, lon, lat, description, message)' +
			' values('+
				' ${belonging_id},'+
				' ${request_id},'+
				' ${provider_id},'+
				' ${begin_date},'+
				' ${end_date},'+
				' ${lon},'+
				' ${lat},'+
				' ${description}'+
				' ${message}'+
			');', offer);
	}
	/*,
	/deleteTask:function(id,callback){
		return db.query("delete from task where Id=?",[id],callback);
	},
	updateTask:function(id,Task,callback){
		return db.query("update task set Title=?,Status=? where Id=?",[Task.Title,Task.Status,id],callback);
	}*/
};

module.exports = Offer;