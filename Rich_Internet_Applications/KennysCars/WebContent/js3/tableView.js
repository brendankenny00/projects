//tableView grabs all the rendered (CarViews) and appends them to the table body.
var TableView = Backbone.View.extend({
  collection: CarList,
  initialize: function(){
   this.render();
   $('#CarTable').dataTable();
  },

  render: function(){
    this.collection.each(function(cars){
    $('#cTableBody').append(new CarView({model:cars}).render());
    }, this);
  }

});






