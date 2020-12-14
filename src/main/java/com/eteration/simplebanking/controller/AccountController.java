package com.eteration.simplebanking.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.service.AccountService;

@RestController
@RequestMapping("/account/v1")
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/id")
	public ResponseEntity<Account> getAccount(@PathVariable int id) {
		HttpHeaders headers=new HttpHeaders();
		
		Account account;
		try {
			account=accountService.findById(id);
		} catch (Exception e) {
			headers.add("Account-Header", e.getMessage());
			return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.accepted().headers(headers).body(account);
		
	}
	
    @PostMapping(value="/credit/{id}")
    public ResponseEntity credit(@PathVariable("id") int id, @RequestBody Map requestBody)  {
        HttpHeaders headers = new HttpHeaders();
        JSONObject jsonObject = new JSONObject();

        double amount = Double.parseDouble((String) requestBody.get("amount"));

        try {
            accountService.credit(amount, id);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("error", e.getMessage());
            jsonObject.put("status", "NOT OKEY");
            headers.add("Account-Header", e.getMessage());
            return ResponseEntity.accepted().headers(headers).body(jsonObject);
        }
    }
    
    @PostMapping(value="/debit/{id}")
    public ResponseEntity debit(@PathVariable("id") int id, @RequestBody Map requestBody)  {
        HttpHeaders headers = new HttpHeaders();
        JSONObject jsonObject = new JSONObject();

        double amount = Double.parseDouble((String) requestBody.get("amount"));

        try {
            accountService.debit(amount, id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (InsufficientBalanceException e) {
            jsonObject.put("error", e.getMessage());
            jsonObject.put("status", "NOT OKEY");
            headers.add("Account-Header", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).headers(headers).body(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("error", e.getMessage());
            jsonObject.put("status", "NOT OKEY");
            headers.add("Account-Header", e.getMessage());
            return ResponseEntity.badRequest().headers(headers).body(jsonObject);
        }
    }

}
