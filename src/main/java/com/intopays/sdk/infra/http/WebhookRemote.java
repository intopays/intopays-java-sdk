package com.intopays.sdk.infra.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.core.enums.EnvironmentTypeEnum;
import com.intopays.sdk.core.models.Webhook;
import com.intopays.sdk.infra.config.Environment;
import com.intopays.sdk.infra.http.request.WebhookCreateData;

import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WebhookRemote {
    private final String baseUrl;
    private final String token;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public WebhookRemote(IntopaysConstructor config) {
        this.token = config.getToken();
        this.baseUrl = Environment.get(EnvironmentTypeEnum.fromValue(config.getMode())).getHost();
    }

    public Webhook create(WebhookCreateData data) throws IOException {
        HttpPost request = new HttpPost(this.baseUrl + "/v1/webhooks");

        String json = objectMapper.writeValueAsString(data);
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);

        request.setHeader("Authorization", this.token);
        request.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());

            if (statusCode < 200 || statusCode >= 300) {
                throw new IOException("Failed to create webhook. Status: " + statusCode + ". Response: " + responseBody);
            }

            return objectMapper.readValue(responseBody, Webhook.class);
        }
        
    }

    public List<Webhook> search(Map<String, String> queryParams) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(this.baseUrl + "/v1/webhooks");

        if (queryParams != null && !queryParams.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                urlBuilder.append(entry.getKey())
                          .append("=")
                          .append(entry.getValue())
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
                throw new IOException("Failed to fetch webhooks. Status: " + statusCode + ". Response: " + responseBody);
            }

            return objectMapper.readValue(responseBody,
                   objectMapper.getTypeFactory().constructCollectionType(List.class, Webhook.class)
            );
        }
    }

    public Boolean delete(String id) throws IOException {
        HttpDelete request = new HttpDelete(this.baseUrl + "/v1/webhooks/" + id);
        request.setHeader("Authorization", this.token);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
//            String responseBody = EntityUtils.toString(response.getEntity());

            if (statusCode < 200 || statusCode >= 300) {
//                throw new IOException("Failed to delete webhook. Status: " + statusCode + ". Response: " + responseBody);
              throw new IOException("Failed to delete webhook. Status: " + statusCode);
            }

//            return objectMapper.readValue(responseBody, Webhook.class);
            return true;
        }
    }
}

