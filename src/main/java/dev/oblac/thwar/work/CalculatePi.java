package dev.oblac.thwar.work;

import dev.oblac.thwar.util.Sleep;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.Semaphore;

/**
 * Long-running task that calculates PI.
 * It supports sleeping on specified term (iteration).
 */
class CalculatePi {

	static String calc3sec() {
		return new CalculatePi().calculatePi(13000, 0, 0);
	}

	static String calcWait(int sleepOnTerm, int sleepMillis) {
		return new CalculatePi().calculatePi(13000, sleepOnTerm, sleepMillis);
	}

	static String calcSemaphore(int sleepOnTerm, int sleepMillis, Semaphore semaphore) {
		try {
			return new CalculatePi().calculatePi(13000, sleepOnTerm, sleepMillis, semaphore);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	String calculatePi(int digits, int sleepOnTerm, int sleepMillis) {

		MathContext mc = new MathContext(digits);

		BigDecimal sum = new BigDecimal(0, mc);

		int terms = 10000;

		for (int i = 1; i <= terms; i++) {
			BigDecimal term = new BigDecimal(4, mc).divide(new BigDecimal(2 * i - 1, mc), mc);

			// Add or subtract the term based on the parity
			if (i % 2 == 0) {
				sum = sum.subtract(term, mc);
			} else {
				sum = sum.add(term, mc);
			}

			if (sleepOnTerm != 0 && i % sleepOnTerm == 0) {
				Sleep.millis(sleepMillis);
			}
		}

		return sum.toString();
	}

	String calculatePi(int digits, int sleepOnTerm, int sleepMillis, Semaphore semaphore) throws InterruptedException {
		semaphore.acquire();
		MathContext mc = new MathContext(digits);

		BigDecimal sum = new BigDecimal(0, mc);

		int terms = 10000;

		for (int i = 1; i <= terms; i++) {
			BigDecimal term = new BigDecimal(4, mc).divide(new BigDecimal(2 * i - 1, mc), mc);

			// Add or subtract the term based on the parity
			if (i % 2 == 0) {
				sum = sum.subtract(term, mc);
			} else {
				sum = sum.add(term, mc);
			}

			if (sleepOnTerm != 0 && i % sleepOnTerm == 0) {
				semaphore.release();
				Sleep.millis(sleepMillis);
				semaphore.acquire();
			}
		}
		semaphore.release();
		return sum.toString();
	}
}
