package com.vineet.java8.concurrency;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class ProducerConsumerOld {

	public static void main(String... args) {

		System.out.println("Starting Producer Consumer Program");
		LinkedList<Integer> list = new LinkedList<Integer>();

		Producer producer = new Producer(list);
		Consumer consumer = new Consumer(list);

		Thread t1 = new Thread(producer, "Producer Thread");
		Thread t2 = new Thread(consumer, "Consumer Thread");

		t1.start();
		t2.start();

		System.out.println("Stopping Producer Consumer Program");
	}
}

class Producer implements Runnable {

	public Producer(LinkedList<Integer> list) {
		System.out.println("Producer Constructor");
		this.list = list;
	}

	private LinkedList<Integer> list = new LinkedList<Integer>();

	@Override
	public void run() {
		System.out.println("Producer started");
		this.produce();
	}

	public void produce() {

		while (true) {

			try {
				if (this.list.size() == 0) {
					int number = ThreadLocalRandom.current().nextInt(500);
					System.out.println("Produced:" + number);
					this.list.add(number);
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Producer Exception:" + e.getMessage());
				e.printStackTrace();
			}
		}

	}
}

class Consumer implements Runnable {

	public Consumer(LinkedList<Integer> list) {
		System.out.println("Consumer Constructor");
		this.list = list;
	}

	private LinkedList<Integer> list = new LinkedList<Integer>();

	@Override
	public void run() {
		System.out.println("Consumer started");
		try {
			this.consume();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void consume() throws InterruptedException {
		synchronized (this.list) {
			while (true) {
				if (this.list.size() > 0) {
					System.out.println("Consumed:" + list.removeFirst());
				}
				Thread.sleep(1000);
			}
		}
	}
}