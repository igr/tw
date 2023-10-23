package dev.oblac.thwar.work;

import dev.oblac.thwar.util.Sleep;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Long-running task that calculates PI.
 * It supports sleeping on specified term (iteration).
 */
class CalculatePi {

	static String calc10000() {
		return new CalculatePi().calculatePi(10000, 0, 0);
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
}
