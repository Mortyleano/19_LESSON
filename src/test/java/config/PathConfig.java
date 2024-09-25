package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/path.properties"
})
public interface PathConfig extends Config {

    @Key("apiPathLogin")
    String apiPathLogin();

    @Key("apiPathBook")
    String apiPathBook();
}