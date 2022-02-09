import java.util.NoSuchElementException;
import java.lang.Iterable;
import java.util.Iterator;
import java.util.ListIterator;
import java.lang.UnsupportedOperationException;

/**
 * 
 * @author Ashley Sah
 * Represents a list that is double linked (has two connections between each element, so two nodes next to one another (A and B) have a connection from A to B and B to A).
 * @param <T> is the type stored in the list
 */
public class DoubleLinkedList<T> implements Iterable<T>{
	/** a reference to the first node of the double linked list */
	  private DLNode<T> front;
	  
	  /** a reference to the last node of a double linked list */
	  private DLNode<T> back;
	  
	  /** Create an empty double linked list. */
	  public DoubleLinkedList() {
	    front = back = null;
	  }
	  
	  /** 
	   * Returns true if the list is empty.
	   * @return  true if the list has no nodes
	   */
	  public boolean isEmpty() {
	    return (getFront() == null);
	  }
	  
	  /**
	   * Returns the reference to the first node of the linked list.
	   * @return the first node of the linked list
	   */
	  protected DLNode<T> getFront() {
	    return front;
	  }
	  
	  /**
	   * Sets the first node of the linked list.
	   * @param node  the node that will be the head of the linked list.
	   */
	  protected void setFront(DLNode<T> node) {
	    front = node;
	  }
	  
	  /**
	   * Returns the reference to the last node of the linked list.
	   * @return the last node of the linked list
	   */
	  protected DLNode<T> getBack() {
	    return back;
	  }
	  
	  /**
	   * Sets the last node of the linked list.
	   * @param node the node that will be the last node of the linked list
	   */
	  protected void setBack(DLNode<T> node) {
	    back = node;
	  }
	  
	  /*----------------------------------------*/
	  /* METHODS TO BE ADDED DURING LAB SESSION */
	  /*----------------------------------------*/
	  
	  /**
	   * Add an element to the head of the linked list.
	   * @param element  the element to add to the front of the linked list
	   */
	  public void addToFront(T element) {
		  
		  if(isEmpty()) {
			  DLNode<T> dlnode = new DLNode<T>(element, null, null);
			  setFront(dlnode);
			  setBack(dlnode);
		  }
		  else
			  setFront(new DLNode<T>(element, null, getFront()));
	  }
	  
	  /**
	   * Add an element to the tail of the linked list.
	   * @param element  the element to add to the tail of the linked list
	   */
	  public void addToBack(T element) {
		  if(isEmpty()) {
			  DLNode<T> dlnode = new DLNode<T>(element, null, null);
			  setFront(dlnode);
			  setBack(dlnode);
		  }
		  else
			  setBack(new DLNode<T>(element, getBack(), null));
			  
	  }
	  /**
	   * Remove and return the element at the front of the linked list.
	   * @return the element that was at the front of the linked list
	   * @throws NoSuchElementException if attempting to remove from an empty list
	   */
	  public T removeFromFront() {
	    
		if(isEmpty()) {
	    	throw new NoSuchElementException();
	    }
		
		else {
			T save = getFront().getElement();
			setFront(getFront().getNext());
			//in case we just removed the last node in the list
			if(!isEmpty())
				getFront().setPrevious(null);
			return save;
		}
	    
	  }
	  
	  /**
	   * Remove and return the element at the back of the linked list.
	   * @return the element that was at the back of the linked list
	   * @throws NoSuchElementException if attempting to remove from an empty list
	   */
	  public T removeFromBack(){
	    if(isEmpty()) {
	    	throw new NoSuchElementException();
	    }
	    return null;
	  }
	   
	  
	  public ListIterator<T> iterator(){
	  	return new DoubleLinkedListIterator<T>(this);
	  }
	   
	  /**
	   * Takes a DoubleLinkedList<T> and adds it to the back of this DoubleLinkedList<T>
	   * @param DoubleLinkedList<T> the double linked list <T> that is added to the back of this DoubleLinkedList
	   */
	  public void append(DoubleLinkedList<T> list2) {
			if (!list2.isEmpty()) {
				//set the last DLNode's next DLNode to the front node of list 2
				getBack().setNext(list2.getFront());
				//set list2's front node's previous node to be the back of this list
				list2.getFront().setPrevious(getBack());
				//set the back of this list to the back of list 2
				setBack(list2.getBack());
			}
		}

