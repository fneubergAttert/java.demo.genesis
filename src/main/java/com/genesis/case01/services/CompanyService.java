package com.genesis.case01.services;

import java.util.List;

import com.genesis.case01.domain.Company;

public interface CompanyService {

	
	public Company findCompanyById(Long id);
	
	
	public List<Company> findAllCompanys();


	public Company saveCompany(Company Company);


	public void deleteCompanyById(Long id);
	
}
