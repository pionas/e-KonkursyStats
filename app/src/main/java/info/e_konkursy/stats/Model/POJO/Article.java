package info.e_konkursy.stats.Model.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Adrian Pionka on 2017-03-27.
 */

public class Article {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("old_cid")
    @Expose
    private String oldCid;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("short_desc")
    @Expose
    private String shortDesc;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("url")
    @Expose
    private List<String> url = null;
    @SerializedName("terms")
    @Expose
    private String terms;
    @SerializedName("prices")
    @Expose
    private String prices;
    @SerializedName("prices_cid")
    @Expose
    private List<PricesCid> pricesCid = null;
    @SerializedName("date_add")
    @Expose
    private String dateAdd;
    @SerializedName("date_edit")
    @Expose
    private String dateEdit;
    @SerializedName("date_start")
    @Expose
    private String dateStart;
    @SerializedName("date_end")
    @Expose
    private String dateEnd;
    @SerializedName("send_by")
    @Expose
    private String sendBy;
    @SerializedName("added_by")
    @Expose
    private String addedBy;
    @SerializedName("views")
    @Expose
    private String views;
    @SerializedName("promo")
    @Expose
    private String promo;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("rating_vote")
    @Expose
    private String ratingVote;
    @SerializedName("rating_count")
    @Expose
    private String ratingCount;
    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("st")
    @Expose
    private String st;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_alias")
    @Expose
    private String categoryAlias;
    @SerializedName("toend")
    @Expose
    private String toend;
    @SerializedName("brkon")
    @Expose
    private Integer brkon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getOldCid() {
        return oldCid;
    }

    public void setOldCid(String oldCid) {
        this.oldCid = oldCid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public List<PricesCid> getPricesCid() {
        return pricesCid;
    }

    public void setPricesCid(List<PricesCid> pricesCid) {
        this.pricesCid = pricesCid;
    }

    public String getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(String dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(String dateEdit) {
        this.dateEdit = dateEdit;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getSendBy() {
        return sendBy;
    }

    public void setSendBy(String sendBy) {
        this.sendBy = sendBy;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getRatingVote() {
        return ratingVote;
    }

    public void setRatingVote(String ratingVote) {
        this.ratingVote = ratingVote;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryAlias() {
        return categoryAlias;
    }

    public void setCategoryAlias(String categoryAlias) {
        this.categoryAlias = categoryAlias;
    }

    public String getToend() {
        return toend;
    }

    public void setToend(String toend) {
        this.toend = toend;
    }

    public Integer getBrkon() {
        return brkon;
    }

    public void setBrkon(Integer brkon) {
        this.brkon = brkon;
    }

}