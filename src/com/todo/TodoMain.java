package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		
		TodoUtil.loadlist(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				System.out.println("Add Complete!!!");
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				System.out.println("Delete Complete!!!");
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				System.out.println("Update Complete!!!");
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("Ascending Sort Complete!!!");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("Descending Sort Complete!!!");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("Sort by Date order Complete!!!");
				isList = true;
				break;
				
			case "help":
				Menu.displaymenu();
				break;

			case "exit":
				quit = true;
				break;
				
			default:
				System.out.println("You should check your command (- help) ");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
