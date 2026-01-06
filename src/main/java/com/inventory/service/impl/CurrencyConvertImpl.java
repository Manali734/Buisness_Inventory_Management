package com.inventory.service.impl;

import com.inventory.service.CurrencyConversion;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import org.springframework.stereotype.Service;
import tools.jackson.core.JsonParser;

import java.io.IOException;

@Service
public class CurrencyConvertImpl implements CurrencyConversion {
    @Override
    public String convert(Double amount, String from , String to) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String APP_ID = "2fd024003e-12f868e59e-t8fj7n";
        Request request = new Request.Builder()
                .url("https://api.fastforex.io/convert?from="+from+"&to="+to+"&amount="+amount+"&api_key="+ APP_ID)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        return  response.body().string();
    }
}
