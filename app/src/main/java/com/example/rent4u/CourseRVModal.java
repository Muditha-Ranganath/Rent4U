package com.example.rent4u;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseRVModal implements Parcelable {
    private String vehicleName;
    private String vehicleDescription;
    private String vehiclePrice;
    private String bestSuitedFor;
    private String vehicleImg;
    private String vehicleLink;
    private String vehicleID;

    public CourseRVModal(){

    }

    public CourseRVModal(String vehicleName, String vehicleDescription, String vehiclePrice, String bestSuitedFor, String vehicleImg, String vehicleLink, String vehicleID) {
        this.vehicleName = vehicleName;
        this.vehicleDescription = vehicleDescription;
        this.vehiclePrice = vehiclePrice;
        this.bestSuitedFor = bestSuitedFor;
        this.vehicleImg = vehicleImg;
        this.vehicleLink = vehicleLink;
        this.vehicleID = vehicleID;
    }

    protected CourseRVModal(Parcel in) {
        vehicleName = in.readString();
        vehicleDescription = in.readString();
        vehiclePrice = in.readString();
        bestSuitedFor = in.readString();
        vehicleImg = in.readString();
        vehicleLink = in.readString();
        vehicleID = in.readString();
    }

    public static final Creator<CourseRVModal> CREATOR = new Creator<CourseRVModal>() {
        @Override
        public CourseRVModal createFromParcel(Parcel in) {
            return new CourseRVModal(in);
        }

        @Override
        public CourseRVModal[] newArray(int size) {
            return new CourseRVModal[size];
        }
    };

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public String getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(String vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public String getBestSuitedFor() {
        return bestSuitedFor;
    }

    public void setBestSuitedFor(String bestSuitedFor) {
        this.bestSuitedFor = bestSuitedFor;
    }

    public String getVehicleImg() {
        return vehicleImg;
    }

    public void setVehicleImg(String vehicleImg) {
        this.vehicleImg = vehicleImg;
    }

    public String getVehicleLink() {
        return vehicleLink;
    }

    public void setVehicleLink(String vehicleLink) {
        this.vehicleLink = vehicleLink;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(vehicleName);
        dest.writeString(vehicleDescription);
        dest.writeString(vehiclePrice);
        dest.writeString(bestSuitedFor);
        dest.writeString(vehicleImg);
        dest.writeString(vehicleLink);
        dest.writeString(vehicleID);
    }
}
