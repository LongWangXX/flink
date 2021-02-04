package com.lw.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class CompletionStageTest {
	public static void main(String[] args) {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello  楼下小黑哥");

		future.thenApply(new Function<String, String>() {
			@Override
			public String apply(String s) {
				return s+"sss";
			}
		});
	}
}
