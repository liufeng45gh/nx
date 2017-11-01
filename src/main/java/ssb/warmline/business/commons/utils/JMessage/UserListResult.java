//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ssb.warmline.business.commons.utils.JMessage;

import cn.jiguang.common.resp.BaseResult;
import cn.jiguang.common.resp.ResponseWrapper;
import com.google.gson.annotations.Expose;

public class UserListResult extends BaseResult {
    @Expose
    Integer total;
    @Expose
    Integer start;
    @Expose
    Integer count;
    @Expose
    UserInfoResult[] users;

    public UserListResult() {
    }

    public static UserListResult fromResponse(ResponseWrapper responseWrapper) {
        UserListResult result = new UserListResult();
        if(responseWrapper.isServerResponse()) {
            result.users = (UserInfoResult[])_gson.fromJson(responseWrapper.responseContent, UserInfoResult[].class);
        }

        result.setResponseWrapper(responseWrapper);
        return result;
    }

    public Integer getTotal() {
        return this.total;
    }

    public Integer getStart() {
        return this.start;
    }

    public Integer getCount() {
        return this.count;
    }

    public UserInfoResult[] getUsers() {
        return this.users;
    }
}
