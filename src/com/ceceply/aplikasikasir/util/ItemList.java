package com.ceceply.aplikasikasir.util;

import com.ceceply.aplikasikasir.model.Item;

import java.util.ArrayList;

/**
 *
 *
 */
public class ItemList extends ArrayList<Item> {
	private Json json;

	/**
	 *
	 */
	public ItemList() {
		super();

		this.json = new Json();

		super.addAll(this.json.readAllItemsFromJson());
	}
}
