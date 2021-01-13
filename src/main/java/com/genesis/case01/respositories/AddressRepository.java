package com.genesis.case01.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.genesis.case01.domain.Address;
import com.genesis.case01.domain.Company;

/**
 * @author fred_
 *
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
