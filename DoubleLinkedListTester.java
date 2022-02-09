import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.ListIterator;

import org.junit.Test;

public class DoubleLinkedListTester {


	@Test
	  public void testAddToFront() {
	    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
	    list.addToFront(3);
	    list.addToFront(2);
	    list.addToFront(1);
	    DLNode<Integer> head = list.getFront();
	    DLNode<Integer> tail = list.getBack();
	    
	    assertEquals("Testing first node of list", new Integer(1), head.getElement());
	    assertEquals("Testing second node of list", new Integer(2), head.getNext().getElement());
	    assertEquals("Testing third node of list", new Integer(3), head.getNext().getNext().getElement());
	    assertEquals("Testing end of list", null, head.getNext().getNext().getNext());
	    
	    assertEquals("Testing node at back of list", new Integer(3), tail.getElement());
	    assertEquals("Testing next to last node", new Integer(2), tail.getPrevious().getElement());
	    assertEquals("Testing third to last node", new Integer(1), tail.getPrevious().getPrevious().getElement());
	    assertEquals("Testing front of list", null, tail.getPrevious().getPrevious().getPrevious());
	  }
	
	@Test
	public void testAddToBack() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.addToBack(1);
		list.addToBack(2);
		list.addToBack(3);
		DLNode<Integer> head = list.getFront();
		DLNode<Integer> tail = list.getBack();
	      
		assertEquals("Testing last node of list", new Integer(3), tail.getElement());
		assertEquals("Testing next to last node", new Integer(2), tail.getPrevious().getElement());
		assertEquals("Testing third to last node", new Integer(1), tail.getPrevious().getPrevious().getElement());
		assertEquals("Testing front of list", null, tail.getPrevious().getPrevious().getPrevious());
	   
