package com.intopays.sdk.infra.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intopays.sdk.IntopaysConstructor;
import com.intopays.sdk.core.enums.EnvironmentTypeEnum;
import com.intopays.sdk.core.models.Boleto;
import com.intopays.sdk.infra.config.Environment;
import com.intopays.sdk.infra.http.response.PageResponse;

import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class BoletoRemote {
    private final String baseUrl;
    private final String token;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public BoletoRemote(IntopaysConstructor config) {
        this.token = config.getToken();
        this.baseUrl = Environment.get(config.getMode()).getHost();
    }

    public Boleto create(Boleto data) throws IOException {
        HttpPost request = new HttpPost(this.baseUrl + "/v1/boletos");

        String json = objectMapper.writeValueAsString(data);
        StringEntity entity = new StringEntity(json);
        request.setEntity(entity);

        request.setHeader("Authorization", this.token);
        request.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());

            if (statusCode < 200 || statusCode >= 300) {
                throw new IOException("Failed to create boleto. Status: " + statusCode + ". Response: " + responseBody);
            }

            return objectMapper.readValue(responseBody, Boleto.class);
        }
    }

    public PageResponse<Boleto> search(Map<String, String> queryParams) throws IOException {
        StringBuilder urlBuilder = new StringBuilder(this.baseUrl + "/v1/boletos");

        if (queryParams != null && !queryParams.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                urlBuilder.append(entry.getKey())
                          .append("=")
                          .append(entry.getValue())
                          .append("&");
            }
            urlBuilder.setLength(urlBuilder.length() - 1); // Remove trailing '&'
        }

        HttpGet request = new HttpGet(urlBuilder.toString());
        request.setHeader("Authorization", this.token);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());

            if (statusCode < 200 || statusCode >= 300) {
                throw new IOException("Failed to fetch boletos. Status: " + statusCode + ". Response: " + responseBody);
            }

            return objectMapper.readValue(
                responseBody,
                objectMapper.getTypeFactory().constructParametricType(PageResponse.class, Boleto.class)
            );
        }
    }

    public Boleto find(Object id) throws IOException {
        HttpGet request = new HttpGet(this.baseUrl + "/v1/boletos/" + id);
        request.setHeader("Authorization", this.token);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());

            if (statusCode < 200 || statusCode >= 300) {
                throw new IOException("Failed to find boleto. Status: " + statusCode + ". Response: " + responseBody);
            }

            return objectMapper.readValue(responseBody, Boleto.class);
        }
    }

    public Boleto cancel(String id) throws IOException {
        HttpPatch request = new HttpPatch(this.baseUrl + "/v1/boletos/" + id + "/cancel");
        request.setHeader("Authorization", this.token);
        request.setHeader("Content-Type", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());

            if (statusCode < 200 || statusCode >= 300) {
                throw new IOException("Failed to cancel boleto. Status: " + statusCode + ". Response: " + responseBody);
            }

//            return objectMapper.readValue(responseBody, Boleto.class);
            return objectMapper.readValue(responseBody, Boleto.class);
        }
    }
}
