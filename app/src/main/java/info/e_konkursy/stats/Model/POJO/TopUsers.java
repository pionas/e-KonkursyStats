package info.e_konkursy.stats.Model.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class TopUsers {

    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("peopleInfo")
    @Expose
    private List<User> peopleInfo = null;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public List<User> getPeopleInfo() {
        return peopleInfo;
    }

    public void setPeopleInfo(List<User> peopleInfo) {
        this.peopleInfo = peopleInfo;
    }

}