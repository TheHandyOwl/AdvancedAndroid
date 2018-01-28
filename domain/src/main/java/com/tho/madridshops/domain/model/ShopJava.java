package com.tho.madridshops.domain.model;

import android.support.annotation.NonNull;

public class ShopJava {
    public int id;
    private String name;
    private String address;

    public ShopJava(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final @NonNull String name) {
        //name = "pepe", // Shot in the foot
        assert(name != null);
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Don't try this at home!!!
    {
        ShopJava shop1 = new ShopJava("Shop1");
        shop1.setAddress("address");
        shop1.getAddress();
    }

}

/*
class HijaDeShop extends ShopJava {
    public HijaDeShop(String name) {
        super(name);
    }

    @Override
    public void setAddress(String address) {
        // super.setAddress(address);
        // algo totalmente distinto
    }
}
*/

// SOLID
