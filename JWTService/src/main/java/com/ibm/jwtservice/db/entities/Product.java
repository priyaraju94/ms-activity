package com.ibm.jwtservice.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ibm.jwtservice.dto.ProductResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements DtoConvertor<ProductResponseDTO>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	private String productName;
	private int price;
	private boolean available;
	
	@Column(name = "store_id")
	private int storeId;

	@Override
	public ProductResponseDTO getDto() {
		ProductResponseDTO p = new ProductResponseDTO();
			p.setAvailable(this.available);
			p.setPrice(this.price);
			p.setProductId(this.productId);
			p.setProductName(this.productName);
			p.setStoreid(this.storeId);
		return p;
	}
	
	public Product()
	{
		
	}
	public Product(int id,String productName, int price, boolean available, int storeId)
	{
		this.productId=id;
		this.productName=productName;
		this.price= price; 
		this.available= available;
		this.storeId= storeId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
}

