package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FuelTransaction;

@Repository
public interface FuelTransactionRepository extends CrudRepository<FuelTransaction, Long>{

}
