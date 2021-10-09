package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<======== MENU ========>");
        System.out.println("- add (ADD New Data)");
        System.out.println("- del (Delete exist data)");
        System.out.println("- edit (Update exist data)");
        System.out.println("- find <keyword>");
        System.out.println("- find_cate <keyword>");
        System.out.println("- ls (List all data)");
        System.out.println("- ls_cate (List Category with only once)");
        System.out.println("- ls_name_asc (List Name in Ascending)");
        System.out.println("- ls_name_desc (List Name in Descending)");
        System.out.println("- ls_date (List by Date)");
        System.out.println("- ls_date_desc (Reverse by Date)");
        System.out.println("- comp (Check the list you completed)");
        System.out.println("- ls_comp (List your completed thingks)");
        System.out.println("- exit (Exit the Program)");
    }
    public static void prompt() 
    {
    	System.out.print("\n Command > ");
    }
}
