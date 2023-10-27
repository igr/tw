package dev.oblac.thwar.working1;

import dev.oblac.thwar.util.Futures;
import dev.oblac.thwar.util.Sleep;
import dev.oblac.thwar.util.StopWatches;
import dev.oblac.thwar.work.Work;
import dev.oblac.thwar.work.Workers;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static dev.oblac.thwar.util.Threads.CPU_COUNT;

public class RunVirtualWork {

	public static void main(String[] args) {
		Sleep.seconds(1);

		var sw = StopWatches.start();
		var futures =
				IntStream.range(0, CPU_COUNT * 100)
					.mapToObj(i -> CompletableFuture.supplyAsync(Work::run, Workers.virtualThreadPool))
					.toList();

		Futures.allOf(futures).join();

		var end = StopWatches.stop(sw);
		System.out.println(end.get());
	}
}
