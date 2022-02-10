import java.util.Iterator;

/** An iterator for our linked list.  The iterator permits looping without using nodes directly */
public class LinkedListIterator<T> implements Iterator<T> {
  // nodeptr marks the current node as we are iterating
  private LLNode<T> nodeptr;
  
  /**
   * Create an iterator for a linked list
   * @param list the list we are creating an iterator for
   */
  public LinkedListIterator(LinkedList<T> list) {
    nodeptr = list.getFirstNode();
  }
  
  /**
   * Returns true if there are more elements in the iteration
   * @return true if there are more elements in the iteration
   */
  @Override
  public boolean hasNext() {
    return nodeptr != null;
  }
  
  /**
   * Returns the next element of the iteration and "increments" the iteration
   * @return the next element of the iteration
   */
  @Override
  public T next() {
    T save = nodeptr.getElement();
    nodeptr = nodeptr.getNext();
    return save;
  }
}