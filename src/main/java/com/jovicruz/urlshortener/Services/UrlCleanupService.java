package com.jovicruz.urlshortener.Services;

import com.jovicruz.urlshortener.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UrlCleanupService {
    @Autowired
    UrlRepository repo;

    @Transactional
    @Scheduled(cron = "0 0 * * * ?")
    public void deleteExpiredUrls() {
        LocalDateTime expirationTime = LocalDateTime.now().minusHours(24);
        int deletedCount = repo.deleteByDateAddedBefore(expirationTime);
        System.out.println("Number of expired URLs deleted: " + deletedCount);
    }
}
