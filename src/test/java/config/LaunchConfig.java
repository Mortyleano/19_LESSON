package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/${env}.properties",
        "classpath:properties/local.properties"
})
public interface LaunchConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("browserSize")
    String getBrowserSize();

    @Key("browser")
    String getBrowser();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("pageLoadStrategy")
    String pageLoadStrategy();
}