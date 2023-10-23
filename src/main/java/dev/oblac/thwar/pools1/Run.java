package dev.oblac.thwar.pools1;

import dev.oblac.thwar.util.Futures;
import dev.oblac.thwar.util.Print;
import dev.oblac.thwar.util.Sleep;

import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Run {

	public static void main(String[] args) {
		// 🔥
		var pool = new BoundedCachedThreadPool(10, false);

		Print.out("Run #1");
		Futures.waitForAll(runOnPool(pool));

		// we sleep to allow threads to timeout
		Print.out("Sleeping");
		Sleep.seconds(15);

		Print.out("Run #2");
		Futures.waitForAll(runOnPool(pool));

		Print.out("Done");
	}

	private static Future<?>[] runOnPool(BoundedCachedThreadPool pool) {
		return IntStream.range(0, 100)
				.boxed()
				.map(i -> pool.submit(Print::currentThread))
				.toArray(Future[]::new);
	}
}
