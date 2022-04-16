package com.ceceply.aplikasikasir.factory;

import com.ceceply.aplikasikasir.model.Item;
import com.ceceply.aplikasikasir.model.ItemCart;

import java.util.ArrayList;
import java.util.Random;

public class ItemCartFactory {

	private ItemFactory itemFactory;

	public ItemCartFactory() {
		this.itemFactory = new ItemFactory();
	}

	public ArrayList<ItemCart> getArrayList() {
		ArrayList<Item> items = this.itemFactory.getArrayList();

		ArrayList<ItemCart> a = new ArrayList<>();

		int index = 1;
		for (Item item : items) {
			a.add(new ItemCart(index, item, new Random().nextInt(10 + 1) + 1));
			index++;
		}

		return a;
	}

}
