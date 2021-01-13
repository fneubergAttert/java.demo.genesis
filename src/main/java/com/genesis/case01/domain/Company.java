package com.genesis.case01.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Company")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","address"})
public class Company implements Serializable {

		private static final long serialVersionUID = -1096380431655917324L;

		
		 

		  
	@Column(name = "CompanyId", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long companyId;

	@NotBlank(message = "CompanyName is mandatory")
	@Column(name = "CompanyName", nullable = false, length = 255)
	private String companyName;

	@Column(name = "CompanyTva", nullable = false, length = 255)
	private String companyTva;

	/**
	 * Link to property in Contact (mappedBy)
	 */
	@ManyToMany(mappedBy = "companyList" ,fetch = FetchType.LAZY)
	private List<Contact> ContactJob = new java.util.ArrayList<Contact>();

	
	/**
	 * Link to property in Adresse (mappedBy)
	 */
	/* OK
	@OneToMany(mappedBy = "company",fetch = FetchType.LAZY ,cascade = CascadeType.ALL, orphanRemoval = true)
	*/
	@JsonIgnore
	@OneToMany(mappedBy = "company"
			,fetch = FetchType.LAZY ,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
        }, orphanRemoval = true)
	private List<CompanyAddress> address = new ArrayList<>();

	
	@Transient 
	private Address  mainAddressCompany = null;

	@Transient 
	private List<Address>  alternateAddressCompany = null;

	
	
	public List<Address> getAlternateAddressCompany() {
		return alternateAddressCompany;
	}

	public void setAlternateAddressCompany(List<Address> alternateAddressCompany) {
		this.alternateAddressCompany = alternateAddressCompany;
	}

	@Transient 
	private Address  mainAddressHeadOffice = null;

	
	@PostLoad
    private void postLoadFunction(){

		System.out.println("postLoadFunction ");
		
		// Company Address
		List<CompanyAddress> addressFiltered0 = address //
				.stream() //
				.filter(c -> c.getAdressePreference().intValue() == 1) // AddressCompany
				.collect(Collectors.toList()//
				);

		if (addressFiltered0.size() > 0) {
			mainAddressCompany = addressFiltered0.get(0).getAddress();
		}
		
		
		// Head Office Address
		List<CompanyAddress> addressFiltered1 = address //
				.stream() //
				.filter(c -> c.getAdressePreference().intValue() == 2) // AddressMainOffice
				.collect(Collectors.toList()//
				);

		if (addressFiltered1.size() > 0) {
			mainAddressHeadOffice =  addressFiltered1.get(0).getAddress();
		}
		

		// AddressAlternative
		alternateAddressCompany = new ArrayList<Address>();

		address //
				.stream() //
				.filter(c -> c.getAdressePreference().intValue() < 0) // AddressAlternative
				.forEach(s -> {
					alternateAddressCompany.add(s.getAddress());
				});
	
		
    }
	
	
	/**
	 *  Head Office Address
	 * @return
	 */
	public Address getMainAddressHeadOffice() {
		
		return mainAddressHeadOffice;
	}

	public Address getMainAddressCompany() {
		return mainAddressCompany;
	}

	public void setMainAddressCompany(Address mainAddressCompany) {
		this.mainAddressCompany = mainAddressCompany;
	}

	public void setMainAddressHeadOffice(Address mainAddressHeadOffice) {
		this.mainAddressHeadOffice = mainAddressHeadOffice;
	}

	private void contactList(Contact value) {
		ContactJob.add(value);
	}

	// !! For Loop !
	// public List<Contact> getContactList() {
	// return ContactJob;
	// }

	public void setCompanyId(long value) {
		setCompanyId(value);
	}

	public void setCompanyId(Long value) {
		this.companyId = value;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public Long getORMID() {
		return getCompanyId();
	}

	public void setCompanyName(String value) {
		this.companyName = value;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyTva(String value) {
		this.companyTva = value;
	}

	public String getCompanyTva() {
		return companyTva;
	}

	public List<CompanyAddress> getAddress() {
		return address;
	}

	public void setAddress(List<CompanyAddress> address) {
		this.address = address;
	}

	/**
	 * Add Additionnnal address
	 * @param tag
	 */
	public void addAddress(Address tag) {
		CompanyAddress postTag = new CompanyAddress(this, tag);
		postTag.setAdressePreference(Long.valueOf(-1));
		address.add(postTag);
		//tag.getCompanies().add(postTag);
	}

	/**
	 * Add Main address
	 * @param tag
	 */
	public void addAddressMain(Address tag) {
		
		// Adapt All Others
		address
			.stream()
			.filter(c -> c.getAdressePreference().intValue() == 1) // AddressCompany
			.forEach(
						s -> s.setAdressePreference(Long.valueOf(-1))
					);

		// AddNew Address
		CompanyAddress postTag = new CompanyAddress(this, tag);
		postTag.setAdressePreference(Long.valueOf(1));
		address.add(postTag);
		
	}

	/**
	 * Add Main address
	 * @param tag
	 */
	public void setAddressMain(Address tag) {
		
		// Adapt All Others
		address
			.stream()
			.filter(c -> c.getAdressePreference().intValue() == 1) // AddressCompany
			.forEach(
						s -> s.setAdressePreference(Long.valueOf(-1))
					);

		// AddNew Address
		CompanyAddress postTag = new CompanyAddress(this, tag);
		postTag.setAdressePreference(Long.valueOf(1));
		address.add(postTag);
		
	}
	
	
	
	/**
	 * Add  Head Office Address
	 * @param tag
	 */
	public void addAddressHeadOffice(Address tag) {
		
		// Adapt All Others
		address
			.stream()
			.filter(c -> c.getAdressePreference().intValue() == 2) // mainAddressHeadOffice
			.forEach(
					
						s -> s.setAdressePreference(Long.valueOf(-1))
					);

		// AddNew Address
		CompanyAddress postTag = new CompanyAddress(this, tag);
		postTag.setAdressePreference(Long.valueOf(2));
		address.add(postTag);

	}
	
	
	/**
	 * Add  Head Office Address
	 * @param tag
	 */
	public void setAddressHeadOffice(Address tag) {
		
		// Adapt All Others
		address
			.stream()
			.filter(c -> c.getAdressePreference().intValue() == 2) // mainAddressHeadOffice
			.forEach(
					
						s -> s.setAdressePreference(Long.valueOf(-1))
					);

		// AddNew Address
		CompanyAddress postTag = new CompanyAddress(this, tag);
		postTag.setAdressePreference(Long.valueOf(2));
		address.add(postTag);

	}
	
	
	
	public void removeAddress(Address tag) {
		for (Iterator<CompanyAddress> iterator = address.iterator(); iterator.hasNext();) {
			CompanyAddress pCompanyAddress = iterator.next();

			if (pCompanyAddress.getAddress().equals(this) && pCompanyAddress.getAddress().equals(tag)) {
				iterator.remove();
				//pCompanyAddress.getAddress().getCompanies().remove(pCompanyAddress);
				pCompanyAddress.setAddress(null);
				pCompanyAddress.setCompany(null);
			}
		}
	}

	public String toString() {
		return String.valueOf(getCompanyId());
	}
}
