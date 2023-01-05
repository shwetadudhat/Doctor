
package com.sparrow.doctor.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Appointment {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("patient_name")
    @Expose
    private String productName;
    @SerializedName("procedure")
    @Expose
    private String procedure_name;
    @SerializedName("start_time")
    @Expose
    private String start_time;

    @SerializedName("end_time")
    @Expose
    private String end_time;
    @SerializedName("date")
    @Expose
    private String date;

    public Appointment(String id, String productName, String procedure_name, String start_time, String end_time) {
        this.id = id;
        this.productName = productName;
        this.procedure_name = procedure_name;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProcedure_name() {
        return procedure_name;
    }

    public void setProcedure_name(String procedure_name) {
        this.procedure_name = procedure_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }


    @Override
    public String toString() {
        return "Appointment{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", procedure_name='" + procedure_name + '\'' +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
