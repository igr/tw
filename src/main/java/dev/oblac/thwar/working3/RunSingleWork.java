package dev.oblac.thwar.working3;

import dev.oblac.thwar.util.Sleep;
import dev.oblac.thwar.util.StopWatches;
import dev.oblac.thwar.work.Work;

public class RunSingleWork {

	public static void main(String[] args) {
		Sleep.seconds(1);

		var elapsed = StopWatches.run(() -> {
			String pi = Work.runw();
			System.out.println(pi);
		});

		System.out.println(elapsed);
		// 3 seconds -> 55 seconds
	}
}
