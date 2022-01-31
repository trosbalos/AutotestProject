package config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import dictionaries.IPathEnum;
import dictionaries.ServiceEnum;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ConfigQA {
    private static ConfigQA instance;
    @Getter
    private final Map<ServiceEnum, ConnectionProperties> baseConnectionMap = new HashMap<>();
    @Getter
    private final Map<IPathEnum, String> serviceDataMap = new HashMap<>();

    private ConfigQA() {
        Config config = ConfigFactory.parseResources("config.conf");
        for (ServiceEnum value : ServiceEnum.values()) {
            Config conf = config.getConfig("service")
                    .getConfig(value.name().toLowerCase());
            readPaths(value, conf);
            readBaseProperties(value, conf.getConfig("base"));
        }
    }

    public static ConfigQA getInstance() {
        if (instance == null) {
            instance = new ConfigQA();
        }
        return instance;
    }

    private void readBaseProperties(ServiceEnum value, Config config) {
        ConnectionProperties connectionProperties = new ConnectionProperties();
        connectionProperties.setUrl(config.getString("url"));
        connectionProperties.setUser(config.getString("user"));
        connectionProperties.setPassword(config.getString("password"));
        connectionProperties.setDialect(config.getString("dialect"));
        connectionProperties.setDriver(config.getString("driver"));
        baseConnectionMap.put(value, connectionProperties);
    }

    private void readPaths(ServiceEnum value, Config config) {
        String port, host;
        host = config.getString("host");
        port = config.getString("port");
        Config pathsConf = config.getConfig("path");
        for (IPathEnum iPathEn : value.getPathEnumList()) {
            String path = pathsConf.getString(iPathEn.name().toLowerCase());
            serviceDataMap.put(iPathEn, generateFullPath(host, port, path));
        }
    }

    public String generateFullPath(String host, String port, String path) {
        return host + ":" + port + "/" + path;
    }
}