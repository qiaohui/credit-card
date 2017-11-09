package com.pay.card.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.card.dao.CreditBankDao;
import com.pay.card.model.CreditBank;
import com.pay.card.service.CreditBankService;

@Service
public class CreditBankServiceImpl implements CreditBankService {

    @Autowired
    private CreditBankDao bankDao;

    @Override
    public List<CreditBank> getBanks() {
        return bankDao.findAll();
    }

}
