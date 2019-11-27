package com.qugenx.springbootwebservices.ws;

import static org.junit.Assert.assertTrue;

import com.qugenx.springbootwebservices.config.TestConfig;
import com.qugenx.springbootwebservices.ws.gen.AccountDetailsRequest;
import com.qugenx.springbootwebservices.ws.gen.AccountDetailsResponse;
import com.qugenx.springbootwebservices.ws.gen.EnumAccountStatus;
import com.qugenx.springbootwebservices.ws.gen.ObjectFactory;
import com.qugenx.springbootwebservices.ws.services.accounts.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class AccountServiceEndpointTest {

    @Autowired
    @Qualifier("accountServiceClient")
    private AccountService accountsServiceClient;

    private AccountDetailsRequest accountDetailsRequest;

    @Before
    public void setUp() throws Exception {

        ObjectFactory objectFactory = new ObjectFactory();
        accountDetailsRequest = objectFactory.createAccountDetailsRequest();
        accountDetailsRequest.setAccountNumber("12345");
    }

    @Test
    public void testGetAccountDetails() throws Exception {

        AccountDetailsResponse response = accountsServiceClient.getAccountDetails(accountDetailsRequest);
        assertTrue(response.getAccountDetails()!= null);
        assertTrue(response.getAccountDetails().getAccountNumber().equals("12345"));
        assertTrue(response.getAccountDetails().getAccountName().equals("Joe Bloggs"));
        assertTrue(response.getAccountDetails().getAccountBalance() == 3400);
        assertTrue(response.getAccountDetails().getAccountStatus().equals(EnumAccountStatus.ACTIVE));
    }

}
