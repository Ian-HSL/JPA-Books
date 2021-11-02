/*
 * Licensed under the Academic Free License (AFL 3.0).
 *     http://opensource.org/licenses/AFL-3.0
 *
 *  This code is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, other than educational.
 *
 *  2018 Alvaro Monge <alvaro.monge@csulb.edu>
 *
 */

package csulb.cecs323.app;

// Import all of the entity classes that we have written for this application.
import csulb.cecs323.BusinessLayer.BusinessLayer;
import csulb.cecs323.BusinessLayer.IBusinessLayer;
import csulb.cecs323.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * A simple application to demonstrate how to persist an object in JPA.
 * <p>
 * This is for demonstration and educational purposes only.
 * </p>
 * <p>
 *     Originally provided by Dr. Alvaro Monge of CSULB, and subsequently modified by Dave Brown.
 * </p>
 */
public class BooksApp {
   /**
    * The Logger can easily be configured to log to a file, rather than, or in addition to, the console.
    * We use it because it is easy to control how much or how little logging gets done without having to
    * go through the application and comment out/uncomment code and run the risk of introducing a bug.
    * Here also, we want to make sure that the one Logger instance is readily available throughout the
    * application, without resorting to creating a global variable.
    */
   private static final Logger LOGGER = Logger.getLogger(Book.class.getName());

   private static List<Book> bookList;
   private static List<Publisher> publisherList;
   private static List<AuthoringEntity> AEList;

   private static List<AuthoringEntity> AHTList;
   private static List<AuthoringEntity> individualAuthorsList;

   private static IBusinessLayer businessLayer;

   public static void main(String[] args) {
      // Create an instance of CarClub and store our new EntityManager as an instance variable
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("BooksApp");
      EntityManager manager = factory.createEntityManager();
      EntityTransaction tx = manager.getTransaction();

      Scanner in = new Scanner(System.in);
      Menu.displayOptions();

      int menuChoice = Validator.checkInput(in,1,5);
      switch(menuChoice)
      {
         //add something
         case 1:
         {
            int addSomethingChoice = Validator.checkInput(in,1,3);
            switch(addSomethingChoice)
            {
               //add AE
               case 1:
               {
                  Menu.addAuthoringEntity();
                  int aeType = Validator.checkInput(in,1,4);
                  switch(aeType)
                  {
                     // Add writing group
                     case 1:
                     {
                        WritingGroup newWritingGroup = new WritingGroup();

                        Menu.clearConsole();

                        System.out.println("Add a WritingGroup! : ");

                        System.out.println("Enter Name: ");
                        newWritingGroup.setName(Validator.checkStringLength(in,80));

                        System.out.println("Enter Email: ");
                        newWritingGroup.setEmail(Validator.checkStringLength(in,30));

                        System.out.println("Enter Head Writer: ");
                        newWritingGroup.setHeadWriter(Validator.checkStringLength(in,80));

                        System.out.println("Enter Year formed:  ");
                        newWritingGroup.setYearFormed(in.nextInt());

                        businessLayer.addAuthoringEntity(newWritingGroup);
                        break;
                     }
                     // Add inidividual author
                     case 2:
                     {
                        IndividualAuthors newIndividualAuthor = new IndividualAuthors();

                        Menu.clearConsole();

                        System.out.println("Adding Individual Author!!");

                        System.out.println("Name: ");
                        newIndividualAuthor.setName(Validator.checkStringLength(in,80));

                        System.out.println("Email: ");
                        newIndividualAuthor.setEmail(Validator.checkStringLength(in,30));

                        businessLayer.addAuthoringEntity(newIndividualAuthor);
                        break;
                     }
                     // Add ad hoc team
                     case 3:
                     {
                        AdHocTeam newAdhocTeam = new AdHocTeam();

                        Menu.clearConsole();
                        System.out.println("Add an Ad Hoc Team Group! : ");

                        System.out.println("Name: ");
                        newAdhocTeam.setName(Validator.checkStringLength(in,80));

                        System.out.println("Email: ");
                        newAdhocTeam.setEmail(Validator.checkStringLength(in,30));

                        businessLayer.addAuthoringEntity(newAdhocTeam);
                        break;
                     }
                     // Add individual author to existing ad hoc team
                     case 4:
                     {
                        AdHocTeamsMember newAdHocTeamsMember = new AdHocTeamsMember();

                        // Initialize Ad hoc teams and individual authors lists
                        List<AdHocTeam> adHocTeamsList = businessLayer.getAllAdHocTeams();
                        List<IndividualAuthors> individualAuthorsList = businessLayer.getAllIndividualAuthors();

                        System.out.println("Ad Hoc Teams: ");
                        for(AdHocTeam adHocTeams : adHocTeamsList){
                           System.out.println(adHocTeams);
                        }

                        int adHocTeamsChoice = Validator.checkInput(in, 1, adHocTeamsList.size());

                        newAdHocTeamsMember.setAdHocTeam(adHocTeamsList.get(adHocTeamsChoice));

                        System.out.println("Individual Teams: ");
                        for(IndividualAuthors individualAuthors : individualAuthorsList){
                           System.out.println(individualAuthors);
                        }

                        int individualAuthorsChoice = Validator.checkInput(in, 1, individualAuthorsList.size());

                        newAdHocTeamsMember.setIndividualAuthor(individualAuthorsList.get(individualAuthorsChoice));

                        businessLayer.addAdHocTeamMember(newAdHocTeamsMember);
                     }
                  }
                  break;
               }
               //add publisher
               case 2:
               {
                  Publisher newPublisher = new Publisher();

                  System.out.println("Add a publisher: ");

                  System.out.println("Name: ");
                  newPublisher.setName(Validator.checkStringLength(in,80));

                  System.out.println("Phone: ");
                  newPublisher.setPhone(Validator.checkStringLength(in,24));

                  System.out.println("Email: ");
                  newPublisher.setEmail(Validator.checkStringLength(in,80));

                  businessLayer.addPublisher(newPublisher);
                  break;
               }
               //add book
               case 3:
               {
                  //initialize publisher and author entity lists
                  List<Publisher> publisherList = businessLayer.getAllPublishers();
                  List<AuthoringEntity> authoringEntityList = businessLayer.getAllAuthoringEntities();
                  Book newBook = new Book();

                  Menu.clearConsole();
                  System.out.println("Add a book: ");
                  if (publisherList.size() == 0){
                     System.out.println("Empty publisher list, so add a publisher first! Thank you!");
                     break;
                  }

                  // display list of publishers
                  for(Publisher publisher : publisherList){
                     System.out.println(publisher);
                  }

                  int publisherChoice = Validator.checkInput(in, 1, publisherList.size());

                  newBook.setPub(publisherList.get(publisherChoice - 1));

                  if (AEList.size() == 0){
                     System.out.println("Empty AE list, so add an authoring entity first! Thank you!");
                     break;
                  }

                  for(AuthoringEntity authoringEntity : authoringEntityList){
                     System.out.println(authoringEntity);
                  }

                  int authoringEntityChoice = Validator.checkInput(in, 1, publisherList.size());

                  newBook.setAE(authoringEntityList.get(authoringEntityChoice));

                  System.out.println("ISBN: ");
                  newBook.setISBN(Validator.checkStringLength(in,17));

                  System.out.println("Title: ");
                  newBook.setTitle(Validator.checkStringLength(in,80));

                  System.out.println("Year published: ");
                  newBook.setYearPublished(in.nextInt());

                  businessLayer.addBook(newBook);

                  break;
               }
            }
            break;
         }

         //list info about object
         case 2:
         {
            System.out.println("Listing information about: ");
            int listMenuChoice = Validator.checkInput(in,1,3);
            switch(listMenuChoice)
            {
               //show list of publishers and select one?
               case 1:
               {
                  break;
               }
               //show list of books and select one?
               case 2:
               {
                  break;
               }
               //show list of writing groups and select 1?
               case 3:
               {
                  break;
               }
            }
            break;
         }

         //delete book
         case 3:
         {
            deleteBook(in);
            break;
         }

         //update book
         case 4:
         {
            //show list of books. and select 1.

            System.out.println("New AE for book: ");
            break;
         }

         //list the primary key of all rows of something
         case 5:
         {

            int primaryKeyInfoChoice = Validator.checkInput(in,1,3);

            switch(primaryKeyInfoChoice)
            {
               //publishers
               case 1:
               {
                  break;
               }
               //books
               case 2:
               {
                  break;
               }
               //authoring entities
               case 3:
               {
                  break;
               }
            }
            break;
         }
      }

      LOGGER.fine("End of Transaction");
      //*/

   } // End of the main method



