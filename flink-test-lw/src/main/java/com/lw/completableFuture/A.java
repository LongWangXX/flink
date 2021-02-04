package com.lw.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class A {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 执行异步任务
		CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
			System.out.println("cf 任务执行开始");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("cf 任务执行结束");
			return "楼下小黑哥";
		});
//
		Executors.newSingleThreadScheduledExecutor().execute(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("主动设置 cf 任务结果");
			// 设置任务结果，由于 cf 任务未执行结束，结果返回 true
			cf.complete("程序通事");
		});
// 由于 cf 未执行结束，将会被阻塞。5 秒后，另外一个线程主动设置任务结果
		System.out.println("get:" + cf.get());
// 等待 cf 任务执行结束
		Thread.sleep(10000);
// 由于已经设置任务结果，cf 执行结束任务结果将会被抛弃
		System.out.println("get:" + cf.get());
/***
 * cf 任务执行开始
 * 主动设置 cf 任务结果
 * get:程序通事
 * cf 任务执行结束
 * get:程序通事
 */
	}
}
