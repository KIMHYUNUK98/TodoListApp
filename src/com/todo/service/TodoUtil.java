package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "TITLE: ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		
		sc.nextLine();
		System.out.print("CONTENT: ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n========= DELETE MENU ==========\n");
		System.out.print("TITLE: ");
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n========= DELETE MENU ==========\n");
		System.out.print("DELETE TITLE: ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("title doesn't exist");
			return;
		}

		System.out.print("NEW TITLE");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			return;
		}
		
		sc.nextLine();
		System.out.print("CONTENT: ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("item updated");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[Whole TITLE]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void loadlist(TodoList l, String string) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(string));
			
			String oneline;
			int line = 0;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				
				TodoItem item = new TodoItem(title, desc);
				l.addItem(item);
				line++;
			}
			
			br.close();
			System.out.println(line + " 개의 항목을 읽었습니다.\n");
		} catch(FileNotFoundException e) {
			System.out.println("todolist.txt 파일이 없습니다.\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void saveList(TodoList l, String string) {
		// TODO Auto-generated method stub
		try {
			Writer w = new FileWriter(string);
			
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
