package com.zynergi.dynamiq.recipebinder.Profile;

public class Profile {
    // Profile name
    private String name;
    private String description;


    public Profile() {
        this.name = "";
        this.description = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
