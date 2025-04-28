package com.vineet.java8.concurrency;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class BlockingQueueSolution {

	public static void main(String[] args) {

		int BOUND = 10;
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);
		new Thread(new NumbersProducer(queue)).start();
		new Thread(new NumbersConsumer(queue, true), "Even Consumer").start();
		new Thread(new NumbersConsumer(queue, false), "Odd Consumer").start();
	}

}

class NumbersConsumer implements Runnable {
	private BlockingQueue<Integer> queue;
	private boolean evenConsumer = false;

	public NumbersConsumer(BlockingQueue<Integer> queue, boolean evenConsumer) {
		this.queue = queue;
		this.evenConsumer = evenConsumer;
	}

	public void run() {
		try {
			while (true) {
				Integer number = queue.peek();
				if (number != null) {
					if (number % 2 == 0 && this.evenConsumer) {
						System.out.println(Thread.currentThread().getName() + " Consumed: " + queue.take());
					}

					if (number % 2 != 0 && !this.evenConsumer) {
						System.out.println(Thread.currentThread().getName() + " Consumed: " + queue.take());
					}
				}
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}

class NumbersProducer implements Runnable {
	
	private BlockingQueue<Integer> numbersQueue;

	public NumbersProducer(BlockingQueue<Integer> numbersQueue) {
		this.numbersQueue = numbersQueue;
	}

	public void run() {
		try {
			generateNumbersInfinitely();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void generateNumbersInfinitely() throws InterruptedException {
		while (true) {
			int number = ThreadLocalRandom.current().nextInt(100);
			numbersQueue.put(number);
			System.out.println(Thread.currentThread().getName() + " Generated: " + number);
			Thread.sleep(1000);
		}
	}
}