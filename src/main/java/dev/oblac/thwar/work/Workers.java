package dev.oblac.thwar.work;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static dev.oblac.thwar.util.Threads.CPU_COUNT;

public class Workers {

	public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(CPU_COUNT);
}
