// Honor Code statement: This work is mine unless otherwise cited. - Noor Buchi

package tree;

import java.util.*;
/**
 *
 * This class constucts a 2-3 Tree with some of the basic
 * operations such as insert, delete, and search
 * It also uses nested classes to create nodes
 * @author Noor Buchi
 */

public class Tree23<E extends Comparable<E>> {

  /**
   * Node of a 2-3 list contains left and right value
   * It also contains the parent, left, middle and right children as other nodes
   * All of the instance variables are set to null by default
   */

  private static class Node<E extends Comparable<E>> {
    private E leftVal;
    private E rightVal;

    private Node<E> parent;
    private Node<E> leftChild;
    private Node<E> midChild;
    private Node<E> rightChild;

    /**
     *
     * Constructor for the Node class only needs an int value to create node
     * By default the new value will be assigned to the leftVal
     * instance variable
     */
    public Node(E element) {
      leftVal = element;
      rightVal = null;
      parent = null;
      leftChild =  null;
      midChild = null;
      rightChild = null;
    }

     /**
      * Getter methods to access the instance variables
      */
    public E getLeftVal() {
      return leftVal;
    }

    public E getRightVal() {
      return rightVal;
    }

    public Node<E> getParent() {
      return parent;
    }

    public Node<E> getLeftChild() {
      return leftChild;
    }

    public Node<E> getMidChild() {
      return midChild;
    }

    public Node<E> getRightChild() {
      return rightChild;
    }

    /**
     * Setter methods to change the value of the instance variables
     */

    public void setLeftVal(E element){
      leftVal = element;
    }

    public void setRightVal(E element){
      rightVal = element;
    }

    public void setLeftChild(Node<E> newLeft){
      leftChild = newLeft;
    }

    public void setRightChild(Node<E> newRight){
      rightChild = newRight;
    }

    public void setMidChild(Node<E> newMid){
      midChild = newMid;
    }

    public void setParent(Node<E> newParent){
      parent = newParent;
    }

    /**
     * Helper methods to check on the status of the node
     * such as having one or two values
     * or being a node with two or three children
     */
    public boolean isLeaf(){
      if (leftChild == null && midChild == null && rightChild == null){
        return true;
      } else {
        return false;
      }
    }

    // check if the node has only one value in leftVal
    public boolean hasOneVal(){
      if (leftVal != null && rightVal == null) {
        return true;
      } else {
        return false;
      }
    }

    // check if the node has two values in leftVal and rightVal
    public boolean hasTwoVals(){
      if (leftVal != null && rightVal != null) {
        return true;
      } else {
        return false;
      }
    }

    /*
     * return -1 if left child
     * returns 0 if middle child
     * returns 1 if right child
     * returns null if it has no parent
     */
    public Integer whichChild(){
      if (this.getParent() == null){ // parent does not exist
        return null;
      } else {
        if (this.getParent().hasOneVal()){ // has only one other sibling
          if (this.getParent().getLeftChild() == this){ // check if it's leftChild
            return -1;
          } else if (this.getParent().getRightChild() == this){
            return 1;
          } else {
            System.out.println("ERROR: unknown child");
            return null;
          }
        } else { // has two other siblings
          if (this.getParent().getLeftChild() == this){ // check if it's leftChild
            return -1;
          } else if (this.getParent().getRightChild() == this){
            return 1;
          } else if (this.getParent().getMidChild() == this){
            return 0;
          } else {
            System.out.println("ERROR: unknown child");
            return null;
          }
        }
      }
    }


    /*
     * returns the midChild of parent if current node is leftChild of a twoVal node
     * otherwise it returns the rightChild of parent if parent hasOneVal
     * returns null if no siblings are found
     * NOTE: if currentNode is a middle childe, it returns the left child of the parent
     */
    public Node<E> getClosestSibling(){
      Integer location = this.whichChild();
      if (location == null) { // no parent thus no siblings
        return null;
      } else {
        if (this.getParent().hasOneVal()){ // no middle sibling
          if (location == -1){ // currentNode is a left
            return this.getParent().getRightChild();
          } else if (location == 1){ // currentNode is a right
            return this.getParent().getLeftChild();
          } else {
            System.out.println("Error finding sibling");
            return null;
          }
        } else { // has middle sibling
          if (location == -1){ // currentNode is a left
            return this.getParent().getMidChild(); // return mid sibling
          } else if (location == 1){ // currentNode is a right
            return this.getParent().getMidChild(); // return mid sibling
          } else if (location == 0) { // currentNode is mid
            return this.getParent().getLeftChild(); // return left sibling
          } else {
            System.out.println("Error finding sibling");
            return null;
          }
        }
      }
    }

    // Swapping method used for making sure the values are sorted in ascending
    // Order from left value to right value. Only used when there are two values
    public void swapLeftRightVal(){
      E temp = leftVal;
      leftVal = rightVal;
      rightVal = temp;
    }

