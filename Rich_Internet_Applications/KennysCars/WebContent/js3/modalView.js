//ModalView builds a list item for each car.
var ModalView = Backbone.View.extend({
  model: Car,
  events:{
	  "click #newCar" : "newCar",
	  "click #delete" : "deleteCar",
	  "click #save" : "saveCar",
  },
 
	
	newCar : function() {
		$("#carId").val("");
		$("#make").val("");
		$("#model").val("");
		$("#year").val("");
		$("#mileage").val("");
		$("#nct").val("");
		$("#colour").val("");
		$("#enginesize").val("");
		$("#pictureedit").val("");
		$("#descriptionedit").val("");
	},
	
	deleteCar : function() {
		var car = new Car({id:$("#carId").val()});
		car.destroy({
			
			success:function(){
				alert("Deleted");
				$('#modal').modal('hide');
				
			}
		
		}
		);
	
	},
	
	
	
	saveCar : function() {
		if ($("#carId").val() == "") {
			var car = new Car();
		} else {
			var car = new Car({
				id : $("#carId").val()
			});
		}
		car.fetch({});
		car.save({
			make : $("#make").val(),
			model : $("#model").val(),
			year : $("#year").val(),
			milleage : $("#mileage").val(),
			nct : $("#nct").val(),
			colour : $("#colour").val(),
			engineSize : $("#enginesize").val()	,	
		}, {

			success : function() {
				alert("saved");
			}
		});
	},
	
	
	
	
    renderDetails:function(){
		 var template= _.template($('#Details').html(), this.model.toJSON());
		return this.$el.html(template);
		
  },
	renderEdit : function() {
		var template = _.template($('#Edit').html(), this.model.toJSON());
		return this.$el.html(template);

	},
});
