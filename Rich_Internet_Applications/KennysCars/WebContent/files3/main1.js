// JavaScript Document

//Create a model class

var TodoItem = Backbone.Model.extend({
      defaults: {
        title: '',
		completed :false
      }
});

//Create a collection class
console.log("here");
var TodoList=Backbone.Collection.extend({
	model:TodoItem,
	localStorage: new Backbone.LocalStorage("todos-backbone"),
});

var todoList;
var ListView = Backbone.View.extend({
  collection: TodoList,
  el: '#todoapp',
  initialize: function(){
  this.render();
  },

  render: function(){
    this.collection.each(function(todoItem){
    $('#todo-list').append(new TodoView({model:todoItem}).render());
    }, this);
  }
});
console.log("here");
var TodoView = Backbone.View.extend({
  model: TodoItem,
  tagName:'li',
    render:function(){
		 var template= _.template($('#todo_list').html(), this.model.toJSON());
		return this.$el.html(template);
  }
});

$(document).ready(function(){
	$(document).on("click", "#register", function(){var listView;
												  listView = new ListView({collection: todoList});
												  $('#modal').modal('show');});
	$(document).on("click", "#registerBtn", function(){alert("register button clicked");});
	
	
	todoList = new TodoList();;
	todoList.fetch({
		success: function(data){
			/*
			//Just uncomment these lines to get an item into the list
			var todo = new TodoItem({title:'Finish backbone.js Exercise'+ new Date().toISOString()});
			todoList.add(todo);
			todo.save();
			*/
			todoList.forEach(function(model){
			alert("To Do List Item: " +model.get('title'));
								 });
		}
	});
});

