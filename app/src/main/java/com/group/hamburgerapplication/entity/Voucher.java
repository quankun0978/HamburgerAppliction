package com.group.hamburgerapplication.entity;

public class Voucher {
    private String codeId,type,expiry_date,description,title;
    private double discount;
   private boolean isPercent;

    public Voucher() {
    }

    public Voucher(String codeId, String type, String expiry_date, String description, double discount, boolean isPercent ,String title) {
        this.codeId = codeId;
        this.title=title;
        this.type = type;
        this.expiry_date = expiry_date;
        this.description = description;
        this.discount = discount;
        this.isPercent = isPercent;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isPercent() {
        return isPercent;
    }

    public void setPercent(boolean percent) {
        isPercent = percent;
    }
}
