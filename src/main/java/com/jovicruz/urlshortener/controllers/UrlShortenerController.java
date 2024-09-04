package com.jovicruz.urlshortener.controllers;

import com.jovicruz.urlshortener.Services.UrlShortenerService;
import com.jovicruz.urlshortener.dtos.UrlRequest;
import com.jovicruz.urlshortener.dtos.UrlResponse;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UrlShortenerController {

    @Autowired
    UrlShortenerService service;

    @GetMapping("/r/{shortUrl}")
    public ResponseEntity<String> redirectUrl(@PathVariable String shortUrl, HttpServletResponse response) {
        try {
            Optional<String> urlOriginal = service.getOriginalUrl(shortUrl);
    
            if (urlOriginal.isEmpty()) {
                //retorna 404 se a url não for encontrada
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Url not found for this short code");
            }
    
            response.sendRedirect(urlOriginal.get());
            return ResponseEntity.ok().build();
    
        } catch (IOException e) {
            //retorna 500 caso de falha no redirecionamento
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Redirection error");
        }
    
}

    @PostMapping("/newurl")
    public ResponseEntity<UrlResponse> createNewShortUrl(@Valid @RequestBody UrlRequest request){
        try {
            UrlResponse response = service.saveShortenedUrl(request.getUrl());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
