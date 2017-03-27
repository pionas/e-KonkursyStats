package info.e_konkursy.stats.Model.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Adrian Pionka on 27 marzec 2017
 * adrian@pionka.com
 */
public class Contact {

    @SerializedName("error")
    @Expose
    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

}
