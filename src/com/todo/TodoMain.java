package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		l.importData("todolist.txt");
		boolean isList = false;
		boolean quit = false;
		
		//TodoUtil.loadlist(l, "todolist.txt");
		Menu.displaymenu();
		do {
			Menu.prompt();
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "find":
				String data = sc.nextLine().trim();
				TodoUtil.find(data,l);
				break;

			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "ls_name_asc":
				System.out.println("Ascending Sort Complete!!!");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("Descending Sort Complete!!!");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("Sort by Date order Complete!!!");
				TodoUtil.listAll(l, "due_date", 1);
				break;
			
			case "ls_date_desc":
				System.out.println("Reverse Sort by Date Order Complete!!!");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_cate" :
				TodoUtil.listCateAll(l);
				break;
				
			case "comp":
				int number = sc.nextInt();
				TodoUtil.completeItem(l, number);
				break;
				
			case "ls_comp":
				TodoUtil.complistAll(l);
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
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
