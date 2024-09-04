package com.jovicruz.urlshortener.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UrlRequest {  
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(http|https)://.*$", message = "URL must start with http:// or https://")
    private String url;
}