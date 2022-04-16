package com.ceceply.aplikasikasir.data;

import com.ceceply.aplikasikasir.factory.ItemCartFactory;
import com.ceceply.aplikasikasir.model.ItemCart;

import java.util.ArrayList;
import java.util.Vector;

public class ItemCartData {

	private ItemCartFactory itemCartFactory;

	public ItemCartData() {
		this.itemCartFactory = new ItemCartFactory();
	}

	public Vector<Vector<Object>> dataForTable() {
		Vector<Vector<Object>> a = new Vector<>();

		ArrayList<ItemCart> itemCarts = this.itemCartFactory.getArrayList();

		for (ItemCart itemCart : itemCarts) {
			a.add(itemCart.toVector());
		}

		return a;
	}

}
