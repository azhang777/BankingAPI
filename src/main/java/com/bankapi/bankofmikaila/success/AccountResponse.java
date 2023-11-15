package com.bankapi.bankofmikaila.success;

import com.bankapi.bankofmikaila.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class AccountResponse {
    @Autowired
    private AccountService accountService;
}
