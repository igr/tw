package dev.oblac.thwar.util;

public class Sleep {

	public static void millis(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void seconds(long seconds) {
		millis(seconds * 1000);
	}

	public static void forever() {
		millis(Long.MAX_VALUE);
	}
}
