package com.ceceply.aplikasikasir.model;

import com.ceceply.aplikasikasir.util.CurrencyConverter;

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

	/**
	 *
	 * @param index
	 * @param item
	 * @param quantity
	 */
	public ItemCart(Integer index, Item item, Integer quantity) {
		this.index = index;
		this.item = item;
		this.quantity = quantity;
	}

	/**
	 *
	 * @return
	 */
	public Integer getIndex() {
		return index;
	}

	/**
	 *
	 * @param index
	 */
	public void setIndex(Integer index) {
		this.index = index;
	}

	/**
	 *
	 * @return
	 */
	public Item getItem() {
		return item;
	}

	/**
	 *
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 *
	 * @return
	 */
	public String getPriceCurrency() {
		return CurrencyConverter.toCurrency(this.item.getPrice());
	}

	/**
	 *
	 * @return
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 *
	 * @param quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 *
	 * @return
	 */
	public Long getTotalPrice() {
		return this.item.getPrice() * this.quantity;
	}

	/**
	 *
	 * @return
	 */
	public String getTotalPriceCurrency() {
		return CurrencyConverter.toCurrency(this.getTotalPrice());
	}

	/**
	 *
	 * @return
	 */
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
