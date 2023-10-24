package dev.oblac.thwar.working2;

import dev.oblac.thwar.util.Futures;
import dev.oblac.thwar.util.Sleep;
import dev.oblac.thwar.util.StopWatches;
import dev.oblac.thwar.work.Work;
import dev.oblac.thwar.work.Workers;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static dev.oblac.thwar.util.Threads.CPU_COUNT;

public class RunContextSwitchWork {

	public static void main(String[] args) {
		Sleep.seconds(1);

		var sleepers = IntStream.range(0, 6000)
			.mapToObj(i -> CompletableFuture.runAsync(Work::infSleep, Workers.cachedThreadPool))
			.toList();

		var sw = StopWatches.start();
		var futures =
				IntStream.range(0, CPU_COUNT * 5)
//				.mapToObj(i -> CompletableFuture.supplyAsync(Work::run, Workers.cachedThreadPool))
						.mapToObj(i -> CompletableFuture.supplyAsync(Work::run, Workers.fixedThreadPool))
						.toList();

		Futures.allOf(futures).join();

		var end = StopWatches.stop(sw);
		System.out.println(end.get());
	}
	// 22.5s
}
