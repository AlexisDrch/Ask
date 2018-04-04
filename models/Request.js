var db = require('../db-connection'); //reference of dbconnection.js
var utils = require('../models/const');

// Convert Javascript date to Pg YYYY MM DD HH MI SS

function pgFormatDate(date) {
  /* Via http://stackoverflow.com/questions/3605214/javascript-add-leading-zeroes-to-date */
  return String(date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()); 
}

var Request = {

	getAllUnFilledRequests:function(){
		return db.any('select * from "request" where status =' + utils.REQUEST_WITH_PENDING_OFFERS);
	},
	getRequestsByRequestId:function(request_id){
		return db.any('select * from "request" where request_id = $1',request_id);
	},
	getRequestsByRequesterId:function(requester_id){
		return db.any('select * from "request" where requester_id = $1',requester_id);
	},
	addRequest:function(requester, request){
		request.begin_date = new Date('December 17, 2018');
		request.end_date = new Date('December 31, 2018');
		request.item_id = parseInt(request.item_id);
		request.status = REQUEST_WITH_PENDING_OFFERS // note that status should always be initialize to 0 (pending)
		request.requester_id = parseInt(request.requester_id);
		request.begin_date = pgFormatDate(request.begin_date);
		request.end_date = pgFormatDate(request.end_date);
		request.lon = parseFloat(request.lon);
		request.lat = parseFloat(request.lat);
		request.requester_id = requester.user_id;
		request.requester_name = requester.name;
		request.requester_surname = requester.surname;
		request.requester_ppicture_url = requester.ppicture_url;
		request.request_price = request.request_price;
		console.log(JSON.stringify(request, null, 2));
		return db.any(
			' Insert into "request"' +
			' (item_id, requester_id, requester_name, requester_surname, requester_ppicture_url, request_price,' +
			' status, begin_date, end_date, lon, lat, description)' +
			' values('+
				' ${item_id},'+
				' ${requester_id},'+
				' ${requester_name},'+
				' ${requester_surname},'+
				' ${requester_ppicture_url},'+
				' ${request_price},'+
				' ${status},'+
				' ${begin_date},'+
				' ${end_date},'+
				' ${lon},'+
				' ${lat},'+
				' ${description}'+
			');', request);
	},

	/* Accept an new offer
	params:
		-  requester_id
		-  offer_id
	use-case:
		A requester chooses an offer, clicks on accept
	*/
	// note : maybe don't need the requester_id : 
	// -> is already in request object (accessible from request id)
	acceptOffer:function(offer, requester_id){
		console.log("status" + utils.OFFER_ACCEPTED_FOR_REQUEST);
		return db.any(
			' UPDATE "offer" set' +
			' requester_id = ' + requester_id +
			' , status = ' + utils.OFFER_ACCEPTED_FOR_REQUEST +
			' WHERE request_id = ${request_id}' +
			' AND provider_id = ${provider_id}' +
			';', offer);
	},

	/* Update a request from status REQUEST_WITH_PENDING_OFFERS to REQUEST_WITH_OFFER_SELECTED
	params:
		-  request_id
	use-case:
		A requester accepted an offer, status changes.
	*/
	updateUnFilledRequestToPending:function(request_id){
		return db.any(
			' UPDATE "request" set' +
			' status = ' + utils.REQUEST_WITH_OFFER_SELECTED +
			' WHERE request_id = $1',request_id);
	}
};

module.exports = Request;