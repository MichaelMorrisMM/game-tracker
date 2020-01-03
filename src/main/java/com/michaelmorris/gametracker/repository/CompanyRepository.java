package com.michaelmorris.gametracker.repository;

import com.michaelmorris.gametracker.model.Company;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<Company, Long> {

}
