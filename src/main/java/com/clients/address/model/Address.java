package com.clients.address.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="address")
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message= "Address is required.")
	private String address1;
	
	@NotEmpty(message= "City is required.")
	private String city;
	
	@NotEmpty(message= "Province is required.")
	private String province;
	
	@NotEmpty(message= "Zip Code is required.")
	@Pattern(regexp="(^\\d{5}$)|(^\\d{9}$)|(^\\d{5}-\\d{4}$)", message="Please enter a valid Zip Code")
	private String zipCode;
	
	@NotEmpty(message= "Country is required.")
	private String country;
	
	
	@JoinColumn(name= "client_id")
	@ManyToOne
	@NotNull(message= "Client is required.")
	private Client client;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
	 this.country = country;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", address1=" + address1 + ", city=" + city + ", province=" + province
				+ ", zipCode=" + zipCode + ", country=" + country + ", client=" + client + "]";
	}
	

}
