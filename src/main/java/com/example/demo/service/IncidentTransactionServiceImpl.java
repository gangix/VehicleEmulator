package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.IncidentTransaction;
import com.example.demo.repository.IncidentTransactionRepository;

@Service
public class IncidentTransactionServiceImpl implements IncidentTransactionService {

	private final IncidentTransactionRepository incidentTransactionRepository;

	@Autowired
	public IncidentTransactionServiceImpl(IncidentTransactionRepository incidentTransactionRepository) {
		super();
		this.incidentTransactionRepository = incidentTransactionRepository;
	}

	@Override
	public IncidentTransaction save(IncidentTransaction incidentTransaction) throws Exception {
		return incidentTransactionRepository.save(incidentTransaction);
	}

}
