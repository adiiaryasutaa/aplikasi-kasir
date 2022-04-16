package com.ceceply.aplikasikasir.model;

import com.ceceply.aplikasikasir.util.CurrencyConverter;

import java.util.HashMap;
import java.util.Vector;

public class ItemCart {

	private Integer index;
	private Item item;
	private Integer quantity;

	public static final int ITEM_INDEX        = 0;
	public static final int ITEM_NAME         = 1;
	public static final int ITEM_QUANTITY     = 2;
	public static final int ITEM_PRICE        = 3;
	public static final int ITEM_TOTAL_PRICE  = 4;

	public ItemCart(Integer index, Item item, Integer quantity) {
		this.index = index;
		this.item = item;
		this.quantity = quantity;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getPriceCurrency() {
		return CurrencyConverter.toCurrency(this.item.getPrice());
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getTotalPrice() {
		return this.item.getPrice() * this.quantity;
	}

	public String getTotalPriceCurrency() {
		return CurrencyConverter.toCurrency(this.getTotalPrice());
	}

	public Vector<Object> toVector() {
		Vector<Object> a = new Vector<>();

		a.add(ITEM_INDEX, this.index);
		a.add(ITEM_NAME, this.item.getName());
		a.add(ITEM_QUANTITY, this.getQuantity());
		a.add(ITEM_PRICE, this.getPriceCurrency());
		a.add(ITEM_TOTAL_PRICE, this.getTotalPriceCurrency());

		return a;
	}
}
