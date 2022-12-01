package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "Product")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer  id;
	
	@Column(name = "product_title")
	private String title;

	@Column(name = "product_quantity")
	private String quantity;

	@Column(name = "product_size")
	private String size;
	
	@Lob
	@Column(name = "product_image", columnDefinition = "mediumblob")
	private byte[] product_image;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public byte[] getProduct_image() {
		return product_image;
	}

	public void setProduct_image(byte[] product_image) {
		this.product_image = product_image;
	}

}
