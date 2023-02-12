package com.clients.address.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="client")
public class Client implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Full Name is required.")
	private String fullName;
	
	@NotEmpty(message = "Photo ID is required.")
	@Pattern(regexp= "[0-9]{3}-[0-9]{7}-[0-9]{1}", message = "Photo ID must have pattern xxx-xxxxxxx-x")
	private String photoId;
	
	@NotEmpty(message = "Email Address is required.")
	private String email;
	
	@NotBlank(message = "Phone Number is required.")
	private String phoneNumber;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Past
	@NotNull(message = "Date of Birth is required.")
	private LocalDate birthdate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", fullName=" + fullName + ", photoId=" + photoId + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", birthdate=" + birthdate + "]";
	}
}
