package com.lw.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class CompletableFutureTest {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(
			new Supplier<String>() {
				@Override
				public String get() {
					try {
						System.out.println(Thread.currentThread().getName());
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return "完成";
				}
			}
		);
		CompletableFuture<String> future1 = future.whenComplete(new BiConsumer<String, Throwable>() {
			@Override
			public void accept(String s, Throwable throwable) {
				System.out.println(Thread.currentThread().getName());
				System.out.println(s+2);
			}
		}).whenComplete(new BiConsumer<String, Throwable>() {
			@Override
			public void accept(String s, Throwable throwable) {
				System.out.println(Thread.currentThread().getName());
				System.out.println(s+1);
			}
		});
Thread.sleep(10000L);
	}
}
