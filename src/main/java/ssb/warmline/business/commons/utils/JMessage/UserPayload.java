//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils.JMessage;

import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.utils.Preconditions;
import cn.jiguang.common.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class UserPayload implements IModel {
    public static final String NICKNAME = "nickname";
    public static final String BIRTHDAY = "birthday";
    public static final String SIGNATURE = "signature";
    public static final String GENDER = "gender";
    public static final String REGION = "region";
    public static final String ADDRESS = "address";
    public static final String AVATAR = "avatar";
    private static Gson _gson = new Gson();
    private String nickname;
    private String birthday;
    private String signature;
    private int gender;
    private String region;
    private String address;
    private String avatar;

    private UserPayload(String nickname, String birthday, String signature, int gender, String region, String address, String avatar) {
        this.nickname = nickname;
        this.birthday = birthday;
        this.signature = signature;
        this.gender = gender;
        this.region = region;
        this.address = address;
        this.avatar = avatar;
    }

    public static UserPayload.Builder newBuilder() {
        return new UserPayload.Builder();
    }

    public JsonElement toJSON() {
        JsonObject json = new JsonObject();
        if(this.nickname != null) {
            json.addProperty("nickname", this.nickname);
        }

        if(this.birthday != null) {
            json.addProperty("birthday", this.birthday);
        }

        if(-1 != this.gender) {
            json.addProperty("gender", Integer.valueOf(this.gender));
        }

        if(this.signature != null) {
            json.addProperty("signature", this.signature);
        }

        if(this.region != null) {
            json.addProperty("region", this.region);
        }

        if(this.address != null) {
            json.addProperty("address", this.address);
        }

        if(this.avatar != null) {
            json.addProperty("avatar", this.avatar);
        }

        return json;
    }

    public String toString() {
        return _gson.toJson(this.toJSON());
    }

    public static class Builder {
        private String nickname;
        private String birthday;
        private String signature;
        private int gender = -1;
        private String region;
        private String address;
        private String avatar;

        public Builder() {
        }

        public UserPayload.Builder setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserPayload.Builder setBirthday(String birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserPayload.Builder setSignature(String signature) {
            this.signature = signature;
            return this;
        }

        public UserPayload.Builder setGender(int gender) {
            this.gender = gender;
            return this;
        }

        public UserPayload.Builder setRegion(String region) {
            this.region = region;
            return this;
        }

        public UserPayload.Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public UserPayload.Builder setAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public UserPayload build() {
            if(this.nickname != null) {
                Preconditions.checkArgument(this.nickname.getBytes().length <= 64, "昵称的长度必须小于64字节。");
                Preconditions.checkArgument(!StringUtils.isLineBroken(this.nickname), "昵称不能包含换行字符。");
            }

            if(this.birthday != null) {
                Preconditions.checkArgument(ServiceHelper.isValidBirthday(this.birthday), "无效的生日。");
            }

            if(this.signature != null) {
                Preconditions.checkArgument(this.signature.getBytes().length <= 250, "签名的长度不能超过250个字节。");
            }

            Preconditions.checkArgument(this.gender >= -1 && this.gender <= 2, "无效的性别。0为未知,1男2女。");
            if(this.region != null) {
                Preconditions.checkArgument(this.region.getBytes().length <= 250, "区域的长度不能超过250个字节。");
            }

            if(this.address != null) {
                Preconditions.checkArgument(this.address.getBytes().length <= 250, "地址的长度不能超过250个字节。");
            }

            return new UserPayload(this.nickname, this.birthday, this.signature, this.gender, this.region, this.address, this.avatar);
        }
    }
}
