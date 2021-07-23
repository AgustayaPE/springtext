package com.springtest.springtest.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlChecker {

    private final String SITEUP = "Site is up";
    private final String SITEDOWN = "Site is down";
    private final String INCORRECTURL = "URL is incorrect";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnmessage = "";
        try {
            URL urlobj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode() /100;
            if(responseCode != 2 || responseCode != 3) {
                returnmessage = SITEDOWN;
            }
            else {
                returnmessage = SITEUP;
            }
        } catch (MalformedURLException e) {
            returnmessage = INCORRECTURL;
        } catch (IOException e) {
            returnmessage = SITEDOWN;
        }





        return returnmessage;
    }
    
}
