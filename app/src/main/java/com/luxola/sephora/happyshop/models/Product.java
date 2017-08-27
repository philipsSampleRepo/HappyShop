package com.luxola.sephora.happyshop.models;

/**
 * Created by Pradeep on 8/21/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("price")
    @Expose
    public Integer price;
    @SerializedName("img_url")
    @Expose
    public String imgUrl;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("under_sale")
    @Expose
    public Boolean underSale;

    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        category = in.readString();
        price = in.readInt();
        imgUrl = in.readString();
        description = in.readString();
        underSale = in.readInt() == 1 ? true : false;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeInt(price);
        dest.writeString(imgUrl);
        dest.writeString(description);
        dest.writeInt(underSale ? 1 : 0);
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", category='" + category + '\'' +
            ", price=" + price +
            ", imgUrl='" + imgUrl + '\'' +
            ", description='" + description + '\'' +
            ", underSale=" + underSale +
            '}';
    }
}