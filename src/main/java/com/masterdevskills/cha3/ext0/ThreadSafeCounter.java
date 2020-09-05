package com.masterdevskills.cha3.ext0;



//TODO make it threadsafe
public class ThreadSafeCounter {
	private int count;

	public void increment() {
		synchronized (this) {
			count = count + 1;
		}
	}

	public int getCount() {
		synchronized (this) {
			return count;
		}
	}
	public static void main(String[] args) throws InterruptedException {
		var counter = new ThreadSafeCounter();
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				counter.increment();
			}
		});

		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				counter.increment();
			}
		});

		Thread t3 = new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				counter.increment();
			}
		});

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();

		int count = counter.getCount();
		System.out.println(count);
	}
}

