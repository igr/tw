package dev.oblac.thwar.deadlock1;

import java.util.stream.IntStream;

public class PrintlnFun {
	// no longer deadlocks
	public static void main(String... args) {
		synchronized (System.out) {
			System.out.println("Hello World");
			IntStream.range(0, 30)
				.parallel()
				.forEach(System.out::println);
		}
	}
}