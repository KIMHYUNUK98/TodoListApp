package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<======== MENU ========>");
        System.out.println("- add (ADD New Data)");
        System.out.println("- del (Delete exist data)");
        System.out.println("- edit (Update exist data)");
        System.out.println("- ls (List all data)");
        System.out.println("- ls_name_asc (List Name in Ascending)");
        System.out.println("- ls_name_desc (List Name in Descending)");
        System.out.println("- ls_date (List by Date)");
        System.out.println("- exit (Exit the Program)");
    }
    public static void prompt() 
    {
    	System.out.print("\n Command > ");
    }
}
