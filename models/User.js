var db = require('../db-connection'); //reference of dbconnection.js


var User = {

	getAllUsers:function(){
		return db.any('select * from "user"');
	},
	getUserByUserId:function(user_id){
		return db.any('select * from "user" where user_id = $1',user_id);
	},
	getUserByEmailNameAndPassword(user){
		return db.any('select * from "user" where email = ${email} AND password = ${password}',user);
	},
	addUser:function(user){
		user.age = parseInt(user.age);
		return db.any(
			' Insert into "user"' +
			' (name, surname, age, description, ppicture_url, phone_num, address)' + 
			' values('+
				' ${name},'+
				' ${surname},'+
				' ${age},'+
				' ${description},'+
				' ${ppicture_url},'+
				' ${phone_num},'+
				' ${address}'+
			');', user);
	}
	/*,
	/deleteTask:function(id,callback){
		return db.query("delete from task where Id=?",[id],callback);
	},
	updateTask:function(id,Task,callback){
		return db.query("update task set Title=?,Status=? where Id=?",[Task.Title,Task.Status,id],callback);
	}*/
};

module.exports = User;