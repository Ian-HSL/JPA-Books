package csulb.cecs323.app;

public class Menu {
    Menu(){

    }

    public static void displayOptions(){
        System.out.println("1. Add new objects: \n" +
                           "2. List information \n" +
                           "3. Delete a book \n" +
                           "4. Update a book\n" +
                           "5. List primary keys\n" +
                           "0. Quit");
    }

    public static void addEntityOptions(){
        System.out.println("1. Add new Authoring Entity\n" +
                           "2. Add a new publisher\n" +
                           "3. Add a new book\n" +
                           "0. Quit");
    }

    public static void listingInfoOptions(){
        System.out.println("1. Publisher\n" +
                           "2. Book\n" +
                           "3. Writing Group\n" +
                           "0. Quit");
    }

    public static void addAuthoringEntity(){
        System.out.println("Add an authoring entity: ");
        System.out.println("1. Add Writing Group\n" +
                           "2. Add Individual Author\n" +
                           "3. Add Ad Hoc Team\n" +
                           "4. Add an individual author to an existing Ad Hoc Team" +
                           "0. Quit");
    }

    public static void displayPrimaryKeyOptions(){
        System.out.println("1. Publishers\n" +
                           "2. Books\n" +
                           "3. Authoring entites(with entity type)\n" +
                           "0. Quit" );
    }
}
