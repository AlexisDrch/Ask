var db = require('../db-connection'); //reference of dbconnection.js
var utils = require('../models/const');
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
	getOffersByRequestId:function(request_id){
		return db.any('select * from "offer" where request_id = $1',request_id);
	},

	/* Get all offers done by a provider
	params:
		- provider_id (id of the provider)
	use-case:
		A provider wants to see the current states of its offers.
	*/
	getOffersByProviderId:function(provider_id){
		return db.any('select * from "offer" where provider_id = $1', provider_id);
	},

	/* Post a new offer
	params:
		- offer (id of the request)
	use-case:
		A provider clicks on a request, fills his offer, and presses on match
	*/
	makeOffer:function(provider, offer){
		offer.begin_date = new Date('December 17, 2018');
		offer.end_date = new Date('December 31, 2018');
		offer.request_id = parseInt(offer.request_id);
		offer.offer_price = parseInt(offer.offer_price);
		offer.provider_id = parseInt(offer.provider_id);
		offer.belonging_id = parseInt(offer.belonging_id);
		offer.begin_date = pgFormatDate(offer.begin_date);
		offer.end_date = pgFormatDate(offer.end_date);
		offer.lon = parseFloat(offer.lon);
		offer.lat = parseFloat(offer.lat);
		offer.provider_name = provider.name;
		offer.provider_surname = provider.surname;
		offer.provider_ppicture_url = provider.ppicture_url;
		offer.status = utils.OFFER_PENDING_REQUEST;
		console.log(JSON.stringify(offer, null, 2));
		return db.any(
			' Insert into "offer"' +
			' (belonging_id, request_id, '+
			' provider_id, provider_name, provider_surname, provider_ppicture_url, '+
			' begin_date, end_date, lon, lat, offer_price, description, status, message)' +
			' values('+
				' ${belonging_id},'+
				' ${request_id},'+
				' ${provider_id},'+
				' ${provider_name},'+
				' ${provider_surname},'+
				' ${provider_ppicture_url},'+
				' ${begin_date},'+
				' ${end_date},'+
				' ${lon},'+
				' ${lat},'+
				' ${offer_price},'+
				' ${description},'+
				' ${status},'+
				' ${message}'+	
			');', offer);
	},

	/* Delete all offers for a request
	params:
		- request_id (id of the request)
	use-case:
		A requester accept an offer, all the other offers (provider_id is NULL)
		for this request are deleted.
	*/
	deleteOffersByRequestId:function(request_id){
		return db.any('DELETE FROM "offer" where request_id = $1 AND provider_id IS NULL',request_id);
	},

	
};

module.exports = Offer;