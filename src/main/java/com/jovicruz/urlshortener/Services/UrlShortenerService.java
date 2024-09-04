package com.jovicruz.urlshortener.Services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jovicruz.urlshortener.domain.Url;
import com.jovicruz.urlshortener.domain.UrlRepository;
import com.jovicruz.urlshortener.dtos.UrlResponse;

@Service
public class UrlShortenerService {
    @Autowired
    UrlRepository repo;

    @Value("${base.url}")
    private String BASE_URL;
    


    public Optional<String> getOriginalUrl(String shortUrl){
        return repo.findOriginalUrlByShortenedUrl(shortUrl)
               .map(url -> url.getOriginalUrl());

    }

    public UrlResponse saveShortenedUrl(String urlOriginal){
        Url urlShort = new Url();
        urlShort.setDateAdded(LocalDateTime.now());
        urlShort.setOriginalUrl(urlOriginal); 

        String generatedUrl = generateShortUrl(urlOriginal);
        urlShort.setShortenedUrl(generatedUrl);

        urlShort = repo.save(urlShort);

        UrlResponse response = new UrlResponse(
            urlShort.getOriginalUrl(),
            BASE_URL + "/r/" + urlShort.getShortenedUrl() 
        );
        

        return response;
    }

    public String generateShortUrl(String urlOriginal){
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
