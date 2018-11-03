package com.client.keranjangkita.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="Item")
public class Item {
	
	@Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "item_id")
    private String id;
	@Column(name="item_code")
	private String itemCode;
	@Column(name="bar_code")
	private String barCode;
	private String name;
	@Column(name="merchant_code")
	private String merchantCode;
	private String weight;
	@Column(name="weight_type")
	private String weightType;
	// panjang lebar tinggi (PLT)
	//panjang
	@Column(name="length_of_item")
	private String lengthOfItem;
	//lebar
	@Column(name="width_of_item")
	private String widthOfItem;
	//tinggi
	@Column(name="height_of_item")
	private String heightOfItem;
	private String description;
	private double price;
	private String netto;
	private String brutto;
	@Column(name="stock")
	private Integer inStock;
	//unite of measure
	private String uom;
	@Column(name="amount_availability")
	private Integer amount_availability;
	@Column(name="image_url")
	private String imageUrl;
	private String status;
	private String note;
	@Transient
	private String statusItem;
	@Transient
	private String statusStock;
	@Transient
	private int qty;
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getStatusItem() {
		return statusItem;
	}
	public void setStatusItem(String statusItem) {
		this.statusItem = statusItem;
	}
	public String getStatusStock() {
		return statusStock;
	}
	public void setStatusStock(String statusStock) {
		this.statusStock = statusStock;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getWeightType() {
		return weightType;
	}
	public void setWeightType(String weightType) {
		this.weightType = weightType;
	}
	public String getLengthOfItem() {
		return lengthOfItem;
	}
	public void setLengthOfItem(String lengthOfItem) {
		this.lengthOfItem = lengthOfItem;
	}
	public String getWidthOfItem() {
		return widthOfItem;
	}
	public void setWidthOfItem(String widthOfItem) {
		this.widthOfItem = widthOfItem;
	}
	public String getHeightOfItem() {
		return heightOfItem;
	}
	public void setHeightOfItem(String heightOfItem) {
		this.heightOfItem = heightOfItem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getNetto() {
		return netto;
	}
	public void setNetto(String netto) {
		this.netto = netto;
	}
	public String getBrutto() {
		return brutto;
	}
	public void setBrutto(String brutto) {
		this.brutto = brutto;
	}
	public Integer getInStock() {
		return inStock;
	}
	public void setInStock(Integer inStock) {
		this.inStock = inStock;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public Integer getAmount_availability() {
		return amount_availability;
	}
	public void setAmount_availability(Integer amount_availability) {
		this.amount_availability = amount_availability;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
