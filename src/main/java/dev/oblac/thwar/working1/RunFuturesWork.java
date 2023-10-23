package dev.oblac.thwar.working1;

import dev.oblac.thwar.util.Sleep;
import dev.oblac.thwar.util.StopWatches;
import dev.oblac.thwar.work.Work;
import dev.oblac.thwar.work.Workers;

import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static dev.oblac.thwar.util.Threads.CPU_COUNT;

// just example running the same thing with Futures
public class RunFuturesWork {

	public static void main(String[] args) {
		Sleep.seconds(1);
		var sw = StopWatches.start();

		final var futures = IntStream.range(0, CPU_COUNT)
				.mapToObj(i -> Workers.cachedThreadPool.submit(Work::run))
				.toList();

		final var result = new StringBuilder();
		for (Future<String> future : futures) {
			try {
				result.append(future.get());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		System.out.println(result.length());

		var end = StopWatches.stop(sw);
		System.out.println(end.get());
	}
}
