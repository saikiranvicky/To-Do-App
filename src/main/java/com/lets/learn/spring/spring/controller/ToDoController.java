package com.lets.learn.spring.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.lets.learn.spring.spring.model.Todo;
//import com.lets.learn.spring.spring.model.ToDo;
import com.lets.learn.spring.spring.service.TodoService;

@Controller
@SessionAttributes("name")
public class ToDoController {
	
	@Autowired
	TodoService service;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	}
	
	@RequestMapping(value="/to-do", method = RequestMethod.GET)
	public String toDo(ModelMap model) {
		String name = getLoggerUserName(model);
		model.put("todo",service.retrieveTodos(name));
		return "to-do";
	}
	
	@RequestMapping(value="/delete-todo", method = RequestMethod.GET)
	public String addToDo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/to-do";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.GET)
	public String showUpdateToDo(@RequestParam int id, ModelMap model) {
		//String name = (String) model.get("name");
		//model.put("todo",service.retrieveTodos(name));
		Todo todo = service.retrieveTodo(id);
		model.put("todo", todo);		
//		model.addAttribute("todo", new Todo(0, (String) model.get("name"), "Default Desc",
//				new Date(), false));
		return "add-todo";
	}
	
	@RequestMapping(value="/update-todo", method = RequestMethod.POST)
	public String updateToDo(@Valid Todo todo, BindingResult result, ModelMap model) {
		//String name = (String) model.get("name");
		//model.put("todo",service.retrieveTodos(name));
		if(result.hasErrors()) {
			return "add-todo";
		}
		todo.setUser(getLoggerUserName(model));
		service.changeTodo(todo);
		return "redirect:/to-do";
	}

	private String getLoggerUserName(ModelMap model) {
		return (String) model.get("name");
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.GET)
	public String addToDo(ModelMap model) {
		//String name = (String) model.get("name");
		//model.put("todo",service.retrieveTodos(name));
		model.addAttribute("todo", new Todo(0, getLoggerUserName(model), "Default Desc",
				new Date(), false));
		return "add-todo";
	}
	
	@RequestMapping(value="/add-todo", method = RequestMethod.POST)
	public String updateToDo(ModelMap model,@Valid Todo todo, BindingResult result) {
		//String name = (String) model.get("name");
		if(result.hasErrors()) {
			return "add-todo";
		}
		service.addTodo(getLoggerUserName(model),todo.getDesc(),todo.getTargetDate(),false);
		
		return "redirect:/to-do";
	}
}
