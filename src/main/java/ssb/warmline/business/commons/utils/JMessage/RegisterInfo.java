//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils.JMessage;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class RegisterInfo implements IModel {
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    private static Gson gson = new Gson();
    private String username;
    private String password;

    private RegisterInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static RegisterInfo.Builder newBuilder() {
        return new RegisterInfo.Builder();
    }

    public JsonElement toJSON() {
        JsonObject json = new JsonObject();
        if(this.username != null) {
            json.addProperty("username", this.username);
        }

        if(this.password != null) {
            json.addProperty("password", this.password);
        }

        return json;
    }

    public String toString() {
        return gson.toJson(this.toJSON());
    }

    public static class Builder {
        private String username;
        private String password;

        public Builder() {
        }

        public RegisterInfo.Builder setUsername(String username) {
            this.username = username.trim();
            return this;
        }

        public RegisterInfo.Builder setPassword(String password) {
            this.password = password.trim();
            return this;
        }

        public RegisterInfo build() {
            StringUtils.checkUsername(this.username);
            StringUtils.checkPassword(this.password);
            return new RegisterInfo(this.username, this.password);
        }
    }
}
