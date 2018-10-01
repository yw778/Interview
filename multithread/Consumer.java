package multithread;

import java.util.*;

public class Consumer implements Runnable {
	BlockQueue<Integer> queue;
	public Consumer(BlockQueue<Integer> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			queue.get();
			System.out.println("Consumer get value.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
