//CarView builds a list item for each car.
var CarView = Backbone.View.extend({
  model: Car,
  tagName:'tr',
  events:{
	  "click .moreDetails": "showDetails",
	  "click .edit":"showEdit"  
  },
  showDetails: function(e){
	  var cars=this.model;
	  $('#modal').html(new ModalView({model:cars}).renderDetails());
	  $('#modal').modal('show');
  },
  
  showEdit: function(e){
	  var car2=this.model;
	  $('#modal').html(new ModalView({model:car2}).renderEdit());
	  $('#modal').modal('show');
  },
    render:function(){
		 var template= _.template($('#carlist').html(), this.model.toJSON());
		return this.$el.html(template);
		
  }
});