    /** Swaps the values if the leftVal is greater than the rightVal
     * Uses the method compareTo() allowed by extending the class to comparable
     * compareTo example:
     * a.compareTo(b) returns:
     * 0 if a == b
     * -1 if a < b
     * 1 if a > b
     */
    public void sortVal(){
      // Check if only left and right values need to be sorted
      if (leftVal!= null && rightVal != null) {
        // Check if the values are in wrong order.
        if (leftVal.compareTo(rightVal) == 1) {
          swapLeftRightVal();
        }
      }
    }

    /*
     * Checks if the element exists in the node and sets its placeholder to null
     * if removing the leftVal of the node, it shifts the rightVal in its place
     * removing the rightVal would just set it to null
     * returns false if the element was not found in the node.
     * returns true if successfully removed
     * NOTE: removing a value could cause the tree structure to be altered
     * so it should be immediately fixed after removal
     */
    public boolean removeVal(E element){
      if (element.compareTo(this.getLeftVal()) == 0){ // element is on the left
        // leftVal takes the value of rightVal
        this.setLeftVal(this.getRightVal());
        // rightVal becomes null
        this.setRightVal(null);
        return true;
      } else if ((element.compareTo(this.getRightVal()) == 0)) { // element is on the right
        // no shifts needed
        this.setRightVal(null);
        return true;
      } else {
        return false;
      }
    }

    // Method designed to pring the content of the node
    // Must be used inside a print statement
    public String toString(){
      String output = "LV: " + leftVal + "\n" +
                      "RV: " + rightVal + "\n" +
                      "LC: " + leftChild + "\n" +
                      "MC: " + midChild + "\n" +
                      "RC: " + rightChild + "\n" +
                      "NODE with " + leftVal + " OVER \n";
      return output;
    }

    /*
     * used to get the lef and right values depending on which is present
     * NOTE: it intentionally add quotation marks to be used in toDot()
     */
    public String getValuesString(){
      String result = "\"";
      if (this.hasOneVal()){
        result = result + leftVal + "\"";
      } else {
        result = result + leftVal + " , " + rightVal + "\"";
      }
      return result;
    }
  } // End of Node class


  // Tree instance variables
  // NOTE: size stands for the number of values and not the number of nodes
  // Since one node can have up to two values
  private int size;
  private Node<E> root;

  // Tree constructor does not require an argument, simply creates empty tree
  public Tree23() {
    root = null;
    size = 0;
  }

  public Node<E> getRoot(){
    return root;
  }

  public void setRoot(Node<E> newRoot){
    root = newRoot;
  }

  public int getSize(){
    return size;
  }

  /*
   * Gets the next child depending on the value of the argument node
   * Only makes one step, does not include loops
   * returns a node object for the appropriate child
   */
  private Node<E> traverse(Node<E> node, E element) {
    Node<E> currentNode = node;
    // Compares element only considering leftVal
    if (currentNode.hasOneVal()){
      // Check if the element is less than or equal to the left value
      if (element.compareTo(currentNode.getLeftVal()) <= 0) {
        // Traverse left if the inserted element is less than leftVal
        // inclusive to left
        currentNode = currentNode.getLeftChild();
      } else if (element.compareTo(currentNode.getLeftVal()) == 1) {
        // Check and traverse right if the element is greater than leftVal
        currentNode = currentNode.getRightChild();
      }
      else {
        System.out.println("ERROR! Unknown Comparison Result");
      }
    // Compare element with consideration to both left and right values
    } else if (currentNode.hasTwoVals()) {
      if (element.compareTo(currentNode.getLeftVal()) <= 0) {
        // Traverse left if the inserted element is less than leftVal
        // inclusive to left
        currentNode = currentNode.getLeftChild();
      } else if (element.compareTo(currentNode.getLeftVal()) == 1 && element.compareTo(currentNode.getRightVal()) <= 0) {
        // Traverse middle if the element is between leftVal and rightVal
        // inclusive to right
        // First condition checks if the element is greater than leftVal
        // Second condition checks if the element is less than or equal to rightVal
        currentNode = currentNode.getMidChild();
      } else if (element.compareTo(currentNode.getRightVal()) == 1) {
        // Traverse right if element is greater than the rightVal
        currentNode = currentNode.getRightChild();
      } else {
        System.out.println("ERROR! Unknown comparason result!");
      }
    } else {
      System.out.println("ERROR! Unknown number of values");
    }
    return currentNode;
  }

