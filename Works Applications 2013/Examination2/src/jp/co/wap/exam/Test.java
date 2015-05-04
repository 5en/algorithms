package jp.co.wap.exam;

public class Test {
	
	public static void main(String[] args) {
		long startTime;
		long endTime;
		double elapsedTime;
		
		PersistentQueue<Integer> pq = new PersistentQueue<Integer>();
		pq = pq.enqueue(0).enqueue(1).enqueue(2).dequeue().enqueue(3);
		PersistentQueue<Integer> pq2 = pq.enqueue(4).enqueue(5).dequeue();
		System.out.println(pq);
		System.out.println(pq2);
		

		startTime = System.nanoTime();
		PersistentQueue<Integer> pq4 = new PersistentQueue<Integer>();
		for (int i=0; i<10000; i++) {
			pq4 = pq4.enqueue(0);
			pq4.peek();
		}
		for (int i=0; i<10000; i++) {
			pq4 = pq4.dequeue();
			pq4.size();
		}
		endTime = System.nanoTime();
		elapsedTime = ((double)(endTime-startTime))/1000000000; //seconds
		System.out.println(elapsedTime);
		
		
		
		//sample
		startTime = System.nanoTime();
		PersistentQueueSample<Integer> pq3 = new PersistentQueueSample<Integer>();
		for (int i=0; i<10000; i++) {
			pq3 = pq3.enqueue(0);
			pq3.peek();
		}
		for (int i=0; i<10000; i++) {
			pq3 = pq3.dequeue();
			pq3.size();
		}
		endTime = System.nanoTime();
		elapsedTime = ((double)(endTime-startTime))/1000000000; //seconds
		System.out.println(elapsedTime);
		
	}
	
}
