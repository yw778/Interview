package multithread;

import java.util.*;

public class ProducerConsumer {
	public static void main(String[] args) {
		BlockQueue<Integer> queue = new BlockQueue<>(2);
		Thread c1 = new Thread(new Consumer(queue));
		Thread c2 = new Thread(new Consumer(queue));
		Thread c3 = new Thread(new Consumer(queue));
		Thread p1 = new Thread(new Producer(queue));
		Thread p2 = new Thread(new Producer(queue));
		Thread p3 = new Thread(new Producer(queue));
		c1.start();
		c2.start();
		c3.start();
		p1.start();
		p2.start();
		p3.start();
	}
}
