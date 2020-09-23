// Honor Code statement: This work is mine unless otherwise cited. - Noor Buchi

package experiment;

import tree.Tree23;
import experiment.ResultsTable;

import java.util.Random;

public class RunCampaign {


  /** The growth factor for input when running an experiment campaign. */
  private static final int INPUT_GROWTH_FACTOR = 2;

  /** The indentation level for the output. */
  private static final String IDENTATION = "  ";
  private int startingSize;
  private int campaignLength;

  public RunCampaign(int size, int length){
    startingSize = size;
    campaignLength = length;
  }

  /** Randomly generate data to use for an experiment. */
  public Integer[] generateRandomData(int size) {
    Random random = new Random();
    Integer[] generated = new Integer[size];
    for (int i = 0; i < generated.length; i++) {
      generated[i] = (Integer)random.nextInt();
    }
    return generated;
  }

  public ResultsTable runInsert() {
    System.out.println("Running Insert Expirement");
    int campaignRound = 0;
    int currentInputSize = startingSize;
    ResultsTable results = new ResultsTable(campaignLength);
    while (campaignRound < campaignLength) {
      Tree23<Integer> myTree = new Tree23<Integer>();
      Integer[] generatedInput = generateRandomData(currentInputSize);
      myTree.insert(generatedInput); // Preparing data
      // generate a random number to insert
      Random targetGenerator = new Random();
      Integer target = (Integer)targetGenerator.nextInt();
      // time the process of inserting this number
      long timeBefore = System.nanoTime();
      myTree.insert(target);
      long timeAfter = System.nanoTime();
      long timeElapsed = timeAfter - timeBefore;
      results.addResult((long)currentInputSize, timeElapsed);
      System.out.println(IDENTATION + "Running round " + campaignRound
          + " with input size " + currentInputSize);
      currentInputSize = currentInputSize * INPUT_GROWTH_FACTOR;
      campaignRound++;
    }
    return results;
  }


  public ResultsTable runSearch() {
    System.out.println("Running Search Expirement");
    int campaignRound = 0;
    int currentInputSize = startingSize;
    ResultsTable results = new ResultsTable(campaignLength);
    while (campaignRound < campaignLength) {
      Tree23<Integer> myTree = new Tree23<Integer>();
      Integer[] generatedInput = generateRandomData(currentInputSize);
      myTree.insert(generatedInput);
      Random targetGenerator = new Random();
      // get a random item from the generated data
      Integer target = generatedInput[targetGenerator.nextInt(generatedInput.length)];
      long timeBefore = System.nanoTime();
      myTree.search(target);
      long timeAfter = System.nanoTime();
      long timeElapsed = timeAfter - timeBefore;
      results.addResult((long)currentInputSize, timeElapsed);
      System.out.println(IDENTATION + "Running round " + campaignRound
          + " with input size " + currentInputSize);
      currentInputSize = currentInputSize * INPUT_GROWTH_FACTOR;
      campaignRound++;
    }
    return results;
  }


  public ResultsTable runDelete() {
    System.out.println("Running Delete Expirement");
    int campaignRound = 0;
    int currentInputSize = startingSize;
    ResultsTable results = new ResultsTable(campaignLength);
    while (campaignRound < campaignLength) {
      Tree23<Integer> myTree = new Tree23<Integer>();
      Integer[] generatedInput = generateRandomData(currentInputSize);
      myTree.insert(generatedInput);
      Random targetGenerator = new Random();
      // get a random item from the generated data
      Integer target = generatedInput[targetGenerator.nextInt(generatedInput.length)];
      long timeBefore = System.nanoTime();
      myTree.delete(target);
      long timeAfter = System.nanoTime();
      long timeElapsed = timeAfter - timeBefore;
      results.addResult((long)currentInputSize, timeElapsed);
      System.out.println(IDENTATION + "Running round " + campaignRound
          + " with input size " + currentInputSize);
      currentInputSize = currentInputSize * INPUT_GROWTH_FACTOR;
      campaignRound++;
    }
    return results;
  }
}
