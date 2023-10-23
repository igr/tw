package dev.oblac.thwar.pools1;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BoundedCachedThreadPool extends ThreadPoolExecutor {
	public BoundedCachedThreadPool(final int size, final boolean timeout) {
		super(size, size * 2 + 1, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
		if (timeout) {
			allowCoreThreadTimeOut(true);
		}
	}
}
