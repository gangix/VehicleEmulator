package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DefectAlertTransaction;
import com.example.demo.repository.DefectAlertTransactionRepository;

@Service
public class DefectAlertTransactionServiceImpl implements DefectAlertTransactionService {

	private final DefectAlertTransactionRepository defectTransactionRepository;

	@Autowired
	public DefectAlertTransactionServiceImpl(DefectAlertTransactionRepository defectTransactionRepository) {
		super();
		this.defectTransactionRepository = defectTransactionRepository;
	}

	@Override
	public DefectAlertTransaction save(DefectAlertTransaction defectAlertTransaction) throws Exception {
		return defectTransactionRepository.save(defectAlertTransaction);
	}

}
