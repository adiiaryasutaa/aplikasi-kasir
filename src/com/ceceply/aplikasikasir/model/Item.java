package com.ceceply.aplikasikasir.model;

/**
 *
 *
 */
public class Item {
	private String name;
	private Long price;

	/**
	 *
	 *
	 */
	public Item() {}

	/**
	 *
	 * @param name
	 * @param price
	 */
	public Item(String name, Long price) {
		this.name = name;
		this.price = price;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 * @return
	 */
	public Long getPrice() {
		return price;
	}

	/**
	 *
	 * @param price
	 */
	public void setPrice(Long price) {
		this.price = price;
	}

	/**
	 *
	 * @return name
	 */
	@Override
	public String toString() {
		return this.getName();
	}

	/**
	 *
	 * @return
	 */
	public Object[] toArray() {
		return new Object[] {this.name, this.price};
	}
}
