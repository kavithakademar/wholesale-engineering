package com.xyzbank.banking.controller;

import com.xyzbank.banking.response.AccountResponse;
import com.xyzbank.banking.service.AccountDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Set;
import java.util.TreeSet;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(SpringExtension.class)
@AutoConfigureDataJpa
public class AccountControllerTest {
    @Autowired
    AccountController accountController;

    @MockBean
    AccountDAO accountService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnAccountList() throws Exception {

        Set<AccountResponse> expectedAccounts = new TreeSet<>();
        expectedAccounts.add(new AccountResponse("1000000001", "Salary Account",
                "Savings", Date.valueOf("2019-09-09"), "USD", 1000));
        expectedAccounts.add(new AccountResponse("1000000002", "OD Account",
                "Savings", Date.valueOf("2020-09-09"), "USD", 2000));

        when(accountService.accounts("1000")).thenReturn(expectedAccounts);
        mockMvc.perform(get("/accounts")
                .content("1000")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).equals(expectedAccounts);
        verify(accountService).accounts("1000");

    }
}
