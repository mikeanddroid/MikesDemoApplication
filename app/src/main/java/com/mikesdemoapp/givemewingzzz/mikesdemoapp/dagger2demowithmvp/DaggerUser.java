package com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp;

/**
 * Project - MikesDemoApp
 * In Package - com.mikesdemoapp.givemewingzzz.mikesdemoapp.dagger2demowithmvp
 * Created by GiveMeWingzzz on 11/3/2017.
 */

public class DaggerUser {

    private Long id;
    private String name;
    private String address;
    private String createdAt;
    private String updatedAt;

    public DaggerUser() {
    }

    public DaggerUser(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "DaggerUser{\n" +
                "id=" + id + "\n" +
                ", name='" + name + '\'' + "\n" +
                ", address='" + address + '\'' + "\n" +
                ", createdAt='" + createdAt + '\'' + "\n" +
                ", updatedAt='" + updatedAt + '\'' + "\n" +
                '}';
    }

}
