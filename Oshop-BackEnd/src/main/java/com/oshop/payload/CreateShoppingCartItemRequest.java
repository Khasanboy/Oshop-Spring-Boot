package com.oshop.payload;

public class CreateShoppingCartItemRequest {
	
	private Long id;
	
	private Integer quantity;
	
	private Long productId;
	

	public CreateShoppingCartItemRequest() {
		super();
	}

	public CreateShoppingCartItemRequest(Long id, Integer quantity, Long productId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
}
