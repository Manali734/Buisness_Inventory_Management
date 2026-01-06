package com.inventory.service;

import com.squareup.okhttp.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CurrencyConversion {
    String convert(Double amount , String from, String to) throws IOException;
}
