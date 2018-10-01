package multithread;
import java.util.*;

public class BlockQueue<E> {
	
	private Queue<E> queue;
	private int limit;
	
	public BlockQueue(int limit) {
		queue = new LinkedList<>();
		this.limit = limit;
	}
	
	public synchronized void put(E val) throws InterruptedException {
		while(queue.size() == this.limit) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (this.queue.size() == 0) {
			// Must be notifyAll.
			notifyAll();
		}
		queue.add(val);
	}
	
	public synchronized E get() throws InterruptedException {
		while(queue.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (queue.size() == this.limit) {
			// Must be notifyAll.
			notifyAll();
		}
		return queue.poll();
	}
}
