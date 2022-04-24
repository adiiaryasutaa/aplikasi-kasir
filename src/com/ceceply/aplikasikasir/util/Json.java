package com.ceceply.aplikasikasir.util;

import com.ceceply.aplikasikasir.model.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;

/**
 *
 *
 */
public class Json {
	private Gson gson;

	/**
	 *
	 *
	 */
	public Json() {
		this.gson = new Gson();
	}

	/**
	 *
	 * @param item
	 */
	public void addItem(Item item) {
		String json = this.gson.toJson(item);
		System.out.println(json);
	}

	/**
	 *
	 * @return
	 */
	public Collection<Item> readAllItemsFromJson() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("items.json");
		String json = null;

		try {
			assert is != null;
			json = new String(is.readAllBytes());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

		if (json == null) {
			return Collections.emptyList();
		}

		Type collectionType = new TypeToken<Collection<Item>>(){}.getType();
		return this.gson.fromJson(json, collectionType);
	}
}
