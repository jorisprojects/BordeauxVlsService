package com.instant.microservice.vls.controller;

import java.net.URI;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/*
 * Custom restTeplate to force content type in response headers
 */
public class CustomRestTemplate extends RestTemplate {
    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback callback, final ResponseExtractor<T> responseExtractor) throws RestClientException {
        return super.doExecute(url, method, callback, response -> {
            String contentType = response.getHeaders().getFirst("Content-Type");
            if (contentType.startsWith("text/xml")) response.getHeaders().setContentType(MediaType.TEXT_XML);
            return responseExtractor.extractData(response);
        });
    }
}