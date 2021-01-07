package com.xyzbank.banking.controller;

import com.xyzbank.banking.response.TransactionHistory;
import com.xyzbank.banking.service.TransactionDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;
import java.util.TreeSet;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@AutoConfigureDataJpa

public class TransactionHistoryControllerTest {
    @Autowired
    TransactionHistoryController transactionHistoryController;

    @MockBean
    TransactionDAO transactionService;


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAccountList() throws Exception {

        Set<TransactionHistory> transactionHistories = new TreeSet<>();
        transactionHistories.add(new TransactionHistory(30000, "Credited from ABC", "2018-06-30", true, "USD", "ODAccount"));
        transactionHistories.add(new TransactionHistory(1000, "Debited for Bill", "2019-08-28", false, "AUD", "SalaryAccount"));
        transactionHistories.add(new TransactionHistory(12000, "Debited from ATM", "2020-05-12", false, "RUPEE", "Demat Account"));
        when(transactionService.transactionHistory("1000000001")).thenReturn(transactionHistories);
        mockMvc.perform(get("/accounts/transaction")
                .content("1000000001")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).equals(transactionHistories);
        verify(transactionService).transactionHistory("1000000001");

    }

}
