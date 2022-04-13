package com.ceceply.applikasikasir.model;

public class ItemCart {

	private Item item;
	private Integer quantity;

	public ItemCart(Item item, Integer quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return this.item.getPrice() * this.quantity;
	}
}
