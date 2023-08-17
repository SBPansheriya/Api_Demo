
package com.kmsoft.api_demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Modal {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("superiorId")
    @Expose
    private String superiorId;

    public Modal(String id, String name, String superiorId) {
        this.id = id;
        this.name = name;
        this.superiorId = superiorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(String superiorId) {
        this.superiorId = superiorId;
    }

    @Override
    public String toString() {
        return "GetModal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", superiorId='" + superiorId + '\'' +
                '}';
    }
}
