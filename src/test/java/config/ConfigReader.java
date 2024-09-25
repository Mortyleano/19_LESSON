package config;

import org.aeonbits.owner.ConfigFactory;

public enum ConfigReader {

    Instance();

    private static final LaunchConfig webConfig = ConfigFactory.create(LaunchConfig.class, System.getProperties());

    public LaunchConfig read() {
        return webConfig;
    }
}