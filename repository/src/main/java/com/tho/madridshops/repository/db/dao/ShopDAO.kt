package com.tho.madridshops.repository.db.dao

import android.database.Cursor
import com.tho.madridshops.repository.db.model.ShopEntity

class ShopDAO: DAOPersistable<ShopEntity> {
    override fun query(id: Int): ShopEntity {
        TODO("not implemented")
    }

    override fun query(): List<ShopEntity> {
        TODO("not implemented")
    }

    override fun queryCursor(): Cursor {
        TODO("not implemented")
    }

    override fun insert() {
        TODO("not implemented")
    }

    override fun update() {
        TODO("not implemented")
    }

    override fun delete() {
        TODO("not implemented")
    }

    override fun deleteAll() {
        TODO("not implemented")
    }

}