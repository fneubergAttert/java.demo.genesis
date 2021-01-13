package com.genesis.case01.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "Address")
@Table(name = "address")
//@JsonIgnoreProperties("companies")

@JsonIgnoreProperties({"hibernateLazyInitializer","handler","companies"})
public class Address implements Serializable {

	private static final long serialVersionUID = -1572321857210999640L;


	@Column(name = "AddressId", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adresseId;




	public void setCompanies(List<CompanyAddress> companies) {
		this.companies = companies;
	}

	//@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY,
			cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        })
	private List<CompanyAddress> companies = new ArrayList<>();

	public Address() {
	}

	// @Column(name="AddressStreet", nullable=false, length=255)
	private String addressStreet;

	// @Column(name="AddressCity", nullable=false, length=255)
	private String addressCity;

	public Address(String addressStreet, String addressCity) {
		this.addressStreet = addressStreet;
		this.addressCity = addressCity;
	}

	

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	
	public Long getAdresseId() {
		return adresseId;
	}


	public void setAdresseId(Long adresseId) {
		this.adresseId = adresseId;
	}

	/*
	public List<CompanyAddress> getCompanies() {
		return companies;
	}
	*/
	
	
	/*
	public List<CompanyAddress> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CompanyAddress> posts) {
		this.companies = posts;
	}
	*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addressCity == null) ? 0 : addressCity.hashCode());
		result = prime * result + ((addressStreet == null) ? 0 : addressStreet.hashCode());
		result = prime * result + ((adresseId == null) ? 0 : adresseId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (addressCity == null) {
			if (other.addressCity != null)
				return false;
		} else if (!addressCity.equals(other.addressCity))
			return false;
		if (addressStreet == null) {
			if (other.addressStreet != null)
				return false;
		} else if (!addressStreet.equals(other.addressStreet))
			return false;
		if (adresseId == null) {
			if (other.adresseId != null)
				return false;
		} else if (!adresseId.equals(other.adresseId))
			return false;
		return true;
	}

}