package com.ceceply.aplikasikasir.factory;

import com.ceceply.aplikasikasir.model.Item;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemFactory {

	public ArrayList<Item> getArrayList() {
		ArrayList<Item> a = new ArrayList<>();

		a.addAll(Arrays.asList(
			new Item("Minyak", 10_000L),
			new Item("Beras 10Kg", 60_000L),
			new Item("Sampo Sunsilk Hijab", 1_000L),
			new Item("Pasta Gigi Pepsodent", 5_000L),
			new Item("Celana Dalam Yelan", 10_000_000L)
		));

		return a;
	}
}
