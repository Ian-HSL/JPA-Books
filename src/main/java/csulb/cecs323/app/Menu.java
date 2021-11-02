package csulb.cecs323.app;

public class Menu {
    Menu(){

    }

    public static void clearConsole(){
        System.out.println("\f");
        System.out.println("-------------------------");
    }

    public static void displayOptions(){
        System.out.println("1. Add new objects: \n" +
                           "2. List information \n" +
                           "3. Delete a book \n" +
                           "4. Update a book\n" +
                           "5. List primary keys");
    }

    public static void addEntityOptions(){
        System.out.println("1. Add new Authoring Entity\n" +
                           "2. Add a new publisher\n" +
                           "3. Add a new book");
    }

    public static void displayPrimaryKeyOptions(){
        System.out.println("1. Publishers\n" +
                           "2. Books(title + ISBN)\n" +
                           "3. Authoring entites(w/type)");
    }

    public static void addAuthoringEntity(){
        System.out.println("Add an authoring entity: ");
        System.out.println("1. Add WritingGroup\n" +
                           "2. Add Individual Author\n" +
                           "3. Add Ad Hoc Team\n" +
                           "4. Add an individual author to an existing Ad Hoc Team");
    }
}
