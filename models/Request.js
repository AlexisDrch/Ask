var db = require('../db-connection'); //reference of dbconnection.js

// Convert Javascript date to Pg YYYY MM DD HH MI SS

function pgFormatDate(date) {
  /* Via http://stackoverflow.com/questions/3605214/javascript-add-leading-zeroes-to-date */
  return String(date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()); 
}

var Request = {

	getAllPendingRequests:function(){
		return db.any('select * from "request" where provider_id IS NULL');
	},
	getRequestsByRequestId:function(request_id){
		return db.any('select * from "request" where request_id = $1',request_id);
	},
	getRequestsByProviderId:function(provider_id){
		return db.any('select * from "request" where provider_id = $1',provider_id);
	},
	addRequest:function(request){
		request.begin_date = new Date('December 17, 2018');
		request.end_date = new Date('December 31, 2018');
		request.item_id = parseInt(request.item_id);
		request.provider_id = null // note that provider_id should always be null when request is first done
		request.requester_id = parseInt(request.requester_id);
		request.begin_date = pgFormatDate(request.begin_date);
		request.end_date = pgFormatDate(request.end_date);
		request.lon = parseFloat(request.lon);
		request.lat = parseFloat(request.lat);
		//console.log(JSON.stringify(request, null, 2));
		return db.any(
			' Insert into "request"' +
			' (item_id, requester_id, begin_date, end_date, lon, lat, description)' +
			' values('+
				' ${item_id},'+
				' ${requester_id},'+
				' ${begin_date},'+
				' ${end_date},'+
				' ${lon},'+
				' ${lat},'+
				' ${description}'+
			');', request);
	},
	/* Accept an new offer
	params:
		-  offer object
	use-case:
		A requester chooses an offer, clicks on accept
	*/
	acceptOffer:function(offer){

			return db.any(
				' UPDATE "request" set' +
				' provider_id = ${provider_id}' +
				' WHERE request_id = ${request_id}' +
				';', offer);
		}
	/*,
	/deleteTask:function(id,callback){
		return db.query("delete from task where Id=?",[id],callback);
	},
	updateTask:function(id,Task,callback){
		return db.query("update task set Title=?,Status=? where Id=?",[Task.Title,Task.Status,id],callback);
	}*/
};

module.exports = Request;