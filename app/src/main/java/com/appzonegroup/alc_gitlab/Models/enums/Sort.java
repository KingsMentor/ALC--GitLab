package com.appzonegroup.alc_gitlab.Models.enums;

/**
 * Created by zone2 on 3/10/17.
 */

public enum Sort {
    FOLLOWERS("followers"), REPO("repositories"), JOINED("joined");

    private String name;
    Sort(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
