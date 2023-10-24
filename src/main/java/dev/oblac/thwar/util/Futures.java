package dev.oblac.thwar.util;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Futures {

	public static void waitForAll(Future<?>... futures) {
		for (Future<?> future : futures) {
			try {
				future.get();
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static <T> void waitForAll(List<CompletableFuture<T>> list) {
		CompletableFuture.allOf(list.toArray(CompletableFuture[]::new)).join();
	}

	public static <T> CompletableFuture<Void> allOf(List<CompletableFuture<T>> list) {
		return CompletableFuture.allOf(list.toArray(CompletableFuture[]::new));
	}
}
