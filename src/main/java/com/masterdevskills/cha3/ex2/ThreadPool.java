package com.masterdevskills.cha3.ex2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

//TODO: Implement this thread pool using BlockingQueue
public class ThreadPool {

	private ThreadGroup threadGroup = new ThreadGroup("MyThreadPool");
	private BlockingQueue<Runnable> jobs = new LinkedBlockingQueue<>();
	private AtomicInteger pendingJobCount = new AtomicInteger();


	public ThreadPool(int poolSize) {
		for (int i = 0; i < poolSize; i++) {
			Job worker = new Job(threadGroup, (String.valueOf(i)));
			worker.start();
		}
	}

	private Runnable take() throws InterruptedException {
		pendingJobCount.decrementAndGet();
		return jobs.poll();
	}

	public void submit(Runnable job) {
		jobs.add(job);
		pendingJobCount.incrementAndGet();
	}

	public int getRunQueueLength() {
		jobs.size();
		return pendingJobCount.get();
	}

	public void shutdown() {

	}

	private class Job extends Thread {
		public Job(ThreadGroup group, String name) {
			super(group, name);
		}

		public void run() {
			//TODO
			// we run in an infinite loop:
			// remove the next job from the linked list using take()
			// we then call the run() method on the job

			while (true){
				try {
					take().run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}