  /*
   * Searches for the element value in the tree starting from beginNode
   * Returns null if the element is not found
   * If found it retruns the element
   */
  public E search(Node<E> beginNode, E element){
    Node<E> currentNode = beginNode;
    // Uses iterative constuct to search
    while (true){
      // check if there is a match with the left value
      if (element.compareTo(currentNode.getLeftVal()) == 0){
        return currentNode.getLeftVal();
      // check if there is a match with the right value
      } else if (currentNode.getRightVal() != null && element.compareTo(currentNode.getRightVal()) == 0) {
        return currentNode.getRightVal();
      // breaks and returns a null if the element wasn't found and current node
      // is a leaf node. Done to prevent null pointer exception
      } else if (currentNode.isLeaf()){
        break;
      // traverse further if no match is present and if there is a next node
      } else {
        currentNode = traverse(currentNode, element);
      }
    }
    return null;
  }


  /*
   * Searches for the element value in the tree starting from beginNode
   * Returns null if the element is not found
   * If found it retruns the node containing the element
   */
  public Node<E> searchNode(Node<E> beginNode, E element){
    Node<E> currentNode = beginNode;
    // Uses iterative constuct to search
    while (true){
      // check if there is a match with the left value
      if (element.compareTo(currentNode.getLeftVal()) == 0){
        return currentNode;
      // check if there is a match with the right value
      } else if (currentNode.getRightVal() != null && element.compareTo(currentNode.getRightVal()) == 0) {
        return currentNode;
      // breaks and returns a null if the element wasn't found and current node
      // is a leaf node. Done to prevent null pointer exception
      } else if (currentNode.isLeaf()){
        break;
      // traverse further if no match is present and if there is a next node
      } else {
        currentNode = traverse(currentNode, element);
      }
    }
    return null;
  }

  /*
   * Use the traverse method to insert the element in the appropriate node
   * does not fix the tree from bottom up if the formatting was wrong
   */
  public void insert(E element){
    // Condition for inserting the first element when the size is zero
    // Simply assigns the value of the root as that element
    if (root == null){
      root = new Node<E>(element);
    } else {
      Node<E> currentNode = root;
      // Condition for traversal loop. Loop will only stop if the node
      // Has null pointers in all directions being a leaf node
      while (!currentNode.isLeaf()) {
        currentNode = traverse(currentNode, element);
      }
      put(element, currentNode);
    }
    // NOTE: it is important to know where the size is being incremented
    // Increment size to account for the newely inserted node
    size++;
  }

  /*
   * Loops through the inputArray and inserts every element in the order they
   * appear. Specifically designed for the doubling experiment.
   */
  public void insert(E[] inputArray){
    for (int i = 0; i < inputArray.length; i++){
      this.insert(inputArray[i]);
    }
  }

  /*
   * Assumes that the search will occur from root
   */
  public void search(E item){
    this.search(this.getRoot(), item);
  }

  // puts the value in the specified node and calls split depending on the condition
  private void put(E element, Node<E> node){
    Node<E> currentNode = node;
    // Do the comparason between the element being inserted and the desticnation node
    // int diff = currentNode.getLeftVal().compareTo(element);
    if (node.isLeaf()){ // Node is a leaf
      if (node.hasOneVal()){ // Leaf only has one value
        // Assign the right value to be the new element
        currentNode.setRightVal(element);
        // Sort the values of the node to ensure less than is on the left
        currentNode.sortVal();
      } else { // Leaf has two values
        splitLeaf(element, currentNode);
        if (currentNode.getParent() != null) { // Refistribute the node into the parent
          // recursively goes up the tree to redistrubute children
          pushUp(currentNode, currentNode.getParent());
        }
      }
    }
  }

  /*
   * Splits a leaf node that was already full but another element needs to be inserted
   */
  private void splitLeaf(E element, Node<E> node){
    Node<E> currentNode = node;
    int diff = currentNode.getLeftVal().compareTo(element);
    if (diff >= 0){ // element is less than leftVal gets put as the newLeft
      // Initialize a new left node with the element as leftVal
      // and connect it to the left of the currentNode
      Node<E> newLeft = new Node<E>(element);
      currentNode.setLeftChild(newLeft);
      newLeft.setParent(currentNode);
      // Initialize a new right node with the rightVal as the new node's leftVal
      // and connect it to the right of the currentNode in addition to removing
      // rightVal from currentNode
      Node<E> newRight = new Node<E>(currentNode.getRightVal());
      currentNode.setRightChild(newRight);
      newRight.setParent(currentNode);
      currentNode.setRightVal(null);
    } else { // element is greater than leftVal, now comparing to rightVal
      diff = currentNode.getRightVal().compareTo(element);
      if (diff >= 0) { // element is less than or equal to rightVal
        // leftval becomes part of a new node as a left child
        // rightVal becomes part of a new node as a right child
        // item takes the place of the current leftVal and rightVal becomes null
        Node<E> newLeft = new Node<E>(currentNode.getLeftVal());
        currentNode.setLeftChild(newLeft);
        newLeft.setParent(currentNode);

        Node<E> newRight = new Node<E>(currentNode.getRightVal());
        currentNode.setRightChild(newRight);
        newRight.setParent(currentNode);

        currentNode.setRightVal(null);
        currentNode.setLeftVal(element);
      } else { // element is greater than thr rightVal
        // leftval becomes part of a new node as a left child
        // rightVal takes the place of the leftVal in the current node
        // item becomes part of a new node as a right child
        Node<E> newLeft = new Node<E>(currentNode.getLeftVal());
        currentNode.setLeftChild(newLeft);
        newLeft.setParent(currentNode);

        Node<E> newRight = new Node<E>(element);
        currentNode.setRightChild(newRight);
        newRight.setParent(currentNode);

        currentNode.setLeftVal(currentNode.getRightVal());
        currentNode.setRightVal(null);
      }
    }
  }


