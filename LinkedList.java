import java.util.NoSuchElementException;
import java.util.Iterator;
/**
 * A class to represent a linked list of nodes.
 */
public class LinkedList<T> implements Iterable<T> {
  /** the first node of the list, or null if the list is empty */
  private LLNode<T> firstNode;
  
  /**
   * Creates an initially empty linked list
   */
  public LinkedList() {
    firstNode = null;
  }
  
  /**
   * Returns the first node.
   */
  protected LLNode<T> getFirstNode() {
    return firstNode;
  }

  /**
   * Changes the first node.
   * @param node  the first node of the new linked list
   */
  protected void setFirstNode(LLNode<T> node) {
    this.firstNode = node;
  }

  /**
   * Add an element to the front of the linked list
   * @param element  the element to add
   */
  public void addToFront(T element) {
    setFirstNode(new LLNode<T>(element, getFirstNode()));
  }
  
  /**
   * Return whether the list is empty
   * @return true if the list is empty
   */
  public boolean isEmpty() {
    return (getFirstNode() == null);
  }
  
  /**
   * Returns the length of the linked list
   * @return the number of nodes in the list
   */
  public int length() {
    int count = 0;                      // counts number of nodes seen
    LLNode<T> nodeptr = getFirstNode();
    while (nodeptr != null) {
      count++;
      nodeptr = nodeptr.getNext();
    }
    return count;
  }
  
  /**
   * Remove and return the element at the front of the list
   * @return the first element of the list
   * @throws NoSuchElementException if there is no such element
   */
  public T removeFromFront() {
    if (isEmpty())
      throw new NoSuchElementException();
    else {
      T save = getFirstNode().getElement();
      setFirstNode(getFirstNode().getNext());
      return save;
    }
  }

  /**
   * Add an element to the very end of the list
   * @param element the element to add to the end of the list
   */
  public void addToEnd(T element) {
    if (isEmpty()) 
      addToFront(element);
    else {
      LLNode<T> nodeptr = getFirstNode();
      // the loop will end with nodeptr looking at the last node in list
      while (nodeptr.getNext() != null)
        nodeptr = nodeptr.getNext();
      nodeptr.setNext(new LLNode<T>(element, null));
    }
  }
  
  /**
   * Overrides the iterator method of Iterable to return an iterator for this list
   * @return an iterator for the list
   */
  
  public LinkedListIterator<T> iterator() {
    return new LinkedListIterator<>(this);
  }
  
  /**
   * Print the contents of the list.
   * We don't get to use the class's generic T in a static method
   * We will declare a generic for this method
   * @param list  the list to print
   */
  public static <S> void printList(LinkedList<S> list) {
    LLNode<S> nodeptr = list.getFirstNode();
    while (nodeptr != null) {
      System.out.println(nodeptr.getElement());
      nodeptr = nodeptr.getNext();
    }
  }
  
  /**
   * Prints the contents of the list.
   * Uses a wildcard for the generic instead of declaring a new generic.
   * @param list  the list to print
   */
  public static void printList2(LinkedList<?> list) {
    LLNode<?> nodeptr = list.getFirstNode();
    while (nodeptr != null) {
      System.out.println(nodeptr.getElement());
      nodeptr = nodeptr.getNext();
    }
  }
  
  /**
   * Insert a Comparable element into a LinkedList "in order".
   * Requirement: the linked list is sorted
   * Post-requ. : the new element is added and linked list is sorted
   * @param e the element to insert into the list
   * @param list a list that has its elements stored in order
   */
  public static <S extends Comparable<? super S>> void insertInOrder(S e, LinkedList<S> list) {
    // 1. the list is empty
    // 2. the list is not empty and e goes at the front 
    if (list.isEmpty() || 
        e.compareTo(list.getFirstNode().getElement()) < 0)
        list.addToFront(e);
    // 3. the list is not empty and e does not go at the front
    else {
      LLNode<S> nodeptr = list.getFirstNode();
      // find the spot where to insert e
      while (nodeptr.getNext() != null &&
             nodeptr.getNext().getElement().compareTo(e) < 0)
        nodeptr = nodeptr.getNext();
      // now either nodeptr is end of list, or next element is larger
      nodeptr.setNext(new LLNode<S>(e, nodeptr.getNext()));
    }    
  
  }
  
}
