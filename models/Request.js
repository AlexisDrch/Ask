var db = require('../db-connection'); //reference of dbconnection.js
const UNFILLED_REQUEST = 0
const PENDING_REQUEST = 1
const FILLED_REQUEST = 2

// Convert Javascript date to Pg YYYY MM DD HH MI SS

function pgFormatDate(date) {
  /* Via http://stackoverflow.com/questions/3605214/javascript-add-leading-zeroes-to-date */
  return String(date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()); 
}

var Request = {

	getAllUnFilledRequests:function(){
		return db.any('select * from "request" where status =' + UNFILLED_REQUEST);
	},
	getRequestsByRequestId:function(request_id){
		return db.any('select * from "request" where request_id = $1',request_id);
	},
	addRequest:function(requester, request){
		request.begin_date = new Date('December 17, 2018');
		request.end_date = new Date('December 31, 2018');
		request.item_id = parseInt(request.item_id);
		request.status = UNFILLED_REQUEST // note that status should always be initialize to 0 (pending)
		request.requester_id = parseInt(request.requester_id);
		request.begin_date = pgFormatDate(request.begin_date);
		request.end_date = pgFormatDate(request.end_date);
		request.lon = parseFloat(request.lon);
		request.lat = parseFloat(request.lat);
		request.requester_id = requester.user_id;
		request.requester_name = requester.name;
		request.requester_surname = requester.surname;
		request.requester_ppicture_url = requester.ppicture_url;
		//console.log(JSON.stringify(request, null, 2));
		return db.any(
			' Insert into "request"' +
			' (item_id, requester_id, requester_name, requester_surname, requester_ppicture_url,' +
			' status, begin_date, end_date, lon, lat, description)' +
			' values('+
				' ${item_id},'+
				' ${requester_id},'+
				' ${requester_name},'+
				' ${requester_surname},'+
				' ${requester_ppicture_url},'+
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

		return db.any(
			' UPDATE "offer" set' +
			' requester_id = ' + requester_id +
			' WHERE request_id = ${request_id}' +
			' AND provider_id = ${provider_id}' +
			';', offer);
	},

	/* Update a request from status UnFilled to Pending
	params:
		-  request_id
	use-case:
		A requester accepted an offer, its request is now pending until its completely filled.
	*/
	updateUnFilledRequestToPending:function(request_id){
		return db.any(
			' UPDATE "request" set' +
			' status = ' + PENDING_REQUEST +
			' WHERE request_id = $1',request_id);
	}
};

module.exports = Request;