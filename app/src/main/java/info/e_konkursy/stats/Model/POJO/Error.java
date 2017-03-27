package info.e_konkursy.stats.Model.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class Error {

    @SerializedName("nr")
    @Expose
    private Integer nr;
    @SerializedName("error")
    @Expose
    private String error;

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}