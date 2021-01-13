/**
 * 
 */
package com.genesis.case01.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.genesis.case01.domain.Company;
import com.genesis.case01.respositories.CompanyRepository;

/**
 * @author fred_
 *
 */
@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository CompanyRepository;

	public CompanyServiceImpl(CompanyRepository CompanyRepository) {
		this.CompanyRepository = CompanyRepository;
	}

	@Override
	public Company findCompanyById(Long id) {

		Company exCompany = CompanyRepository.findById(id).orElse(null);

		return exCompany;

	}

	@Override
	public List<Company> findAllCompanys() {
		return CompanyRepository.findAll();
	}

	@Override
	public Company saveCompany(Company Company) {
		return CompanyRepository.save(Company);
	}

	
	@Override
	public void deleteCompanyById(Long id) {
		CompanyRepository.deleteById(null);
	}

}
