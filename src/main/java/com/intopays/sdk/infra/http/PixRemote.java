package com.intopays.sdk.infra.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.core.enums.EnvironmentTypeEnum;
import com.intopays.sdk.core.models.Pix;
import com.intopays.sdk.infra.config.Environment;
import com.intopays.sdk.infra.http.response.PageResponse;

import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class PixRemote {
    private final String baseUrl;
    private final String token;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public PixRemote(IntopaysConstructor config) {
        this.token = config.getToken();
        this.baseUrl = Environment.get(config.getMode()).getHost();
    }

    public Pix create(Pix data) throws IOException {
        HttpPost request = new HttpPost(this.baseUrl + "/v1/pixs");

        String json = objectMapper.writeValueAsString(data);
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);

        request.setHeader("Authorization", this.token);
        request.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());

            if (statusCode < 200 || statusCode >= 300) {
                throw new IOException("Failed to create pix. Status: " + statusCode + ". Response: " + responseBody);
            }

            return objectMapper.readValue(responseBody, Pix.class);
        }
    }

    public PageResponse<Pix> search(Map<String, String> queryParams) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(this.baseUrl + "/v1/pixs");

        if (queryParams != null && !queryParams.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name()))
                          .append("=")
                          .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name()))
                          .append("&");
            }
            urlBuilder.setLength(urlBuilder.length() - 1); // remove trailing &
        }

        HttpGet request = new HttpGet(urlBuilder.toString());
        request.setHeader("Authorization", this.token);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());

            if (statusCode < 200 || statusCode >= 300) {
                throw new IOException("Failed to search pixs. Status: " + statusCode + ". Response: " + responseBody);
            }

            return objectMapper.readValue(responseBody,
                objectMapper.getTypeFactory().constructParametricType(PageResponse.class, Pix.class)
            );
        }
    }

    public Pix find(String id) throws IOException {
        HttpGet request = new HttpGet(this.baseUrl + "/v1/pixs/" + id);
        request.setHeader("Authorization", this.token);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());

            if (statusCode < 200 || statusCode >= 300) {
                throw new IOException("Failed to find pix. Status: " + statusCode + ". Response: " + responseBody);
            }

            return objectMapper.readValue(responseBody, Pix.class);
        }
    }
}

