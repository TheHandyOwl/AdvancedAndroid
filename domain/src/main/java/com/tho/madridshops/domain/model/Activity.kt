package com.tho.madridshops.domain.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Activity: represents one Activity
 */

data class Activity(val id: Int, val name: String,
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

                    // init se ejecuta junto con el constructor
                    init{
                        Activities(ArrayList<Activity>())
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

                    companion object CREATOR : Parcelable.Creator<Activity> {
                        override fun createFromParcel(parcel: Parcel): Activity {
                            return Activity(parcel)
                        }

                        override fun newArray(size: Int): Array<Activity?> {
                            return arrayOfNulls(size)
                        }
                    }
}

class Activities(val activities: MutableList<Activity>): Aggregate<Activity> {
    override fun count(): Int = activities.size

    override fun all(): List<Activity> = activities

    override fun get(position: Int): Activity {
        return activities.get(position)
    }

    override fun add(element: Activity) {
        activities.add(element)
    }

    override fun delete(position: Int) {
        activities.removeAt(position)
    }

    override fun delete(element: Activity) {
        activities.remove(element)
    }

}
