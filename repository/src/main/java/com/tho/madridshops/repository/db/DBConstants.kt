package com.tho.madridshops.repository.db

internal object DBConstants {

    // SHOP
    val TABLE_SHOP = "SHOP"

    // Table field constants
    val KEY_SHOP_DATABASE_ID = "_id"
    val KEY_SHOP_ID_JSON = "ID_JSON"
    val KEY_SHOP_NAME = "NAME"
    val KEY_SHOP_IMAGE_URL = "IMAGE_URL"
    val KEY_SHOP_LOGO_IMAGE_URL = "LOGO_IMAGE_URL"

    val KEY_SHOP_ADDRESS = "ADDRESS"
    val KEY_SHOP_URL = "URL"

    val KEY_SHOP_LATITUDE = "LATITUDE"
    val KEY_SHOP_LONGITUDE = "LONGITUDE"

    val KEY_SHOP_DESCRIPTION_EN = "DESCRIPTION_EN"
    val KEY_SHOP_DESCRIPTION_ES = "DESCRIPTION_ES"
    val KEY_SHOP_OPENING_HOURS_EN = "OPENING_HOURS_EN"
    val KEY_SHOP_OPENING_HOURS_ES = "OPENING_HOURS_ES"

    val ALL_SHOP_COLUMNS = arrayOf(
            KEY_SHOP_DATABASE_ID,
            KEY_SHOP_ID_JSON,
            KEY_SHOP_NAME,
            KEY_SHOP_IMAGE_URL,
            KEY_SHOP_LOGO_IMAGE_URL,
            KEY_SHOP_ADDRESS,
            KEY_SHOP_URL,
            KEY_SHOP_LATITUDE,
            KEY_SHOP_LONGITUDE,
            KEY_SHOP_DESCRIPTION_EN,
            KEY_SHOP_DESCRIPTION_ES,
            KEY_SHOP_OPENING_HOURS_EN,
            KEY_SHOP_OPENING_HOURS_ES
    )

    val SQL_SCRIPT_CREATE_SHOP_TABLE = (
            "create table " + TABLE_SHOP
                    + "( "
                    + KEY_SHOP_DATABASE_ID + " integer primary key autoincrement, "
                    + KEY_SHOP_ID_JSON + " integer, "
                    + KEY_SHOP_NAME + " text not null,"
                    + KEY_SHOP_IMAGE_URL + " text, "
                    + KEY_SHOP_LOGO_IMAGE_URL + " text, "
                    + KEY_SHOP_ADDRESS + " text,"
                    + KEY_SHOP_URL + " text,"
                    + KEY_SHOP_LATITUDE + " real,"
                    + KEY_SHOP_LONGITUDE + " real, "
                    + KEY_SHOP_DESCRIPTION_EN + " text, "
                    + KEY_SHOP_DESCRIPTION_ES + " text, "
                    + KEY_SHOP_OPENING_HOURS_EN + " text, "
                    + KEY_SHOP_OPENING_HOURS_ES + " text "
                    + ");")

    // ACTIVITY
    val TABLE_ACTIVITY = "ACTIVITY"

    // Table field constants
    val KEY_ACTIVITY_DATABASE_ID = "_id"
    val KEY_ACTIVITY_ID_JSON = "ID_JSON"
    val KEY_ACTIVITY_NAME = "NAME"
    val KEY_ACTIVITY_IMAGE_URL = "IMAGE_URL"
    val KEY_ACTIVITY_LOGO_IMAGE_URL = "LOGO_IMAGE_URL"

    val KEY_ACTIVITY_ADDRESS = "ADDRESS"
    val KEY_ACTIVITY_URL = "URL"

    val KEY_ACTIVITY_LATITUDE = "LATITUDE"
    val KEY_ACTIVITY_LONGITUDE = "LONGITUDE"

    val KEY_ACTIVITY_DESCRIPTION_EN = "DESCRIPTION_EN"
    val KEY_ACTIVITY_DESCRIPTION_ES = "DESCRIPTION_ES"
    val KEY_ACTIVITY_OPENING_HOURS_EN = "OPENING_HOURS_EN"
    val KEY_ACTIVITY_OPENING_HOURS_ES = "OPENING_HOURS_ES"

    val ALL_ACTIVITY_COLUMNS = arrayOf(
            KEY_ACTIVITY_DATABASE_ID,
            KEY_ACTIVITY_ID_JSON,
            KEY_ACTIVITY_NAME,
            KEY_ACTIVITY_IMAGE_URL,
            KEY_ACTIVITY_LOGO_IMAGE_URL,
            KEY_ACTIVITY_ADDRESS,
            KEY_ACTIVITY_URL,
            KEY_ACTIVITY_LATITUDE,
            KEY_ACTIVITY_LONGITUDE,
            KEY_ACTIVITY_DESCRIPTION_EN,
            KEY_ACTIVITY_DESCRIPTION_ES,
            KEY_ACTIVITY_OPENING_HOURS_EN,
            KEY_ACTIVITY_OPENING_HOURS_ES
    )

    val SQL_SCRIPT_CREATE_ACTIVITY_TABLE = (
            "create table " + TABLE_ACTIVITY
                    + "( "
                    + KEY_ACTIVITY_DATABASE_ID + " integer primary key autoincrement, "
                    + KEY_ACTIVITY_ID_JSON + " integer, "
                    + KEY_ACTIVITY_NAME + " text not null,"
                    + KEY_ACTIVITY_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_LOGO_IMAGE_URL + " text, "
                    + KEY_ACTIVITY_ADDRESS + " text,"
                    + KEY_ACTIVITY_URL + " text,"
                    + KEY_ACTIVITY_LATITUDE + " real,"
                    + KEY_ACTIVITY_LONGITUDE + " real, "
                    + KEY_ACTIVITY_DESCRIPTION_EN + " text, "
                    + KEY_ACTIVITY_DESCRIPTION_ES + " text, "
                    + KEY_ACTIVITY_OPENING_HOURS_EN + " text, "
                    + KEY_ACTIVITY_OPENING_HOURS_ES + " text "
                    + ");")

    val DROP_DATABASE_SCRIPTS = ""

    val CREATE_DATABASE_SCRIPTS = arrayOf(
            SQL_SCRIPT_CREATE_SHOP_TABLE,
            SQL_SCRIPT_CREATE_ACTIVITY_TABLE
    )

}
