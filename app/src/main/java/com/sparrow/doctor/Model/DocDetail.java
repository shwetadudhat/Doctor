
package com.sparrow.doctor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DocDetail {

    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("facility_name")
    @Expose
    private String facility_name;
    @SerializedName("doctor_id")
    @Expose
    private String doctor_id;

    @SerializedName("full_name_en")
    @Expose
    private String full_name_en;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("specialty")
    @Expose
    private String specialty;
    @SerializedName("coins")
    @Expose
    private String coins;
    @SerializedName("fk_facility_id")
    @Expose
    private String fk_facility_id;
    @SerializedName("fk_branch_id")
    @Expose
    private String fk_branch_id;
    @SerializedName("fk_clinic_id")
    @Expose
    private String fk_clinic_id;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("msg")
    @Expose
    private String msg;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFacility_name() {
        return facility_name;
    }

    public void setFacility_name(String facility_name) {
        this.facility_name = facility_name;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getFull_name_en() {
        return full_name_en;
    }

    public void setFull_name_en(String full_name_en) {
        this.full_name_en = full_name_en;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getFk_facility_id() {
        return fk_facility_id;
    }

    public void setFk_facility_id(String fk_facility_id) {
        this.fk_facility_id = fk_facility_id;
    }

    public String getFk_branch_id() {
        return fk_branch_id;
    }

    public void setFk_branch_id(String fk_branch_id) {
        this.fk_branch_id = fk_branch_id;
    }

    public String getFk_clinic_id() {
        return fk_clinic_id;
    }

    public void setFk_clinic_id(String fk_clinic_id) {
        this.fk_clinic_id = fk_clinic_id;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "DocDetail{" +
                "user_id='" + user_id + '\'' +
                ", user='" + user + '\'' +
                ", facility_name='" + facility_name + '\'' +
                ", doctor_id='" + doctor_id + '\'' +
                ", full_name_en='" + full_name_en + '\'' +
                ", photo='" + photo + '\'' +
                ", specialty='" + specialty + '\'' +
                ", coins='" + coins + '\'' +
                ", fk_facility_id='" + fk_facility_id + '\'' +
                ", fk_branch_id='" + fk_branch_id + '\'' +
                ", fk_clinic_id='" + fk_clinic_id + '\'' +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}
