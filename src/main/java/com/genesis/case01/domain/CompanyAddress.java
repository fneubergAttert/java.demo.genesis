package com.genesis.case01.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "CompanyAddress")
@Table(name = "company_address")
public class CompanyAddress implements Serializable {

	private static final long serialVersionUID = -4077057321703870054L;

	@EmbeddedId
	private CompanyAddressId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("companyId") // Key in Company
	private Company company;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("adresseId")// Key in Company
	private Address address;

	@Column(name = "adresse_preference")
	private Long adressePreference = Long.valueOf(-1);

	private CompanyAddress() {
	}

	public CompanyAddress(Company company, Address address) {
		this.company = company;
		this.address = address;
		this.id = new CompanyAddressId(company.getCompanyId(), address.getAdresseId());
	}

	
	public CompanyAddressId getId() {
		return id;
	}

	public void setId(CompanyAddressId id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getAdressePreference() {
		return adressePreference;
	}

	public void setAdressePreference(Long adressePreference) {
		this.adressePreference = adressePreference;
	}
	
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		CompanyAddress that = (CompanyAddress) o;
		return Objects.equals(company, that.company) && Objects.equals(address, that.address);
	}

	

	

	@Override
	public int hashCode() {
		return Objects.hash(company, address);
	}
}
