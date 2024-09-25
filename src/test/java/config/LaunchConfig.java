package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/${env}.properties",
        "classpath:properties/local.properties"
})
public interface LaunchConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("baseBrowserSize")
    String getBaseBrowserSize();

    @Key("baseBrowser")
    String getBaseBrowser();

    @Key("baseBrowserVersion")
    String getBaseBrowserVersion();

    @Key("pageLoadStrategy")
    String pageLoadStrategy();
}