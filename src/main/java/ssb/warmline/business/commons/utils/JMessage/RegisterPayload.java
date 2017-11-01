//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils.JMessage;

import cn.jiguang.common.utils.Preconditions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class RegisterPayload implements IModel {
    private static Gson gson = new Gson();
    private JsonArray array;

    private RegisterPayload(JsonArray array) {
        this.array = array;
    }

    public static RegisterPayload.Builder newBuilder() {
        return new RegisterPayload.Builder();
    }

    public JsonElement toJSON() {
        return this.array;
    }

    public String toString() {
        return gson.toJson(this.toJSON());
    }

    public static class Builder {
        private JsonArray array = new JsonArray();

        public Builder() {
        }

        public RegisterPayload.Builder addUsers(RegisterInfo... users) {
            if(users == null) {
                return this;
            } else {
                RegisterInfo[] var5 = users;
                int var4 = users.length;

                for(int var3 = 0; var3 < var4; ++var3) {
                    RegisterInfo user = var5[var3];
                    this.array.add(user.toJSON());
                }

                return this;
            }
        }

        public RegisterPayload build() {
            Preconditions.checkArgument(this.array.size() != 0, "用户列表不能为空。");
            return new RegisterPayload(this.array);
        }
    }
}
