package jp.co.wap.exam;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * The Queue class represents an immutable first-in-first-out (FIFO) queue of objects.
 * @param <E>
 */
public class PersistentQueue<E> {
	final private static class PQElement<E> {
		final private E e;
		final private PQElement<E> previous;
		
		public PQElement(E e, PQElement<E> pqePrevious) {
			this.e = e;
			this.previous = pqePrevious;
		}
	}
	
	
	final private int length;
	final private PQElement<E> head;
	final private PQElement<E> headNext;
	final private PQElement<E> tail;
	
	/**
	 * requires default constructor.
	 */
	public PersistentQueue() {
		this.length = 0;
		this.head = null;
		this.headNext = null;
		this.tail = null;
	}
	private PersistentQueue(PQElement<E> pqe) {
		this.length = 1;
		this.head = pqe;
		this.headNext = null;
		this.tail = pqe;
	}
	private PersistentQueue(PQElement<E> pqeHead, PQElement<E> pqeTail) {
		this.length = 2;
		this.head = pqeHead;
		this.headNext = pqeTail;
		this.tail = pqeTail;
	}
	private PersistentQueue(PQElement<E> pqeHead, PQElement<E> pqeHeadNext, PQElement<E> pqeTail) {
		this.length = 3;
		this.head = pqeHead;
		this.headNext = pqeHeadNext;
		this.tail = pqeTail;
	}
	private PersistentQueue(int length, PQElement<E> pqeHead, PQElement<E> pqeHeadNext, PQElement<E> pqeTail) {
		this.length = length;
		this.head = pqeHead;
		this.headNext = pqeHeadNext;
		this.tail = pqeTail;
	}
	
	/**
	 * Returns the queue that adds an item into the tail of this queue without modifying this queue.
	 * <pre>
	 * e.g.
	 *   When this queue represents the queue (2, 1, 2, 2, 6) and we enqueue the value 4 into this queue,
	 *   this method returns a new queue (2, 1, 2, 2, 6, 4)
	 *   and this object still represents the queue (2, 1, 2, 2, 6) .
	 *   </pre>
	 * If the element e is null, throws IllegalArgumentException.
	 * @param e
	 * @return
	 * @throws IllegalArgumentException
	 */
	public PersistentQueue<E> enqueue(E e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		
		PersistentQueue<E> pqNew = null;
		PQElement<E> pqeNew = new PQElement<E>(e, this.tail);
		
		switch (this.length)
		{
			case 0:
				pqNew = new PersistentQueue<E>(pqeNew);
				break;
			case 1:
				pqNew = new PersistentQueue<E>(this.head, pqeNew);
				break;
			case 2:
				pqNew = new PersistentQueue<E>(this.head, this.tail, pqeNew);
				break;
			default:
				pqNew = new PersistentQueue<E>(this.length+1, this.head, this.headNext, pqeNew);
				break;
		}
		
		return pqNew;
	}
	
	/**
	 * Returns the queue that removes the object at the head of this queue without modifying this queue.
	 * <pre>
	 * e.g.
	 *   When this queue represents the queue (7, 1, 3, 3, 5, 1) ,
	 *   this method returns a new queue (1, 3, 3, 5, 1)
	 *   and this object still represents the queue (7, 1, 3, 3, 5, 1) .
	 *   </pre>
	 * If this queue is empty, throws java.util.NoSuchElementException.
	 * @return
	 * @throws java.util.NoSuchElementException
	 */
	public PersistentQueue<E> dequeue() {
		if (this.length == 0) {
			throw new NoSuchElementException();
		}
		
		PersistentQueue<E> pqNew = null;
		
		switch (this.length)
		{
			case 1:
				pqNew = new PersistentQueue<E>();
				break;
			case 2:
				pqNew = new PersistentQueue<E>(this.tail);
				break;
			case 3:
				pqNew = new PersistentQueue<E>(this.headNext, this.tail);
				break;
			default:
				PQElement<E> headNew = null;
				PQElement<E> headNextNew = this.tail;
				for (int i=0; i<this.length-3; i++) {
					headNextNew = headNextNew.previous;
				}
				headNew = headNextNew.previous;
				pqNew = new PersistentQueue<E>(this.length-1, headNew, headNextNew, this.tail);
				break;
		}
		
		return pqNew;
	}
	
	/**
	 * Looks at the object which is the head of this queue without removing it from the queue.
	 * <pre>
	 * e.g.
	 *   When this queue represents the queue (7, 1, 3, 3, 5, 1),
	 *   this method returns 7 and this object still represents the queue (7, 1, 3, 3, 5, 1)
	 *   </pre>
	 * If the queue is empty, throws java.util.NoSuchElementException.
	 * @return
	 * @throws java.util.NoSuchElementException
	 */
	 public E peek() {
		 if (this.length == 0) {
			 throw new NoSuchElementException();
		 }
		 
		 return this.head.e;
	 }
	 
	 /**
	  * Returns the number of objects in this queue.
	  * @return
	  */
	 public int size() {
		 return this.length;
	 }
	 
	 /**
	  * Returns the String view of this persistent queue.
	  * @return
	  */
	 /*
	 public String toString() {
		 StringBuilder sb = new StringBuilder();
		 Stack<E> s = new Stack<E>();
		 PQElement<E> tmpElement = this.tail;
		 
		 for (int i=0; i<this.size(); i++) {
			 s.push(tmpElement.e);
			 tmpElement = tmpElement.previous;
		 }
		 
		 sb.append("[");
		 if (this.size() != 0) {
			 sb.append(s.pop());
		 }
		 for (int i=0; i<this.size()-1; i++) {
			 sb.append(", ").append(s.pop());
		 }
		 sb.append("]");
		 
		 return sb.toString();
	 }
	 */
	 
	 public String toString() {
		 StringBuilder sb = new StringBuilder();
		 
		 sb.append("[");
		 if (this.size() != 0) {
			 if (this.tail.previous != null) {
				 sb.append(toStringSubroutine(this.length-1, this.tail.previous));
			 }
			 sb.append(this.tail.e);
		 }
		 sb.append("]");
		 
		 return sb.toString();
	 }
	 
	 private String toStringSubroutine(int remainingLength, PQElement<E> pqElement) {
		 StringBuilder sb = new StringBuilder();
		 
		 if (remainingLength>0 && pqElement != null) {
			 if (pqElement.previous != null) {
				 sb.append(toStringSubroutine(remainingLength-1, pqElement.previous));
			 }
			 
			 sb.append(pqElement.e).append(", ");
		 }
		 
		 return sb.toString();
	 }
	 
}