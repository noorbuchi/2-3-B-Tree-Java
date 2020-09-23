// Honor Code statement: This work is mine unless otherwise cited. - Noor Buchi

import experiment.ResultsTable;
import experiment.RunCampaign;
import tree.Tree23;
import java.io.*;
import java.util.*;

/**
 * This class is responsible for controlling the behavior of the program.
 * It imports the needed classes and packages, and creates the needed objects.
 * @author Noor Buchi
 */
public class ProgramController {

  // Main funciton
  public static void main(String[] args) {
    System.out.println("Welcome to 2-3 tree simulator!");
    System.out.println("Please make sure Graphviz is installed on your device before proceeding");
    System.out.println("You can find steps on how to install it here: https://graphviz.gitlab.io/download/ ");
    Scanner inputScanner = new Scanner(System.in);
    int choice = 0;
    // prevent user from entering an invalid choice
    while (choice < 1 || choice > 2){
      System.out.println("Please pick an operation: ");
      System.out.println("1: Build a 2-3 tree");
      System.out.println("2: Run a doubling experiment on a 2-3 tree");
      choice = inputScanner.nextInt();
    }
    if (choice == 1){
      buildATree();
    } else {
      doublingExperiment();
    }

    }

    public static void doublingExperiment(){
      System.out.println("-----------------------------------------------------------------------");
      Scanner inputScanner = new Scanner(System.in);
      int startingSize = 0;
      int campaignLength = 0;
      int choice = 0;
      while (choice != 1 && choice != 2 && choice != 3){
        System.out.println("Please enter which algorithm to run the expirement on:");
        System.out.println("1: Insert");
        System.out.println("2: Delete");
        System.out.println("3: Search");
        choice = inputScanner.nextInt();
      }
      while (startingSize < 100){
        System.out.println("Please enter a starting size for expirement (must be larger than 99)");
        startingSize = inputScanner.nextInt();
      }
      while (campaignLength < 3){
        System.out.println("Please enter how many times input will be doubled (must be larger than 3)");
        campaignLength = inputScanner.nextInt();
      }
      RunCampaign myCampaign = new RunCampaign(startingSize, campaignLength);
      System.out.println("-----------------------------------------------------------------------");
      if (choice == 1){
        System.out.println("Insert Expirement Results (time in nanoseconds):");
        System.out.println(myCampaign.runInsert().toString());
      } else if (choice == 2){
        System.out.println("Delete Expirement Results (time in nanoseconds):");
        System.out.println(myCampaign.runDelete().toString());
      } else if (choice == 3) {
        System.out.println("Search Expirement Results (time in nanoseconds):");
        System.out.println(myCampaign.runSearch().toString());
      }else {
        System.out.println("Unknown Input");
      }
    }

