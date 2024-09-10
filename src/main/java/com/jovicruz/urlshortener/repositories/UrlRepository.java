package com.jovicruz.urlshortener.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import com.jovicruz.urlshortener.domain.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findOriginalUrlByShortenedUrl(String shortUrl);
    Boolean existsByShortenedUrl(String shortUrl);
    @Modifying
    int deleteByDateAddedBefore(LocalDateTime dateAdded);
}