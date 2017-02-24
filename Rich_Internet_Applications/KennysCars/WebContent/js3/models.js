var rootUrl = "http://localhost:8080/KennysCars/rest/cars";
var Car = Backbone.Model.extend({
	urlRoot:rootUrl,
	defaults:{ 
	    "id": null,
	    "make": " ",
	    "year": " ",
	    "milleage": " ",
	    "nct": " ",
	    "colour": " ",
	    "description": " ",
	    "edit": " ",
	    "picture": " ",
	    "engineSize": " "
	    
	},
  initialize: function(){
    console.log("car init");
    this.on('change', function(){
    	console.log('Values have changed');
    });
  }
});

var CarList = Backbone.Collection.extend({
	model: Car,
	url:rootUrl});