		/**
		 * Determines if the input Object is equal to the DoubleLinkedList<T> A
		 * DoubleLinkedList<T> can only be equal to another DoubleLinkedList that stores
		 * the same type of elements, and elements in the same order. For example a
		 * double linked list storing 1, 2, 3, 4 will be equal to another
		 * DoubleLinkedList storing 1, 2, 3, 4, but it will not be equal to another
		 * DoubleLinkedList storing 4, 1, 2, 3 or a DoubleLinkedList storing strings.
		 * 
		 * @returns a boolean on whether the object is equal to this DoubleLinkedList<T>
		 */
	  
	  @Override
	  public boolean equals(Object list2) {
		 
		  if(list2 instanceof DoubleLinkedList)//if it's a double linked list
		     {
			   //String typeStoredHere = getFront().getElement().getClass().getName();
			   //String typeStoredList2 = ((DoubleLinkedList)list2).getFront().getElement().getClass().getName();
		       
			   if( getFront().getElement().getClass().getName().equals(((DoubleLinkedList)list2).getFront().getElement().getClass().getName()) )//if they store the same generic type
		       {
		         DLNode<T> nodeptr1 = getFront();
		         DLNode<T> nodeptr2 = ((DoubleLinkedList<T>)list2).getFront();
		         
		         while(nodeptr1 != null && nodeptr2 != null){
		           if(!( nodeptr1.getElement().equals(nodeptr2.getElement()) ) ) {
		             return false;
		           }
		           
		           nodeptr1 = nodeptr1.getNext();
		           nodeptr2 = nodeptr2.getNext();
		         }
		         
		         if(nodeptr2 == null && nodeptr1 == null)
		        	 return true;
		         else
		        	 return false;
		       }
		     }
		   
		    return false;
		  
	  }
	  
	  /**
	   * A ListIterator for DoubleLinkedList
	   * @author Ashley Sah
	   * @param <T> represents the elements stored in double linked list the iterator iterates through
	   */
	  private static class DoubleLinkedListIterator<T> implements ListIterator<T>{
		private DLNode<T> previous;
		private DLNode<T> nodeptr;
		private DoubleLinkedList<T> list;
		private int index = 0;
		
		/**
		 * 
		 * @param list the DoubleLinkedList the iterator iterates through
		 */
		public DoubleLinkedListIterator(DoubleLinkedList<T> list) {
			nodeptr = list.getFront();
			this.list = list;
		}
		@Override
		/**
		 * Checks if there is a current node
		 */
		public boolean hasNext() {
			if(nodeptr != null)
				return true;
			return false;
		}

		@Override
		/**
		 * moves the cursor the the next element
		 * returns the element the cursor moved from
		 */
		public T next() {
			if(list.isEmpty() || nodeptr == null) {
				previous = null;
				return null;
			}
			previous = nodeptr;
			nodeptr = nodeptr.getNext();
			index++;
			return previous.getElement();
		}

		@Override
		/**
		 * moves the cursor to the previous element
		 * returns the element the cursor moved from
		 */
		public T previous() {
			if(list.isEmpty()) {
				return null;
			}
			nodeptr = previous;
			
			//if the nodeptr is not null
			if(nodeptr!=null) {
				previous = previous.getPrevious();
				index--;
				return nodeptr.getElement();
			}
			
			return null;
		}

		@Override
		/**
		 * sets the element at the cursor to the input value
		 * 
		 */
		public void set(T e) {
			if(list.isEmpty()) {
				add(e);
				nodeptr = list.getFront();
			}
			
			nodeptr.setElement(e);
		}

		@Override
		/**
		 * adds an element to the back of the list
		 */
		public void add(T e) {
			list.addToBack(e);
		}
		
		@Override
		/**
		 * checks if there is a previous node
		 */
		public boolean hasPrevious() {
			if (previous!=null)
				return true;
			return false;
		}
		
		//methods I chose not to implement:
		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		  
	  }
	 
}
