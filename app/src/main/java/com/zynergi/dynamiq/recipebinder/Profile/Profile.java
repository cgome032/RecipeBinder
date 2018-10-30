package com.zynergi.dynamiq.recipebinder.Profile;

import java.util.ArrayList;

public class Profile {
    // Profile name
    private String name;
    private String description;
    private ArrayList<String> postedRecipes;

    public Profile() {
        this.name = "";
        this.description = "";
    }
    public Profile(String fname, String fdescription)
    {
        this.name = fname;
        this.description = fdescription;
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

    public ArrayList<String> getPostedRecipes() {
        return postedRecipes;
    }

}
