package com.lw.completableFuture;

import java.util.concurrent.*;

public class FutureTest {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		executor.shutdown();
	}

}
