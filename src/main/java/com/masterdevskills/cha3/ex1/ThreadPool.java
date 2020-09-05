package com.masterdevskills.cha3.ex1;


import java.util.LinkedList;

//TODO: Create a thread group field
// Create a LinkedList field containing Runnable
// Hint: Since LinkedList is not thread-safe, we need to synchronize it
public class ThreadPool {
	private LinkedList<Runnable> jobs = new LinkedList<>();
	private ThreadGroup threadGroup = new ThreadGroup("MyThreadpool-");
	private volatile boolean running = true;
	public ThreadPool(int poolSize) {
		for (int i =0; i < poolSize; i++) {
			Worker worker = new Worker(threadGroup,(String.valueOf(i)));
			worker.start();
		}
	}

	private Runnable take() throws InterruptedException {
		//TODO if the LinkedList is empty, we wait
		// remove the first job from the LinkedList and return it
		synchronized (jobs) {
			while (jobs.isEmpty()) {
				jobs.wait();
			}
			return jobs.removeFirst();
		}
	}

	public void submit(Runnable job) {
		//TODO Add the job to the LinkedList and notifyAll
		synchronized (jobs) {
			jobs.add(job);
			jobs.notifyAll();
		}
	}

	public int getRunQueueLength() {
		//TODO return the length of the LinkedList
		// remember to also synchronize!
		synchronized (jobs) {
			return jobs.size();
		}
	}

	public void shutdown() {
        this.running = false;
        threadGroup.interrupt();
	}

	private class Worker extends Thread {
		public Worker(ThreadGroup group, String name) {
			super(group, name);
		}

		public void run() {
			//TODO
			// we run in an infinite loop:
			// remove the next job from the linked list using take()
			// we then call the run() method on the job
			while(running) {
				Runnable job = null;
				try {
					job = take();
					job.run();
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}


