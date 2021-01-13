package com.genesis.case01.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompanyAddressId implements Serializable {


	private static final long serialVersionUID = 5020066574219316421L;

	@Column(name = "company_id")
	private Long companyId;

	@Column(name = "adresse_id")
	private Long adresseId;

	public Long getAdresseId() {
		return adresseId;
	}

	public void setAdresseId(Long adresseId) {
		this.adresseId = adresseId;
	}

	private CompanyAddressId() {
	}

	public CompanyAddressId(Long companyId, Long adresseId) {
		this.companyId = companyId;
		this.adresseId = adresseId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresseId == null) ? 0 : adresseId.hashCode());
		result = prime * result + ((companyId == null) ? 0 : companyId.hashCode());
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
		CompanyAddressId other = (CompanyAddressId) obj;
		if (adresseId == null) {
			if (other.adresseId != null)
				return false;
		} else if (!adresseId.equals(other.adresseId))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		return true;
	}

	
	
}
