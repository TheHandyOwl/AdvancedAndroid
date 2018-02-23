package com.tho.madridshops.domain.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Shop: represents one Shop
 */

data class Shop (
        val id: Int, val name: String,
        val image_url: String, val logo_image_url: String,
        val address: String, val url : String,
        val latitude: Float, val longitude: Float,
        val description_en: String, val description_es: String,
        val opening_hours_en: String, val opening_hours_es: String
    ) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readFloat(),
            parcel.readFloat(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    // init is a block of code executed with the constructor
    init {
        Shops(ArrayList<Shop>())
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(image_url)
        parcel.writeString(logo_image_url)
        parcel.writeString(address)
        parcel.writeString(url)
        parcel.writeFloat(latitude)
        parcel.writeFloat(longitude)
        parcel.writeString(description_en)
        parcel.writeString(description_es)
        parcel.writeString(opening_hours_en)
        parcel.writeString(opening_hours_es)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Shop> {
        override fun createFromParcel(parcel: Parcel): Shop {
            return Shop(parcel)
        }

        override fun newArray(size: Int): Array<Shop?> {
            return arrayOfNulls(size)
        }
    }
}

/**
 * Shops: represents several Shops
 */

class Shops(val shops: MutableList<Shop>): Aggregate<Shop> {
    override fun count(): Int = shops.size

    override fun all(): List<Shop> = shops

    override fun get(position: Int): Shop = shops.get(position)

    override fun add(element: Shop) {
        shops.add(element)
    }

    override fun delete(position: Int) {
        shops.removeAt(position)
    }

    override fun delete(element: Shop) {
        shops.remove(element)
    }

}