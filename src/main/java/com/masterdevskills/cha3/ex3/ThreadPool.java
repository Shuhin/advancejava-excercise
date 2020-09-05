package com.masterdevskills.cha3.ex3;

import java.util.concurrent.*;

//TODO: Implement this thread pool using ExecutorService
public class ThreadPool {

	private ThreadPoolExecutor pool;

	public ThreadPool(int poolSize) {
		pool = new ThreadPoolExecutor(poolSize, poolSize, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	}

	private Runnable take() throws InterruptedException {
		throw new UnsupportedOperationException("not implemented");
	}

	public void submit(Runnable job) {
		pool.submit(job);
	}

	public int getRunQueueLength() {
		return pool.getQueue().size();
	}

	public void shutdown() {
		pool.shutdownNow();
	}

}


