package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FuelTransaction;
import com.example.demo.repository.FuelTransactionRepository;

@Service
public class FuelTransactionServiceImpl implements FuelTransactionService {

	
	private final FuelTransactionRepository fuelTransactionRepository;

	@Autowired
	public FuelTransactionServiceImpl(FuelTransactionRepository fuelTransactionRepository) {
		super();
		this.fuelTransactionRepository = fuelTransactionRepository;
	}



	@Override
	public FuelTransaction save(FuelTransaction fuelTransaction) throws Exception {
		return fuelTransactionRepository.save(fuelTransaction);
	}

}
