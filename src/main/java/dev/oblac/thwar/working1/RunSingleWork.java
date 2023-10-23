package dev.oblac.thwar.working1;

import dev.oblac.thwar.util.Sleep;
import dev.oblac.thwar.util.StopWatches;
import dev.oblac.thwar.work.Work;

public class RunSingleWork {

	public static void main(String[] args) {
		Sleep.seconds(1);

		var elapsed = StopWatches.run(() -> {
			String pi = Work.run();
			System.out.println(pi);
		});

		System.out.println(elapsed);
		// 2.44 seconds
	}
}