   private Publisher selectPublisher (Scanner in, List<Publisher> pubs)
   {
      List<Publisher> publisherList = businessLayer.getAllPublishers();


      System.out.println("Publishers:");
      for (int i  = 0; i < pubs.size(); i++)
      {
         System.out.println(i + " " + pubs.get(i));
      }

      int pickedPub = -1;
      while(pickedPub < 0 || pickedPub >= pubs.size()) {
         System.out.println("Enter Selection: ");
         pickedPub = in.nextInt();
      }
      System.out.println(pickedPub + ": " + pubs.get(pickedPub));
      return pubs.get(pickedPub);
   }

   private AuthoringEntity selectAE (Scanner in, List<AuthoringEntity> AE)
   {
      System.out.println("Authoring Entity:");
      for (int i  = 0; i < AE.size(); i++)
      {
         if(AE.get(i) instanceof WritingGroup)
         {

            System.out.println(i + " " + ((WritingGroup) AE.get(i)));
         }
         if(AE.get(i) instanceof IndividualAuthors)
         {

            System.out.println(i + " " + ((IndividualAuthors) AE.get(i)));
         }
         if(AE.get(i) instanceof AdHocTeam)
         {

            System.out.println(i + " " + ((AdHocTeam) AE.get(i)));
         }
      }

      //Scanner in = new Scanner(System.in);
      int pickedAE = -1;
      while(pickedAE < 0 || pickedAE >= AE.size()) {
         System.out.println("Enter Selection: ");
         pickedAE = in.nextInt();
      }
      System.out.println(pickedAE + ": " + AE.get(pickedAE));
      return AE.get(pickedAE);

   }

   private static void deleteBook(Scanner in) {
      boolean remove = true;

      String title;
      String publisherName;
      String authoringEntityName;

      while(remove){
         System.out.print("Please enter title: ");
         title = in.nextLine();
         System.out.print("Please enter publisher name: ");
         publisherName = in.nextLine();
         System.out.print("Please enter authoring entity name: ");
         authoringEntityName = in.nextLine();

         businessLayer.deleteBook(title, publisherName, authoringEntityName);
      }
   }

   private static void displayList(Scanner in){

   }

} // End of Books class
