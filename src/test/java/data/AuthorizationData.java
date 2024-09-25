package data;

import config.AuthConfig;
import lombok.Data;
import models.AuthorizationModel;
import org.aeonbits.owner.ConfigFactory;

@Data
public class AuthorizationData {

    private String userName;
    private String password;

    public AuthorizationData() {
        AuthorizationModel authModel = getAuthModel();
        this.userName = authModel.getUserName();
        this.password = authModel.getPassword();
    }

    private AuthorizationModel getAuthModel() {
        AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
        return new AuthorizationModel(config.userName(), config.password());
    }
}