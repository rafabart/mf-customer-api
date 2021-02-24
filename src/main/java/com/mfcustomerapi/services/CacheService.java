package com.mfcustomerapi.services;

import com.mfcustomerapi.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CacheService {

    public static final String CUSTOMER = "customer";

    private final CacheManager cacheManager;
    private final CustomerRepository customerRepository;


    public void cachingAllValuesFromDb() {
        customerRepository.findAll().stream()
                .forEach(customer -> {
                    cacheManager.getCache(CUSTOMER).put(customer.getId(), customer);
                    cacheManager.getCache(CUSTOMER).put(customer.getEmail(), customer);
                });
    }


    public void evictAllCacheValues() {
        cacheManager.getCache(CUSTOMER).clear();
    }
}
