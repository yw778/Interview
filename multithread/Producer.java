package multithread;

import java.util.*;

public class Producer implements Runnable{
	
	BlockQueue<Integer> queue;
	
	public Producer(BlockQueue<Integer> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			queue.put(1);
			System.out.println("Producer offer value.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
