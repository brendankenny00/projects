
//An instance of tableView is made whenever app.js is run.

var tableView;
var carList;


//The rendered tableView is displayed to the page.

$(document).ready(function(){
	console.log("here");
	carList = new CarList();
	

	carList.fetch({
		success: function(data){
			console.log("data fetched");
			
			tableView = new TableView({collection: carList});
			
			console.log(tableView.$el);
		}
	});
});




