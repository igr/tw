package dev.oblac.thwar.work;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class CalculatePiRecursive {
	final static int sleepOnTerm = 100;
	final static int sleepMillis = 500;
	static int maxTerms = 10000;
	public CalculatePiRecursive() {
	}

	public record Calculation(
		MathContext mc,
		BigDecimal sum,
		int term
	) {}

	public String calculatePi() {
		return calculatePi(13000);
	}

	public String calculatePi(int digits) {
		MathContext mc = new MathContext(digits);
		BigDecimal sum = new BigDecimal(0, mc);
		var calculation = new Calculation(mc, sum, 0);
		try {
			return run(calculation).sum.toString();
		} catch (ExecutionException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public ScheduledFuture<Calculation> start() {
		MathContext mc = new MathContext(13000);
		BigDecimal sum = new BigDecimal(0, mc);
		return scheduleRun(new Calculation(mc, sum, 0));
	}
	private ScheduledFuture<Calculation> scheduleRun(Calculation calculation) {
		return Workers.scheduledThreadPool.schedule(
				() -> chunk(calculation), sleepMillis, TimeUnit.MILLISECONDS);
	}

	private Calculation run(Calculation calculation) throws ExecutionException, InterruptedException {
		while (true) {
			var newCalculation = scheduleRun(calculation).get();
			if (newCalculation == null) {
				return calculation;
			}
			calculation = newCalculation;
		}
	}

	public static ScheduledFuture<Calculation> continueNext(Calculation calculation) {
		return Workers.scheduledThreadPool.schedule(
				() -> chunk(calculation), sleepMillis, TimeUnit.MILLISECONDS);
	}

	private static Calculation chunk(Calculation calculation) {
		final var mc = calculation.mc();
		var i = calculation.term();
		var sum = calculation.sum();

		while (true) {
			final BigDecimal term = new BigDecimal(4, mc).divide(new BigDecimal(2 * i - 1, mc), mc);

			if (i % 2 == 0) {
				sum = sum.subtract(term, mc);
			} else {
				sum = sum.add(term, mc);
			}
			i++;
			if (i % sleepOnTerm == 0) {
				break;
			}
		}

		if (i >= maxTerms) {
			return null;
		}
		return new Calculation(mc, sum, i);
	}
}
