package com.intopays.sdk.infra.config;

import java.util.HashMap;
import java.util.Map;

import com.intopays.sdk.core.enums.EnvironmentTypeEnum;

public class Environment {
    private static final Map<EnvironmentTypeEnum, EnvironmentData> environments = new HashMap<>();

    static {
        environments.put(EnvironmentTypeEnum.PRODUCTION, new EnvironmentData("https://app.intopays.com", null));
        environments.put(EnvironmentTypeEnum.DEVELOPMENT, new EnvironmentData("http://localhost:8090", null));
        environments.put(EnvironmentTypeEnum.TEST, new EnvironmentData(
            "http://localhost:8090",
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NiwiZW1haWwiOiJzZGtAaW50b3BheXMuY29tIiwiaWF0IjoxNzEzMTMzMDMwfQ.0uOXAMSq09aasfUkDCzuvaKVUBBAZf0mU1uBz-UDXkQ"
        ));
    }

    public static EnvironmentData get(EnvironmentTypeEnum type) {
        return environments.get(type);
    }
}
