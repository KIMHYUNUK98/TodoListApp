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
		
		String title, desc, cate, due_date, is_completed;
		Scanner sc = new Scanner(System.in);
		
		// Read title
		System.out.print("[Add Menu]\n" + "TITLE: ");
		title = sc.next();
		if(list.isDuplicate(title)) {
			System.out.println("Name is already existed!!");
			return;
		}

		// Read Category
		sc.nextLine();
		System.out.print("CATEGORY > ");
		cate = sc.next();

		// Read Content
		sc.nextLine();
		System.out.print("CONTENT > ");
		desc = sc.nextLine().trim();
		
		// Read Due_date
		System.out.print("DUE DATE > ");
		due_date = sc.nextLine().trim();
		
		// Read Is_completed
		System.out.print("Is completed? > ");
		is_completed = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, cate, due_date, is_completed);
		if(list.addItem(t) > 0) {
			System.out.println("Add Complete!!!");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[DELETE MENU]\n");
		System.out.print("Choose the number of menu > ");
		int index = sc.nextInt();
		
		if(l.deleteItem(index) > 0)
			System.out.println("Delete Complete!!");
	
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n[ UPDATE MENU ]\n");
		System.out.print("Choose the number of menu > ");
		int index = sc.nextInt();

		// Read new Title
		System.out.print("NEW TITLE > ");
		String new_title = sc.next().trim();
		
		// Read new Category
		System.out.print("NEW CATEGORY > ");
		String new_cate = sc.next().trim();
		
		// Read new Content
		sc.nextLine();
		System.out.print("NEW CONTENT > ");
		String new_description = sc.nextLine().trim();
		
		// Read new Due_date
		System.out.print("NEW DUE DATE > ");
		String new_due_date = sc.nextLine().trim();
		
		// Read is_completed
		System.out.print("Is Completed > ");
		String is_completed = sc.nextLine().trim();
		
		// Add new title
		TodoItem t = new TodoItem(new_title, new_description, new_cate, new_due_date, is_completed);
		t.setId(index);
		if(l.updateItem(t) > 0)
			System.out.println("Update Complete!!!");
	}
	
	public static void completeItem(TodoList l, int number) {
		if(l.completeItem(number) > 0)
			System.out.println("Complete your Mission!!");
	}

	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.println(item + " ");
			count++;
		}
		System.out.printf("\n %d Categories in Here\n", count);
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
				String is_completed = st.nextToken();
				
				TodoItem item = new TodoItem(title, desc, cate, due_date, is_completed);
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

	public static void find(String keyword, TodoList l) {
		// TODO Auto-generated method stub
		int count = 0;
		for(TodoItem item : l.getList()) {
			String line = item.toString();
			if(line.contains(keyword)) {
				System.out.println(item.toString());
				count++;
			}
		}
		System.out.println("Found " + count + " things of data. ");
	}

	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n Found %d things in Here", count);
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
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[Total %d]\n", l.getCount());
		for(TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}

	public static void listAll(TodoList l) {
		// TODO Auto-generated method stub
		int count = 0;
		System.out.println("[All list]");
		for(TodoItem item : l.getList()) {
			System.out.println(item + " ");
			count++;
		}
		System.out.printf("\n[ENTRY : %d]\n", count);
	}
	
	
	public static void complistAll(TodoList l) {
		int count = 0;
		for(TodoItem item : l.getList()) {
			String completed = item.get_Is_completed();
			if(completed.equals("1")) {
				System.out.println(item.toString());
				count++;
			}
		}
		System.out.printf("\n %d Completed!!\n", count);
	}
}
