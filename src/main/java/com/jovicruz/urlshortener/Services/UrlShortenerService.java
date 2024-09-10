package com.jovicruz.urlshortener.Services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jovicruz.urlshortener.domain.Url;
import com.jovicruz.urlshortener.dtos.UrlResponse;
import com.jovicruz.urlshortener.exceptions.UrlNotFoundException;
import com.jovicruz.urlshortener.repositories.UrlRepository;

@Service
public class UrlShortenerService {
    @Autowired
    UrlRepository repo;

    @Value("${base.url}")
    private String BASE_URL;
    


    public Optional<String> getOriginalUrl(String shortUrl){

        Optional<String> originalUrl = repo.findOriginalUrlByShortenedUrl(shortUrl)
        .map(url -> url.getOriginalUrl());

        if (originalUrl.isEmpty()) {
            throw new UrlNotFoundException();
        }
        return originalUrl;
    }

    public UrlResponse saveShortenedUrl(String urlOriginal){
        Url urlShort = new Url();
        urlShort.setDateAdded(LocalDateTime.now());
        urlShort.setOriginalUrl(urlOriginal); 

        String generatedUrl;
        do {
            generatedUrl = generateShortUrl();
        } while (repo.existsByShortenedUrl(generatedUrl));
        urlShort.setShortenedUrl(generatedUrl);
        urlShort = repo.save(urlShort);

        String fullRedirectUrl = BASE_URL + "/r/" + urlShort.getShortenedUrl();

        UrlResponse response = new UrlResponse(
            urlShort.getOriginalUrl(),
            fullRedirectUrl,
            "https://api.qrserver.com/v1/create-qr-code/?data=" + fullRedirectUrl
        );
        

        return response;
    }

    public String generateShortUrl(){
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                            + "0123456789"
                            + "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(5);
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(alphanumeric.length());
            sb.append(alphanumeric.charAt(index));
        }

        String randomString = sb.toString();
        return randomString;
    }
}
