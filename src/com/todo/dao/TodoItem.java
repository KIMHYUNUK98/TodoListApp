package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    private String category;
    private String due_date;
    private String is_completed;
    private int id;


    public TodoItem(String title, String desc, String cate, String due_date, String is_completed){
        this.title=title;
        this.desc=desc;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());
        this.due_date = due_date;
        this.category = cate;
        this.is_completed = is_completed;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void set_Is_completed(String is_completed) {
    	this.is_completed = is_completed;
    }
    
    public String get_Is_completed() {
    	return is_completed;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getCategory() {
    	return category;
    }
    
    public void setCategory(String category) {
    	this.category = category;
    }
    
    public String getDueDate() {
		// TODO Auto-generated method stub
		return due_date;
	}
    public void setDueDate(String due_date) {
    	this.due_date = due_date;
    }
 
    
    @Override
    public String toString() {
    	if(is_completed.equals("1")) {
    		return id + "." +" [" + category + "(V)" + "]" + "[" + title + "]" + "[" + desc + "]" + " - " + due_date +
    			" - " + current_date;
    	} else {
    		return id + "." +" [" + category + "]" + "[" + title + "]" + "[" + desc + "]" + " - " + due_date +
        			" - " + current_date;
    	}
    }
    
    public String toSaveString() {
    	return category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "##" + is_completed + "\n";
    }


}