    /*
     * Takes user input to build a 2-3 tree and uses graphviz to create a
     * visual representation of the tree.
     */
    public static void buildATree(){
      System.out.println("\n");
      Tree23<Integer> myTree = new Tree23<Integer>();
      ArrayList<Integer> content = new ArrayList<Integer>();
      Scanner inputScanner = new Scanner(System.in);
      int choice = 0;
      while (true) {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("please select and operation to build a 2-3 tree (all input must be integers): ");
        System.out.println("1: insert an item");
        System.out.println("2: delete an item");
        System.out.println("3: search for an item");
        System.out.println("4: visualize current tree");
        System.out.println("5: exit program");
        choice = inputScanner.nextInt();
        if (choice == 5){ // exit
          System.out.println("Exiting...");
          return;
        } else if (choice == 1) { // insert
          boolean pass = false;
          int selectedInsert = 0;
          while (!pass){
            System.out.println("Please enter a number to insert: ");
            selectedInsert = inputScanner.nextInt();
            if (content.contains(selectedInsert)){ // prevent duplicate input
              System.out.println("INVALID INPUT: number already exists in the tree");
            } else {
              pass = true;
            }
          }
          long timeBefore = System.nanoTime();
          myTree.insert(selectedInsert);
          long timeAfter = System.nanoTime();
          long timeElapsed = timeAfter - timeBefore;
          content.add(selectedInsert); // used to keep track of existing input
          System.out.println("\n\t*----------------------------------------------------------*");
          System.out.println("\t|   " + selectedInsert + " was inserted into the tree in " + timeElapsed + " nanoseconds     |");
          System.out.println("\t|    Current content of the tree: " + content.toString());
          System.out.println("\t*----------------------------------------------------------*");

        } else if (choice == 2) { // delete
          if (myTree.getSize() == 0){
            System.out.println("ERROR: tree is empty, nothing to delete");
          } else {
            System.out.println("Please enter a number to delete: ");
            int selectedDelete = inputScanner.nextInt();
            long timeBefore = System.nanoTime();
            boolean successful = myTree.delete(selectedDelete);
            long timeAfter = System.nanoTime();
            long timeElapsed = timeAfter - timeBefore;
            if (successful){ // delete successful
              content.remove((Integer)selectedDelete);
              System.out.println("\n\t*----------------------------------------------------------*");
              System.out.println("\t|   " + selectedDelete + " was deleted from tree in " + timeElapsed + " nanoseconds     |");
              System.out.println("\t|    Current content of the tree: " + content.toString());
              System.out.println("\t*----------------------------------------------------------*");
            } else {
              System.out.println("ERROR: Value not found within the tree");
              System.out.println("\n\t*----------------------------------------------------------*");
              System.out.println("\t|   " + selectedDelete + " was NOT deleted from tree in " + timeElapsed + " nanoseconds     |");
              System.out.println("\t|    Current content of the tree: " + content.toString());
              System.out.println("\t*----------------------------------------------------------*");
            }
        }
        } else if (choice == 3) { // search
          if (myTree.getSize() == 0){
            System.out.println("ERROR: tree is empty, nothing to search for");
          } else {
            System.out.println("Please enter a number to search for: ");
            int selectedSearch = inputScanner.nextInt();
            Integer result;
            long timeBefore = System.nanoTime();
            result = myTree.search(myTree.getRoot(), selectedSearch);
            long timeAfter = System.nanoTime();
            long timeElapsed = timeAfter - timeBefore;
            if (result != null){ // element found
              System.out.println("\n\t*----------------------------------------------------------*");
              System.out.println("\t|    " + selectedSearch + " was found inside the tree in " + timeElapsed + " nanoseconds     |");
              System.out.println("\t*----------------------------------------------------------*");
            } else if (result == null) {  // element not found
              System.out.println("\n\t*----------------------------------------------------------*");
              System.out.println("\t|    " + selectedSearch + " was NOT found inside the tree in " + timeElapsed + " nanoseconds  |");
              System.out.println("\t*----------------------------------------------------------*");
            }
          }

        } else if (choice == 4) { // visualize
          if (myTree.getSize() < 3){ // Prevent toDot nul pointer exception
            System.out.println("ERROR: tree must have 3 or more elements to visualize");
          } else {
            // stores the tree attributes
            ArrayList<String> dotNotaion = myTree.toDot();
            System.out.println("\n");
            File myDot = new File("tree.dot");
            try {
              if (myDot.createNewFile()) {
                System.out.println("File created: " + myDot.getName());
              } else {
                System.out.println("File already exists.");
              }
            } catch (IOException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }

            try {
              FileWriter myWriter = new FileWriter("tree.dot");
              // write the heading of the file
              myWriter.write("graph Tree{" + "\n");
              // traverse through the attributes and write the content to file
              for (int i = 0; i < dotNotaion.size(); i++){
                myWriter.write(dotNotaion.get(i));
              }
              // Writes the closing bracket for file
              myWriter.write("}");
              myWriter.close();
              System.out.println("Successfully wrote to .dot file.");
            } catch (IOException e) {
              System.out.println("An error occurred.");
              e.printStackTrace();
            }
            compileDot();
            System.out.println("\n");

          }
        }

      }

    }

    public static void compileDot(){
      String compileCommand = "dot -Tpng tree.dot -o tree.png";
      try {
        // run the compileCommand
        Process compile = Runtime.getRuntime().exec(compileCommand);
      } catch (IOException e) {
        System.out.println("Failed!");
        e.printStackTrace();
      }
      System.out.println("Compiled successfully, image file created");
      System.out.println("Opening Image...");

      // Detect operating system
      String operatingSystem = System.getProperty("os.name").toLowerCase();
      if (operatingSystem.equals("linux")){
        // Allow the image to finish rendering before opening
        try {
              Thread.sleep(2000); // 2 seconds (2000ms) of pause before opening
          } catch (InterruptedException ie) {
              ie.printStackTrace();
          }
        String openCommand = "display tree.png";
        try {
          Process display = Runtime.getRuntime().exec(openCommand);
        } catch (IOException e) {
          System.out.println("Failed!");
          e.printStackTrace();
        }
      }


    }


}
