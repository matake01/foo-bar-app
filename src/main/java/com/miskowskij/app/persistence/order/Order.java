package com.miskowskij.app.persistence.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.Assert;

@Entity
@Table(name = "SampleOrder") // Needs to be explicitly named as Order is a reserved keyword
public class Order {

	private @Id @GeneratedValue Long id;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<LineItem> lineItems = new ArrayList<LineItem>();

	Order() {
		
	}

	/**
	 * Adds a {@link LineItem} to the {@link Order}.
	 * 
	 * @param lineItem must not be {@literal null}.
	 */
	public void add(LineItem lineItem) {

		Assert.notNull(lineItem, "Line item must not be null!");

		this.lineItems.add(lineItem);
	}

	@Entity
	@Table(name = "LineItem")
	public static class LineItem {

		private @Id @GeneratedValue Long id;
		private final String description;
		
		LineItem() {
			this.description = null;
		}
	}
}