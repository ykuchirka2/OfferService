package edu.ykuchirka.Entity;


import com.opencsv.bean.CsvBindByName;

/**
 * Created by Yura on 28.03.2017.
 */
public class Offer {

    @CsvBindByName(column = "TM_activity_id")
    private int tmActivityId;

    @CsvBindByName(column = "activity_group_id")
    private int activityGroupId;

    @CsvBindByName(column = "campaign_type_cd")
    private int campaignTypeCd;

    @CsvBindByName(column = "store_id")
    private int storeId;

    @CsvBindByName(column = "offer_id")
    private int offerId;

    @CsvBindByName(column = "content_id")
    private int contentId;

    @CsvBindByName(column = "offer_desc")
    private String offerDesc;

    public Offer() {
    }

    public Offer(int tmActivityId, int activityGroupId, int campaignTypeCd, int storeId, int offerId, int contentId, String offerDesc) {
        this.tmActivityId = tmActivityId;
        this.activityGroupId = activityGroupId;
        this.campaignTypeCd = campaignTypeCd;
        this.storeId = storeId;
        this.offerId = offerId;
        this.contentId = contentId;
        this.offerDesc = offerDesc;
    }

    public int getTmActivityId() {
        return tmActivityId;
    }

    public void setTmActivityId(int tmActivityId) {
        this.tmActivityId = tmActivityId;
    }

    public int getActivityGroupId() {
        return activityGroupId;
    }

    public void setActivityGroupId(int activityGroupId) {
        this.activityGroupId = activityGroupId;
    }

    public int getCampaignTypeCd() {
        return campaignTypeCd;
    }

    public void setCampaignTypeCd(int campaignTypeCd) {
        this.campaignTypeCd = campaignTypeCd;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public String getOfferDesc() {
        return offerDesc;
    }

    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "tmActivityId=" + tmActivityId +
                ", activityGroupId=" + activityGroupId +
                ", campaignTypeCd=" + campaignTypeCd +
                ", storeId=" + storeId +
                ", offerId=" + offerId +
                ", contentId=" + contentId +
                ", offerDesc='" + offerDesc + '\'' +
                '}';
    }
}
