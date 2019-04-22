package com.test.gambit.game.player.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Shubham on 22-04-2019.
 */

public class PlayerData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("height_feet")
    @Expose
    private String heightFeet;
    @SerializedName("height_inches")
    @Expose
    private String heightInches;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("position")
    @Expose
    private String position;
   /* @SerializedName("team")
    @Expose
    private Team team;*/

    @SerializedName("weight_pounds")
    @Expose
    private String weightPounds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(String heightFeet) {
        this.heightFeet = heightFeet;
    }

    public String getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(String heightInches) {
        this.heightInches = heightInches;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

  /*  public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }*/

    public String getWeightPounds() {
        return weightPounds;
    }

    public void setWeightPounds(String weightPounds) {
        this.weightPounds = weightPounds;
    }

    @Override
    public String toString() {
        return "PlayerData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", heightFeet='" + heightFeet + '\'' +
                ", heightInches='" + heightInches + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", weightPounds='" + weightPounds + '\'' +
                '}';
    }
}