  /* Combines a node into it's parent taking into consideration whether the parent
   * already has 2 values or can fit another value. It also contains a recusive
   * call that would account for the parent being full
    */
  private void pushUp(Node<E> currentNode, Node<E> parentNode) {
    if (parentNode.hasOneVal()){ // Node with only leftVal (a 2-node)
      int diff = parentNode.getLeftVal().compareTo(currentNode.getLeftVal());
      if (diff >= 0) { // Left val of current is less or equal to leftVal of parent
        // leftVal of parent node moves to be rightVal while leftVal of currentNode
        // Takes the place for parent leftVal
        // currentNode's left child becomes leftChild of parent node
        // currentNode's right child becomes midChild of parentNode
        parentNode.setRightVal(parentNode.getLeftVal());
        parentNode.setLeftVal(currentNode.getLeftVal());
        parentNode.setLeftChild(currentNode.getLeftChild());
        currentNode.getLeftChild().setParent(parentNode);
        parentNode.setMidChild(currentNode.getRightChild());
        currentNode.getRightChild().setParent(parentNode);
      } else { // Left val of current is greater than leftVal of parent
        // leftVal of the currentNode is moved to the rightVal of the parentNode
        // leftChild of currentNode becomes the midChild of the parentNode
        // rightChild of the currentNode becomes the rightChild of the parentNode
        parentNode.setRightVal(currentNode.getLeftVal());
        parentNode.setMidChild(currentNode.getLeftChild());
        currentNode.getLeftChild().setParent(parentNode);
        parentNode.setRightChild(currentNode.getRightChild());
        currentNode.getRightChild().setParent(parentNode);
      }
    } else { // parent node has a rightVal (3-node)
      splitThreeNode(currentNode, parentNode);
      if (parentNode.getParent() != null){ //check to see if it reached root
        // recursively push up the tree
        pushUp(parentNode, parentNode.getParent());
      }
    }
  }

  /*
   * redistrubute the children and values of a node with two values and three
   * children. Used before pushing up the tree. Takes two nodes arguments
   */
  private void splitThreeNode(Node<E> currentNode, Node<E> parentNode){
    int diff = parentNode.getLeftVal().compareTo(currentNode.getLeftVal());
    // this node is used to balance and redistrubute children of parent node
    Node<E> temp = null;
    if (diff >= 0) { // currentNode's leftVal is less than parent's leftVal
      // assure that current node is parent's left child
      // Assign the temp node the rightVal of the parentNode and make parentNode
      // its parent
      // Erase rightVal from parentNode by assigning it to null
      // Assign what was the midChild of parentNode as the leftChild of temp
      // Assign what was the rightChild of parentNode as the rightChild of temp
      // Assign temp as the new rightChild of the parentNode
      // Erase parentNode's mid by assigning it to null
      parentNode.setLeftChild(currentNode);
      currentNode.setParent(parentNode);
      temp = new Node<E>(parentNode.getRightVal());
      temp.setParent(parentNode);
      parentNode.setRightVal(null);
      temp.setLeftChild(parentNode.getMidChild());
      temp.getLeftChild().setParent(temp);
      temp.setRightChild(parentNode.getRightChild());
      temp.getRightChild().setParent(temp);
      parentNode.setRightChild(temp);
      parentNode.setMidChild(null);
    } else { // currentNode's leftval is greater than parent's leftval
      // compare currentNode's leftVal to the rightVal of the parentNode
      diff = parentNode.getRightVal().compareTo(currentNode.getLeftVal());
      if (diff >= 0) { // leftVal of current node is between left and rightVal of parent
        // Uses two temp nodes to move the leftVal of the current node up
        // and the old left and rightVal of the current node become left and
        // right children

        /*
         * First section deals with the left side of the parent node
         * It demotes the leftVal of the parent node by creating a temp node for import junit.framework.TestCase;
         * and shifting the previous left down. It also assigns the currentNode's
         * left child as temp's right child
         */
        temp = new Node<E>(parentNode.getLeftVal());
        temp.setParent(parentNode);
        temp.setLeftChild(parentNode.getLeftChild());
        temp.getLeftChild().setParent(temp);
        temp.setRightChild(currentNode.getLeftChild());
        temp.getRightChild().setParent(temp);
        parentNode.setLeftChild(temp);

        /*
         * Similar to the previous sectio by deals with the right side of
         * the parent node.
         */
        temp = new Node<E>(parentNode.getRightVal());
        temp.setParent(parentNode);
        temp.setLeftChild(currentNode.getRightChild());
        temp.getLeftChild().setParent(temp);
        temp.setRightChild(parentNode.getRightChild());
        temp.getRightChild().setParent(temp);
        parentNode.setRightChild(temp);

        // Final changes to the parentNode so the currentNode gets promoted
        // to be the parentNode
        parentNode.setLeftVal(currentNode.getLeftVal());
        parentNode.setRightVal(null);
        parentNode.setMidChild(null);
      } else { // leftVal of currentNode is greater than rightVal of parentNode
        // Similar to what was done in the case of less than the leftVal
        // This case redistrubute the children using a temp node and promote
        // the correct node
        parentNode.setRightChild(currentNode);
        currentNode.setParent(parentNode);
        temp = new Node<E>(parentNode.getLeftVal());
        temp.setParent(parentNode);
        temp.setLeftChild(parentNode.getLeftChild());
        temp.getLeftChild().setParent(temp);
        temp.setRightChild(parentNode.getMidChild());
        temp.getRightChild().setParent(temp);
        parentNode.setLeftChild(temp);
        parentNode.setLeftVal(parentNode.getRightVal());
        parentNode.setRightVal(null);
        parentNode.setMidChild(null);
      }
    }


  }

