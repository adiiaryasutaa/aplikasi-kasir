package com.ceceply.aplikasikasir.util;

import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;
import java.text.ParseException;

public class CurrencyConverter {

	public static @NotNull String toCurrency(Long value) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		return numberFormat.format(value).replace("IDR", "Rp ");
	}

	public static Long toLong(String value) {
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
		try {
			return (Long) numberFormat.parse(value);
		} catch (ParseException e) {
			System.err.println(e.toString());
		}
		return 0L;
	}

}
