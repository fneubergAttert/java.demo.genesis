package com.genesis.case01.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//@Data
//@EqualsAndHashCode(exclude = "publishers")

@Entity
@Table(name = "Contact")
public class Contact {

	@Column(name = "ContactId", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contactId;

	@Column(name = "ContactLasteName", nullable = false, length = 255)
	private String contactLasteName;

	@Column(name = "ContactFirstName", nullable = false, length = 255)
	private String contactFirstName;

	@Column(name = "ContactAdress", nullable = false, length = 255)
	private String contactAdress;

	@Column(name = "ContactFreelance", nullable = true)
	private Boolean contactFreelance = false;

	@Column(name = "ContactTVA", nullable = true, length = 255)
	private String contactTVA;

	// Bis direction
	// -- ContactJob -> Table Name
	// -- ContactContactId -> joinColumns
	// -- CompanyCompanyId -> inverseJoinColumns
	@ManyToMany(targetEntity = Company.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "ContactJob"
		, joinColumns = { 
			@JoinColumn(name = "ContactContactId") }
		, inverseJoinColumns = {
			@JoinColumn(name = "CompanyCompanyId") })
	private List<Company> companyList = new java.util.ArrayList<Company>();

	
	public List<Company> getCompanyList() {
		return companyList;
	}

	public void addCompany(Company value) {
		companyList.add(value);
	}

	public void removeCompany(Company value) {
		companyList.remove(value);
	}

	
	private void setContactId(Long value) {
		this.contactId = value;
	}

	public Long getContactId() {
		return contactId;
	}

	public Long getORMID() {
		return getContactId();
	}

	public void setContactLasteName(String value) {
		this.contactLasteName = value;
	}

	public String getContactLasteName() {
		return contactLasteName;
	}

	public void setContactFirstName(String value) {
		this.contactFirstName = value;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactAdress(String value) {
		this.contactAdress = value;
	}

	public String getContactAdress() {
		return this.contactAdress;
	}

	public Boolean getContactFreelance() {
		return contactFreelance;
	}

	public void setContactFreelance(Boolean contactFreelance) {
		this.contactFreelance = contactFreelance;
	}
	

	public void setContactTVA(String value) {
		this.contactTVA = value;
	}

	public String getContactTVA() {
		return contactTVA;
	}

	public String toString() {
		return String.valueOf(getContactId());
	}

}