  /*
   * This method accepts an element and NOT a node.
   * It uses search to locate the value to be deleted.
   * It returns false if the element was not found in the tree.
   * returns true if the delete was done successfully.
   * Deletes element in order of successor
   */
  public boolean delete(E element){
    Node<E> foundNode = searchNode(this.getRoot(), element);
    if (foundNode != null) { // element was found in Tree
      // special case when deleting in a tree with 2 or less items
      if (this.getSize() == 1) {
        System.out.println("Deleting last element in the tree");
        // deleting root
        this.setRoot(null);
        size--; // decrementing size here because method will not continue
        return true;
      }
      if (this.getSize() == 2){
        System.out.println("One item remaining in tree");
        foundNode.removeVal(element);
        size--; // decrementing size here because method will not continue
        return true;
      }
      Node<E> successor = getSuccessor(foundNode, element);
      E successorVal = successor.getLeftVal();
      // Switching procedure
      if (element.compareTo(foundNode.getLeftVal()) == 0){ // element is in leftVal
        // switch leftVal of successor and leftVal of foundNode
        // move the element to the successor node
        successor.setLeftVal(foundNode.getLeftVal());
        // move the successor value to the found node
        foundNode.setLeftVal(successorVal);
      } else { // element is in rightVal
        // switch leftVal of successor and rightVal of foundNode
        // move the element to the successor node
        successor.setLeftVal(foundNode.getRightVal());
        // move the successor value to the found node
        foundNode.setRightVal(successorVal);
      }

      // Store important successor attributes
      Node<E> currentParent = successor.getParent();
      Node<E> closestSibling = successor.getClosestSibling();
      Integer successorPosition = successor.whichChild();

      // element could be removed right away if there is another value in the node
      if (successor.hasTwoVals()){ // simple case
        successor.removeVal(element);
      // parent only has one value, thus one other sibling
      } else if(currentParent.hasOneVal()){
        if (closestSibling.hasTwoVals()){ // sibling has two values
          if (successorPosition == 1){ // successor is a right child
            // bring the parent value down to the right child (successor node)
            successor.setLeftVal(currentParent.getLeftVal());
            // bring up rightVal of sibling to the parent
            currentParent.setLeftVal(closestSibling.getRightVal());
            // remove the rightVal of the sibling
            closestSibling.setRightVal(null);
          } else if (successorPosition == -1) { // successor is a left child
            // bring the parent value down to the left child (successor node)
            successor.setLeftVal(currentParent.getLeftVal());
            // bring up leftVal of sibling to the parent
            currentParent.setLeftVal(closestSibling.getLeftVal());
            // move the rightVal of the sibling to take the place of leftVal
            closestSibling.setLeftVal(closestSibling.getRightVal());
            // set rightVal of sibling to null
            closestSibling.setRightVal(null);
          } else {
            System.out.println("ERROR: comparason result unknown");
            return false;
          }
        } else { // sibling has one value
          recursiveDelete(successor);
        }
      // parent has two values, two other siblings
      } else if(currentParent.hasTwoVals()) {
        if (closestSibling.hasOneVal()){ // no extra value to steal
          if (successorPosition == 1) { // successor is a right child
            // Take the value from middle child
            successor.setLeftVal(closestSibling.getLeftVal());
            // remove the middle child
            currentParent.setMidChild(null);
            // Bring down the righ value of the parent
            successor.setRightVal(currentParent.getRightVal());
            // remove the right val from parent
            currentParent.setRightVal(null);
          } else if (successorPosition == 0) { // successor is a middle child
            // bring down leftVal of parent to the closestSibling
            closestSibling.setRightVal(currentParent.getLeftVal());
            // remove midChild
            currentParent.setMidChild(null);
            // Shift rightVal of parent to the leftVal
            currentParent.setLeftVal(currentParent.getRightVal());
            // remove parent's right val
            currentParent.setRightVal(null);
          } else if (successorPosition == -1) { // successor is a left child
            // bring down the leftVal of the parent
            successor.setLeftVal(currentParent.getLeftVal());
            // bring the value of closest child as the rightVal of successor
            successor.setRightVal(closestSibling.getLeftVal());
            // remove the parent's middle child
            currentParent.setMidChild(null);
            // shift the rightVal of parent over to leftVal
            currentParent.setLeftVal(currentParent.getRightVal());
            // remove parent's rightVal
            currentParent.setRightVal(null);
          } else {
            System.out.println("ERROR: comparason result unknown");
            return false;
          }
        } else { // promote extra value from sibling to parent and bring down old parent
          if (successorPosition == -1) { // successor is a leftChild
            // bring down the leftVal from parent
            successor.setLeftVal(currentParent.getLeftVal());
            // bring up sibling leftVal to parent leftVal
            currentParent.setLeftVal(closestSibling.getLeftVal());
            // shift the rightVal of sibling to the left and remove rightVal
            closestSibling.setLeftVal(closestSibling.getRightVal());
            closestSibling.setRightVal(null);
          } else if (successorPosition == 0) { // successor is a mid child
            // NOTE: this assumes that the closest sibling of a middle child
            // is its left sibling
            // bring down leftVal from parent
            successor.setLeftVal(currentParent.getLeftVal());
            // move up the right val of the closestSibling to the leftVal of parent
            currentParent.setLeftVal(closestSibling.getRightVal());
            // remove the rightVal from the sibling
            closestSibling.setRightVal(null);
          } else if (successorPosition == 1) { // successor is a right child
            // bring down the rightVal of parent
            successor.setLeftVal(currentParent.getRightVal());
            // bring up the closestSibling rightVal as the parent rightVal
            currentParent.setRightVal(closestSibling.getRightVal());
            // remove the rightVal from the sibling
            closestSibling.setRightVal(null);
          } else {
            System.out.println("ERROR: comparason result unknown");
            return false;
          }
        }
      } else {
        System.out.println("ERROR: unknown delete condition");
        return false;
      }
      size--;
      return true;
    } else{ // element was not found in tree
      return false;
    }
  }

