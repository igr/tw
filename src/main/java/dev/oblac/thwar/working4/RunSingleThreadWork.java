package dev.oblac.thwar.working4;

import dev.oblac.thwar.util.Sleep;
import dev.oblac.thwar.util.StopWatches;
import dev.oblac.thwar.work.CalculatePiRecursive;

import java.util.ArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.stream.IntStream;

import static dev.oblac.thwar.util.Threads.CPU_COUNT;

public class RunSingleThreadWork {

	public static void main(String[] args) {
		Sleep.seconds(1);

		var sw = StopWatches.start();
		var list = IntStream.range(0, 6 * CPU_COUNT)
				.mapToObj(i -> new CalculatePiRecursive().start())
				.toList();
		while (true) {
			var newList = new ArrayList<ScheduledFuture<CalculatePiRecursive.Calculation>>();
			list.forEach(f -> {
				try {
					var a = f.get();
					if (a != null) {
						newList.add(CalculatePiRecursive.continueNext(a));
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});

			if (newList.isEmpty()) {
				break;
			}
			list = newList;
		}

		var end = StopWatches.stop(sw);
		System.out.println(end.get());
	}
}
