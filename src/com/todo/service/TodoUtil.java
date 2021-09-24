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
		
		String title, desc, cate, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n" + "TITLE: ");
		
		// Read title
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		
		// Read Category
		sc.nextLine();
		System.out.print("CATEGORY: ");
		cate = sc.next();
		if(list.isDuplicate(cate)) {
			System.out.println("Category can't be duplicate");
			return;
		}
		
		// Read Content
		sc.nextLine();
		System.out.print("CONTENT: ");
		desc = sc.nextLine().trim();
		
		// Read Due_date
		System.out.print("DUE DATE: ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, cate, due_date);
		list.addItem(t);
		System.out.println("Add Complete!!!");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[DELETE MENU]\n");
		System.out.print("Choose the number of menu > ");
		int number = sc.nextInt();
		
		for (TodoItem item : l.getList()) {
			if (number == l.indexOf(item) + 1) {
				System.out.println(l.indexOf(item)+1 + "." + item.toString());
				System.out.print("Check Again!! Delete Right away (y/n) > ");
				String data = sc.next();
				if(data.equals("y")) {
					l.deleteItem(item);
					System.out.println("Deleted!!");
				}
				else {
					System.out.println("Canceled!!");
				}
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[ UPDATE MENU ]\n");
		System.out.print("Choose the number of menu > ");
		int number = sc.nextInt();
		
		for(TodoItem item : l.getList()) {
			if(number == l.indexOf(item) + 1) {
				System.out.println(l.indexOf(item) + 1 +"." + item.toString());
				l.deleteItem(item);
			}
		}

		// Read new Title
		System.out.print("NEW TITLE > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			return;
		}
		
		// Read new Category
		System.out.print("NEW CATEGORY > ");
		String new_cate = sc.next().trim();
		if(l.isDuplicate(new_cate)) {
			System.out.println("Category can't be duplicate");
			return;
		}
		
		// Read new Content
		sc.nextLine();
		System.out.print("NEW CONTENT > ");
		String new_description = sc.nextLine().trim();
		
		// Read new Due_date
		System.out.print("NEW DUE DATE > ");
		String new_due_date = sc.nextLine().trim();
		
		// Add new title
		TodoItem t = new TodoItem(new_title, new_description, new_cate, new_due_date);
		l.addItem(t);
		System.out.println("Update Complete!!!");

	}

	public static void listAll(TodoList l) {
		System.out.println("\n[Whole TITLE, " + l.length() + "]");
		for (TodoItem item : l.getList()) {
			System.out.println(l.indexOf(item)+1 + "." + item.toString());
		}
	}

	public static void loadlist(TodoList l, String filename) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String oneline;
			int line = 0;
			while((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String cate = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String cur_date = st.nextToken();
				
				TodoItem item = new TodoItem(title, desc, cate, due_date);
				item.setCurrent_date(cur_date);
				l.addItem(item);
				line++;
			}
			
			br.close();
			System.out.println(line + " 개의 항목을 읽었습니다.\n");
		} catch(FileNotFoundException e) {
			System.out.println(filename + "파일이 없습니다.\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void saveList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		try {
			Writer w = new FileWriter(filename);
			
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void find(String data, TodoList l) {
		// TODO Auto-generated method stub
		int count = 0;
		for(TodoItem item : l.getList()) {
			String line = item.toString();
			if(line.contains(data)) {
				System.out.println(l.indexOf(item)+1 + "." + item.toString());
				count++;
			}
		}
		System.out.println("Found " + count + " things of data. ");
	}

	public static void find_cate(String cate, TodoList l) {
		// TODO Auto-generated method stub
		int count = 0;
		for(TodoItem item : l.getList()) {
			String line = item.getCategory();
			if(line.contains(cate)) {
				System.out.println(l.indexOf(item)+1 + "." + item.toString());
				count++;
			}
		}
		System.out.println("Found " + count + " things of data. ");
	}

	public static void ls_cate(TodoList l) {
		// TODO Auto-generated method stub
		HashSet<String> hs = new HashSet<String>(); 

		for(TodoItem item : l.getList()) {
			String line = item.getCategory();
			hs.add(line);
		}
		
		System.out.print("CATEGORY = ");
		Iterator iter = hs.iterator();
		while(iter.hasNext()) {
			System.out.print(iter.next() + " ");
		}
		System.out.println("\nFound " + hs.size() + " things of data. ");
	}
}