  /*
   * recursively merge parent into child node until special case is reached
   * or root has been reached
   * receive the successor node which then changes after every recursive call
   * does not return a value
   * NOTE: should only be called on a node that has one or no value inside
   */
  private void recursiveDelete(Node<E> node) {
    Node<E> currentNode = node;
    /*
     * In the case of the first call, this would delete the item
     * In the case of other calls, the node would be empty to begin with
     */
    if (currentNode.getLeftVal() != null){
      currentNode.setLeftVal(null);
    }

    if (currentNode == this.getRoot()){ // if the calls reached the root
      this.setRoot(currentNode.getLeftChild()); // make the child as the new root
      this.getRoot().setParent(null); // remove the reference to empty node
    // Condition for recusive call
    } else if (currentNode.getClosestSibling().hasOneVal() && currentNode.getParent().hasOneVal()){
      mergeParentAndSibling(currentNode);
      recursiveDelete(currentNode.getParent());
    // Condition for closestSibling having two keys
    } else if (currentNode.getClosestSibling().hasTwoVals()){
      stealFromSibling(currentNode);
    // Condition for parent having 2 keys
    } else if (currentNode.getParent().hasTwoVals()) {
      mergeWithSibling(currentNode);
    }

  }

  /*
   * recursive delete helper methods
   */
  private void stealFromSibling(Node<E> node){
    Node<E> currentNode = node;
    int nodePosition = currentNode.whichChild();
    Node<E> closestSibling = currentNode.getClosestSibling();
    Node<E> parent = currentNode.getParent();
    if (parent.hasOneVal()){
      if (nodePosition == 1){ // current is a rightChild
        // Bring down parent value and move up sibling rightVal
        currentNode.setLeftVal(parent.getLeftVal());
        parent.setLeftVal(closestSibling.getRightVal());
        closestSibling.setRightVal(null);
        // shift the left child to make room for new child
        currentNode.setRightChild(currentNode.getLeftChild());
        currentNode.setLeftChild(closestSibling.getRightChild());
        currentNode.getLeftChild().setParent(currentNode);
        // remove rightChild of closestSibling
        closestSibling.setRightChild(closestSibling.getMidChild());
        closestSibling.setMidChild(null);
      } else if (nodePosition == -1){ // current is a lefttChild
        // Bring down parent leftVal and move up sibling leftVal
        currentNode.setLeftVal(parent.getLeftVal());
        parent.setLeftVal(closestSibling.getLeftVal());
        closestSibling.setLeftVal(closestSibling.getRightVal());
        closestSibling.setRightVal(null);
        // take siblings leftChild and shift siblings midChild to be leftChild
        currentNode.setRightChild(closestSibling.getLeftChild());
        currentNode.getRightChild().setParent(currentNode);
        closestSibling.setLeftChild(closestSibling.getMidChild());
        closestSibling.setMidChild(null);
      } else {
        System.out.println("ERROR: node position unknown");
      }
    } else if (parent.hasTwoVals()){
      if (nodePosition == 1){ // current is a rightChild
        // rotate values
        currentNode.setLeftVal(parent.getRightVal());
        parent.setRightVal(closestSibling.getRightVal());
        closestSibling.setRightVal(null);
        // move closestSibling right child as a leftChild after shifting
        currentNode.setRightChild(currentNode.getLeftChild());
        currentNode.setLeftChild(closestSibling.getRightChild());
        currentNode.getLeftChild().setParent(currentNode);
        // shift closestSibling mid child to become rightChild
        closestSibling.setRightChild(closestSibling.getMidChild());
        closestSibling.setMidChild(null);
      } else if (nodePosition == -1){ // current is a lefttChild
        // rotate values
        currentNode.setLeftVal(parent.getLeftVal());
        parent.setLeftVal(closestSibling.getLeftVal());
        closestSibling.setLeftVal(closestSibling.getRightVal());
        closestSibling.setRightVal(null);
        // move left child of closest sibling as a right child of currentNode
        currentNode.setRightChild(closestSibling.getLeftChild());
        currentNode.getRightChild().setParent(currentNode);
        closestSibling.setLeftChild(closestSibling.getMidChild());
        closestSibling.setMidChild(null);
      } else if (nodePosition == 0) { // current is a midChild
        // rotate values
        currentNode.setLeftVal(parent.getLeftVal());
        parent.setLeftVal(closestSibling.getRightVal());
        closestSibling.setRightVal(null);
        // move right child of closestSibling as leftChild
        currentNode.setRightChild(currentNode.getLeftChild());
        currentNode.setLeftChild(closestSibling.getRightChild());
        currentNode.getLeftChild().setParent(currentNode);
        closestSibling.setRightChild(closestSibling.getMidChild());
        closestSibling.setMidChild(null);
      } else {
        System.out.println("ERROR: node position unknown");
      }
    } else {
      System.out.println("ERROR: unknown parent propery");
    }
  }


