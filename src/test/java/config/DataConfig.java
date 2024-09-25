package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:properties/data.properties"
})
public interface DataConfig extends Config {

    @Key("defaultIsbnId")
    String getDefaultIsbnId();

    @Key("userName")
    String getUserName();

    @Key("authorBook")
    String getAuthorBook();
}