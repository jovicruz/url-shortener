package com.jovicruz.urlshortener.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlResponse {  
    private String originalUrl;
    private String completeUrl;
    private String qrCodeUrl;
}
