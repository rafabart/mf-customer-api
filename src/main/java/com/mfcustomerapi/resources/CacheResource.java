package com.mfcustomerapi.resources;

import com.mfcustomerapi.services.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/caches")
public class CacheResource {

    private final CacheService cacheService;


    @ResponseStatus(OK)
    @GetMapping("/cleanCustomerCache")
    public void clean() {

        cacheService.evictAllCacheValues();
    }


    @ResponseStatus(OK)
    @GetMapping("/loadCustomerCache")
    public void load() {

        cacheService.cachingAllValuesFromDb();
    }
}
