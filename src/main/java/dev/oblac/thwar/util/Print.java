package dev.oblac.thwar.util;

import java.util.concurrent.ForkJoinPool;

public class Print {

	public static void out(ForkJoinPool forkJoinPool) {
		System.out.println("ForkJoinPool: " + forkJoinPool);
	}

	public static void currentThread() {
		System.out.println("Thread: " + Thread.currentThread().getName());
	}

	public static void out(String string) {
		System.out.println(string);
	}
}
