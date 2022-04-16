package com.ceceply.aplikasikasir.model;

import java.text.NumberFormat;
import java.util.Locale;

public class Item {
	private String name;
	private Long price;

	public Item() {}

	public Item(String name, Long price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	public Object[] toArray() {
		return new Object[] {this.name, this.price};
	}
}
