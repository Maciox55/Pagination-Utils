package com.github.ygimenez.model;

import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts two input arguments and returns no
 * result. This is the throwing specialization of {@link BiConsumer}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object, Object)}.
 *
 * @param <A> the type of the first argument to the operation
 * @param <B> the type of the second argument to the operation
 */
@FunctionalInterface
public interface ThrowingBiConsumer<A, B> extends BiConsumer<A, B> {

	/**
	 * Performs this operation on the given arguments.
	 *
	 * @param a the first input argument
	 * @param b the second input argument
	 */
	void acceptThrows(A a, B b) throws Exception;

	@Override
	default void accept(A a, B b) {
		try {
			acceptThrows(a, b);
		} catch (final Exception e) {
			Logger logger = JDALogger.getLog(this.getClass());
			logger.error(e.getMessage(), e);

			throw new RuntimeException(e);
		}
	}
}
