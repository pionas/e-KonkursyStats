package info.e_konkursy.stats.Model.POJO;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Adrian Pionka on 27 marzec 2017
 * adrian@pionka.com
 */
public class ContactMessage {
    @SerializedName("login")
    private String name;
    @SerializedName("mail")
    private String mail;
    @SerializedName("content")
    private String content;

    public ContactMessage() {

    }

    public ContactMessage(String name, String mail, String content) {
        this.name = name;
        this.mail = mail;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
