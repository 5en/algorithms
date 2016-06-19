package jp.co.wap.exam;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * The Queue class represents an immutable first-in-first-out (FIFO) queue of objects.
 * @param <E>
 */
public class PersistentQueue2<E> {
	final private static class PQTreeNode<E> {
		final private E e;
		final private Map<Integer, PQTreeNode<E>> childrenMap;
		
		public PQTreeNode(E e) {
			this.e = e;
			this.childrenMap = new TreeMap<Integer, PersistentQueue2.PQTreeNode<E>>(); /*Map an integer key to its children.*/
		}
	}
	
	final private PQTreeNode<E> root;
	final private PQTreeNode<E> leaf;
	final private List<Integer> pathKeys; /*keys to find specific elements in this PersistentQueue2*/
	
	/**
	 * requires default constructor.
	 */
	public PersistentQueue2() {
		this.root = null;
		this.leaf = null;
		this.pathKeys = null;
	}
	private PersistentQueue2(PQTreeNode<E> node) {
		this.root = node;
		this.leaf = node;
		this.pathKeys = new LinkedList<Integer>();
	}
	private PersistentQueue2(PQTreeNode<E> root, PQTreeNode<E> leaf, List<Integer> pathKeys) {
		this.root = root;
		this.leaf = leaf;
		this.pathKeys = pathKeys;
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
	public PersistentQueue2<E> enqueue(E e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		
		PersistentQueue2<E> pqNew = null; /*The new PersistentQueue2 to be constructed.*/
		PQTreeNode<E> pqTreeNodeNew = new PQTreeNode<E>(e); /*The new element to be added.*/
		
		switch (this.size()) {
			case 0:
				pqNew = new PersistentQueue2<E>(pqTreeNodeNew);
				break;
			default:
				List<Integer> pathKeysNew = new LinkedList<Integer>(this.pathKeys);
				pathKeysNew.add(this.leaf.childrenMap.size()+1);
				pqNew = new PersistentQueue2<E>(this.root, pqTreeNodeNew, pathKeysNew);
				this.leaf.childrenMap.put(this.leaf.childrenMap.size()+1, pqTreeNodeNew);
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
	public PersistentQueue2<E> dequeue() {
		if (this.size() == 0) {
			throw new NoSuchElementException();
		}
		
		PersistentQueue2<E> pqNew = null;
		
		switch (this.size()) {
			case 1:
				pqNew = new PersistentQueue2<E>();
				break;
			default:
				List<Integer> pathKeysNew = new LinkedList<Integer>(this.pathKeys);
				pathKeysNew.remove(0);
				pqNew = new PersistentQueue2<E>(this.root.childrenMap.get(this.pathKeys.get(0)), this.leaf, pathKeysNew);
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
		 if (this.size() == 0) {
			 throw new NoSuchElementException();
		 }
		 
		 return this.root.e;
	 }
	 
	 /**
	  * Returns the number of objects in this queue.
	  * @return
	  */
	 public int size() {
		 return (this.pathKeys==null ? 0 : this.pathKeys.size()+1);
	 }
	 
	 /**
	  * Returns the String view of this persistent queue.
	  * @return
	  */
	 public String toString() {
		 StringBuilder sb = new StringBuilder();
		 
		 sb.append("[");
		 
		 if (this.size() != 0) {
			 PQTreeNode<E> tmpTreeNode = this.root;
			 
			 sb.append(this.root.e.toString());
			 
			 for (Integer key : this.pathKeys) {
				 tmpTreeNode = tmpTreeNode.childrenMap.get(key);
				 sb.append(", ").append(tmpTreeNode.e.toString());
			 }
		 }
		 
		 sb.append("]");
		 
		 return sb.toString();
	 }
}