  private void mergeWithSibling(Node<E> node){
    Node<E> currentNode = node;
    int nodePosition = currentNode.whichChild();
    Node<E> closestSibling = currentNode.getClosestSibling();
    Node<E> parent = currentNode.getParent();
    if (nodePosition == -1){ // node is a leftChild
      // Shift sibling leftVal to the right
      closestSibling.setRightVal(closestSibling.getLeftVal());
      // Bring down parent leftVal
      closestSibling.setLeftVal(parent.getLeftVal());
      // Shift rightVal of parent to the left
      parent.setLeftVal(parent.getRightVal());
      // remove parent rightVal
      parent.setRightVal(null);
      // Shift closestSibling leftChild to mid
      closestSibling.setMidChild(closestSibling.getLeftChild());
      // bring currentNode's child to become left child of sibling
      closestSibling.setLeftChild(currentNode.getLeftChild());
      // reestablish a parent relationship with the closestSibling
      closestSibling.getLeftChild().setParent(closestSibling);
      // make closestSibling as the leftChild of parent
      parent.setLeftChild(closestSibling);
      // remove the old midChild
      parent.setMidChild(null);
    } else if (nodePosition == 0) { // node is a midChild
      // bring down leftVal of parent
      closestSibling.setRightVal(parent.getLeftVal());
      // Shif right val of parent to the left
      parent.setLeftVal(parent.getRightVal());
      parent.setRightVal(null);
      // Shift right child of sibling to mid
      closestSibling.setMidChild(closestSibling.getRightChild());
      // Bring currentNode child as the right child of sibling
      closestSibling.setRightChild(currentNode.getLeftChild());
      closestSibling.getRightChild().setParent(closestSibling);
      // Remove parent's midChild
      parent.setMidChild(null);
    } else if (nodePosition == 1) { // node is a rightChild
      // bring down the rightVal of parent
      closestSibling.setRightVal(parent.getRightVal());
      parent.setRightVal(null);
      // Shift right val of closestSibling to mid
      closestSibling.setMidChild(closestSibling.getRightChild());
      // bring currentNode child as a right child of sibling
      closestSibling.setRightChild(currentNode.getLeftChild());
      closestSibling.getRightChild().setParent(closestSibling);
      // make the closestSibling the right child of parent and remove midChild
      parent.setRightChild(closestSibling);
      parent.setMidChild(null);
    }
  }