		assertEquals("Testing node at front of list", new Integer(1), head.getElement());
		assertEquals("Testing second node of list", new Integer(2), head.getNext().getElement());
		assertEquals("Testing third node of list", new Integer(3), head.getNext().getNext().getElement());
		assertEquals("Testing end of list", null, head.getNext().getNext().getNext());
		
	}
	
	@Test
	public void testRemoveFromFront() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.addToFront(1);
		list.addToFront(2);
		list.addToFront(3);
		assertEquals("Removing element of list", new Integer(3), list.removeFromFront());
		assertEquals("Removing a second element", new Integer(2), list.removeFromFront());
		assertEquals("Removing a third element", new Integer(1), list.removeFromFront());
		assertEquals("Removed last element of list", true, list.isEmpty());
		
		try {
			list.removeFromFront();
			fail("Removing from empty list did not throw an exception.");
		} catch (java.util.NoSuchElementException e) {
			/* everything is good */
		} catch (Exception e) {
			fail("Removing from empty list threw the wrong type of exception.");
		}

		list.addToBack(6);
		list.addToBack(7);
		
		assertEquals("Removing element added to back of list", new Integer(6), list.removeFromFront());
		assertEquals("Removing second element added to back", new Integer(7), list.removeFromFront());
	}
	
	@Test
	public void testEquals() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.addToFront(3);
	    list.addToFront(2);
	    list.addToFront(1);
	    
		DoubleLinkedList<Integer> list2 = new DoubleLinkedList<Integer>();
		list2.addToFront(0);
		list2.addToFront(3);
	    list2.addToFront(2);
	    list2.addToFront(1);
	    
	    DoubleLinkedList<Integer> list3 = new DoubleLinkedList<Integer>();
		list3.addToFront(3);
	    list3.addToFront(2);
	    list3.addToFront(1);
        
        
	    list.equals(list2);
	    String test = "jdkslajfdklsa";
		assertEquals(false, list.equals(list2));
		assertEquals(false, list.equals(test));
		assertEquals(true, list.equals(list3));
	}
	
	@Test
	public void testAppend() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		list.addToFront(3);
	    list.addToFront(2);
	    list.addToFront(1);
	    
	    //test 0
	    DoubleLinkedList<Integer> list0 = new DoubleLinkedList<Integer>();
	    list.append(list0);
	    
	    assertEquals(null, list.getBack().getNext());
	    assertEquals(new Integer(3), list.getBack().getElement());
	    assertEquals(new Integer(2), list.getFront().getNext().getElement());
	    assertEquals(new Integer(1), list.getFront().getElement());
	    
	    //test 1
	    DoubleLinkedList<Integer> list1 = new DoubleLinkedList<Integer>();
	    list1.addToFront(4);
	    list.append(list1);
	    
	    assertEquals(new Integer(4), list.getBack().getElement());
	    assertEquals(null, list.getBack().getNext());
	    assertEquals(new Integer(3), list.getBack().getPrevious().getElement());
	    assertEquals(new Integer(4), list.getBack().getPrevious().getNext().getElement());
	    
	    //test many
	    DoubleLinkedList<Integer> list2 = new DoubleLinkedList<Integer>();
	    list2.addToFront(7);
		list2.addToFront(6);
	    list2.addToFront(5);
	    list2.addToFront(4);
	    
	    list.append(list2);
	    
	    assertEquals(new Integer(7), list.getBack().getElement());
	    assertEquals(new Integer(4), list.getFront().getNext().getNext().getNext().getElement());
	    assertEquals(new Integer(3), list.getFront().getNext().getNext().getNext().getPrevious().getElement());
	    
	}
	/*
	 * The following JUnit Tests are for the DoubleLinkedListIterator<T> nested class
	 */
	
	@Test
	  public void testListIterator() {
	    DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
	    for (int i = 5; i > 0; i--)
	      list.addToFront(i);
	    
	    /* checking that we get out what we put it */
	    int i = 1;
	    for (Integer x: list) {
	      assertEquals("Testing value returned by iterator", new Integer(i++), x);
	    }
	    
	    if (i != 6)
	      fail("The iterator did not run through all the elements of the list");
	  }
	
	@Test
	/*test for hasNext()*/
	
	public void testListIteratorHasNext() {
		
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		
		//checking it works for an empty list:
		Iterator<Integer> it = list.iterator();
		
		assertEquals(false, it.hasNext());
		
		//checking it works for a list with one element:
		list.addToFront(3);
		it = list.iterator();
		
		assertEquals(true, it.hasNext());
		it.next();
		assertEquals(false, it.hasNext());
		
		//checking it works for a list with multiple elements:
		list.addToFront(2);
		list.addToFront(1);
		it = list.iterator();
		
		assertEquals(true, it.hasNext());
		it.next();
		assertEquals(true, it.hasNext());
		it.next();
		assertEquals(true, it.hasNext());
		it.next();
		assertEquals(false, it.hasNext());
		
	}
	
	@Test
	/*test for next()*/
	public void testListIteratorNext() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		//checking it works for an empty list
		Iterator<Integer> it = list.iterator();
		
		assertEquals(null, it.next());
		
		//checking it works for a list with one element:
		list.addToFront(3);
		it = list.iterator();
		
		assertEquals(new Integer(3), it.next());
		
		// checking it works for a list with multiple elements:
		list.addToBack(2);
		list.addToBack(1);
		it = list.iterator();
		
		assertEquals(new Integer(3), it.next());
		assertEquals(new Integer(2), it.next());
		assertEquals(new Integer(1), it.next());
	}
	
	@Test
	public void testListIteratorAdd() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		//checking it works
		ListIterator<Integer> it = list.iterator();
		it.add(new Integer(3));
		
		assertEquals(new Integer(3), list.getFront().getElement());
		
		it.add(new Integer(2));
		assertEquals(new Integer(2), list.getBack().getElement());
	}
	
	@Test
	public void testListIteratorHasPrevious() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		//check it works on an empty list
		ListIterator<Integer> it = list.iterator();
		assertEquals(false, it.hasPrevious());
		
		//checking it works for a list with one element:
		it = list.iterator();
		it.add(3);
		assertEquals(false, it.hasPrevious());
		
		//check it works on a list with multiple elements
		it.add(2);
		it.add(1); //321
		it = list.iterator();
		
		assertEquals(new Integer(3), it.next());
		assertEquals(true, it.hasPrevious());
		assertEquals(new Integer(2), it.next());
		assertEquals(true, it.hasPrevious());
		assertEquals(new Integer(1), it.next());
		assertEquals(true, it.hasPrevious());
		assertEquals(null, it.next());
		assertEquals(false, it.hasPrevious());

	}
	
	@Test
	public void testListIteratorPrevious() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		//check it works on an empty list
		ListIterator<Integer> it = list.iterator();
		assertEquals(null, it.previous());
		
		//checking it works for a list with one element:
		it.add(3);
		it = list.iterator();
		assertEquals(null, it.previous());
		
		//check it works on a list with multiple elements 
		it.add(2);
		it.add(1);
		it = list.iterator();
		
		it.next();
		it.next();
		
		assertEquals(new Integer(2), it.previous());
		assertEquals(new Integer(3), it.previous());
		assertEquals(null, it.previous());
	}
	
	@Test
	public void testListIteratorSet() {
		DoubleLinkedList<Integer> list = new DoubleLinkedList<Integer>();
		//check it works on an empty list
		ListIterator<Integer> it = list.iterator();
		it.set(3);
		assertEquals(new Integer(3), list.getFront().getElement());
		
		//check it works on a list with one element
		DoubleLinkedList<Integer> list2 = new DoubleLinkedList<Integer>();
		it.add(2);
		it = list2.iterator();
		it.set(1);
		
		assertEquals(new Integer(1), list2.getBack().getElement());
		
		//check it works on a list with multiple elements
		it.add(1);
		it.add(0);
		it = list2.iterator();
		
		it.next();
		it.set(5);
		it.next();
		it.set(4);
		
		assertEquals(new Integer(4), list2.getBack().getElement());
		assertEquals(new Integer(5), list2.getBack().getPrevious().getElement());
	}

}
