//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils.JMessage;

import cn.jiguang.common.connection.HttpProxy;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jiguang.common.utils.Preconditions;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserClient extends BaseClient {
    private static final Logger LOG = LoggerFactory.getLogger(UserClient.class);
    private String userPath;

    public UserClient(String appkey, String masterSecret) {
        this(appkey, masterSecret, (HttpProxy)null, JMessageConfig.getInstance());
    }

    public UserClient(String appkey, String masterSecret, HttpProxy proxy, JMessageConfig config) {
        super(appkey, masterSecret, proxy, config);
        this.userPath = (String)config.get("im.user.path");
    }

    public ResponseWrapper registerUsers(RegisterPayload payload) throws APIConnectionException, APIRequestException {
        Preconditions.checkArgument(payload != null, "有效载荷不应该为空");
        return this._httpClient.sendPost(this._baseUrl + this.userPath, payload.toString());
    }

    public UserInfoResult getUserInfo(String username) throws APIConnectionException, APIRequestException {
        StringUtils.checkUsername(username);
        ResponseWrapper response = this._httpClient.sendGet(this._baseUrl + this.userPath + "/" + username);
        return (UserInfoResult)UserInfoResult.fromResponse(response, UserInfoResult.class);
    }

    public ResponseWrapper updateUserInfo(String username, UserPayload payload) throws APIConnectionException, APIRequestException {
        StringUtils.checkUsername(username);
        Preconditions.checkArgument(payload != null, "有效载荷不应该为空");
        return this._httpClient.sendPut(this._baseUrl + this.userPath + "/" + username, payload.toString());
    }

    public UserStateResult getUserState(String username) throws APIConnectionException, APIRequestException {
        StringUtils.checkUsername(username);
        ResponseWrapper response = this._httpClient.sendGet(this._baseUrl + this.userPath + "/" + username + "/userstat");
        return (UserStateResult)UserStateResult.fromResponse(response, UserStateResult.class);
    }

    public ResponseWrapper updatePassword(String username, String password) throws APIConnectionException, APIRequestException {
        StringUtils.checkUsername(username);
        StringUtils.checkPassword(password);
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("new_password", password);
        return this._httpClient.sendPut(this._baseUrl + this.userPath + "/" + username + "/password", jsonObj.toString());
    }

    public ResponseWrapper deleteUser(String username) throws APIConnectionException, APIRequestException {
        StringUtils.checkUsername(username);
        return this._httpClient.sendDelete(this._baseUrl + this.userPath + "/" + username);
    }

    public UserInfoResult[] getBlackList(String username) throws APIConnectionException, APIRequestException {
        StringUtils.checkUsername(username);
        ResponseWrapper response = this._httpClient.sendGet(this._baseUrl + this.userPath + "/" + username + "/blacklist");
        return (UserInfoResult[])this._gson.fromJson(response.responseContent, UserInfoResult[].class);
    }

    public ResponseWrapper addBlackList(String username, String... users) throws APIConnectionException, APIRequestException {
        StringUtils.checkUsername(username);
        Preconditions.checkArgument(users != null && users.length > 0, "黑名单不应该是空");
        JsonArray array = new JsonArray();
        String[] var7 = users;
        int var6 = users.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            String user = var7[var5];
            array.add(new JsonPrimitive(user));
        }

        return this._httpClient.sendPut(this._baseUrl + this.userPath + "/" + username + "/blacklist", array.toString());
    }

    public ResponseWrapper removeBlackList(String username, String... users) throws APIConnectionException, APIRequestException {
        StringUtils.checkUsername(username);
        Preconditions.checkArgument(users != null && users.length > 0, "黑名单不应该是空");
        JsonArray array = new JsonArray();
        String[] var7 = users;
        int var6 = users.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            String user = var7[var5];
            array.add(new JsonPrimitive(user));
        }

        return this._httpClient.sendDelete(this._baseUrl + this.userPath + "/" + username + "/blacklist", array.toString());
    }
}