  private void mergeParentAndSibling(Node<E> node){
    Node<E> currentNode = node;
    int nodePosition = currentNode.whichChild();
    Node<E> closestSibling = currentNode.getClosestSibling();
    Node<E> parent = currentNode.getParent();
    if (nodePosition == -1){ // node is a leftChild
      // Shift sibling's val to the right
      closestSibling.setRightVal(closestSibling.getLeftVal());
      // Bring down parent leftVal
      closestSibling.setLeftVal(parent.getLeftVal());
      // remove parent's val
      parent.setLeftVal(null);
      // shift leftChild of closestSibling to become a midChild
      closestSibling.setMidChild(closestSibling.getLeftChild());
      // bring currentNode child to become closestSibling leftChild
      closestSibling.setLeftChild(currentNode.getLeftChild());
      if (closestSibling.getLeftChild() != null){
        closestSibling.getLeftChild().setParent(closestSibling);
      }
      // shift the parent's right child to the left
      parent.setLeftChild(parent.getRightChild());
      // remove old right child
      parent.setRightChild(null);
    } else if (nodePosition == 1){ // node is a right child
      // bring down the parent value
      closestSibling.setRightVal(parent.getLeftVal());
      // remove parent's value
      parent.setLeftVal(null);
      // remove right child
      parent.setRightChild(null);
      // shift rightChild of closestSibling to become the midChild
      closestSibling.setMidChild(closestSibling.getRightChild());
      // shift currentNode's child to the rightChild of closestSibling
      closestSibling.setRightChild(currentNode.getLeftChild());
      if (closestSibling.getRightChild() != null){
        closestSibling.getRightChild().setParent(closestSibling);
      }
    } else {
      System.out.println("ERROR: unknown recursive deletion condition");
    }

  }

  /*
   * Traverse through the tree and identify the in-order successor of a value
   * In the tree. Returns the node containing the successor.
   */
  private Node<E> getSuccessor(Node<E> found, E element){
    Node<E> currentNode = found;
    if (!currentNode.isLeaf()) { // successor exists
      // Fist step of getting successor depends on the children of the node
      if (currentNode.hasOneVal()){ // node has one key and only two children
        // closest successor on the right
        currentNode = currentNode.getRightChild();
      } else { // node has two keys and three children
        // check if the element is in the left or right key of the node
        int diff = element.compareTo(currentNode.getLeftVal());
        // NOTE: could cause issues with duplicates
        if (diff == 0){ // element is the leftVal
          // move to the middle child
          currentNode = currentNode.getMidChild();
        } else if(diff == 1){ // element is the rightVal
          // move to the right child
          currentNode = currentNode.getRightChild();
        } else {
          System.out.println("Comparason error!");
          return null;
        }
      }
      while(!currentNode.isLeaf()){
        currentNode = currentNode.getLeftChild();
      }
    }
    return currentNode;
  }



  // Method used to print the content of the Tree23
  // recursively output the content of the tree, not practical and messy
  public String toString(){
    String output = "Size of tree: " + size + "\n" +
                    "Root of tree: " + root.toString();
    return output;
  }

  /*
   * Uses breadth first traversal to create a dot notation for every node and
   * it's children in the tree. It then returns these notations in an
   * ArrayList of strings to be later written on a .dot file
   */
  public ArrayList<String> toDot() {
    // used to store the final string results
    ArrayList<String> result = new ArrayList<String>();
    // store unvisited nodes in a queue
    Queue<Node<E>> queue = new LinkedList<Node<E>>();
    queue.add(this.getRoot());
    while(!queue.isEmpty()){
      // removes any null nodes that were added
      while (queue.peek() == null) {
        queue.remove();
      }
      Node<E> currentNode = queue.peek();
      if (!currentNode.getLeftChild().isLeaf()){
        queue.add(currentNode.getLeftChild());
        queue.add(currentNode.getMidChild());
        queue.add(currentNode.getRightChild());
      }
      if (currentNode.hasOneVal()){
        // add a string formatted as follows:
        // \t parentVal -- {leftChildVal rightChildVal};\n
        // This formatting is dot language signaling that the parent node points
        // to the left and right children. In the case of a three node the midChild
        // is also added
        result.add("\t" + currentNode.getValuesString() + " -- " +
                    "{" + currentNode.getLeftChild().getValuesString() + " " +
                    currentNode.getRightChild().getValuesString() + "};\n");
      } else {
        result.add("\t" + currentNode.getValuesString() + " -- " +
                    "{" + currentNode.getLeftChild().getValuesString() + " " +
                    currentNode.getMidChild().getValuesString() + " " +
                    currentNode.getRightChild().getValuesString() + "};\n");
      }
      // remove the current node to move on
      queue.remove();
    }
    return result;
  }

}
