package com.ceceply.aplikasikasir.util;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 *
 */
public class CurrencyConverter {

	/**
	 *
	 * @param value
	 * @return
	 */
	public static @NotNull String toCurrency(Long value) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		return numberFormat.format(value).replace("IDR", "Rp ");
	}

	/**
	 *
	 * @param value
	 * @return
	 */
	public static Long toLong(@NotNull String value) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		try {
			return (Long) numberFormat.parse(value.replace("Rp ", "IDR"));
		} catch (ParseException e) {
			System.err.println(e);
		}
		return 0L;
	}

	/**
	 *
	 * @param values
	 * @return
	 */
	public static Long totalFromCurrency(Collection<String> values) {
		Long total = 0L;

		for (Object value : values) {
			total += toLong((String) value);
		}

		return total;
	}

	/**
	 *
	 * @param values
	 * @return
	 */
	public static String totalFromCurrencyToCurrency(Collection<String> values) {
		return toCurrency(totalFromCurrency(values));
	}

}
