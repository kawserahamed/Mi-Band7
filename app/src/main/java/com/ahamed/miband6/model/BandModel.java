package com.ahamed.miband6.model;

public class BandModel {

    private String objectId;
    private String image;
    private String author;
    private String specification;
    private String language;
    private String timeformat;
    private String title;
    private String type;
    private String url;
    private String background;
    private String category;
    private String  createdAt;
    private String updatedAt;

    private boolean Steps;
    private boolean Calorie;
    private boolean Pulse;
    private boolean Time;
    private boolean Date;
    private boolean Weather;
    private boolean Battery;
    private boolean Alarm;
    private boolean PAI;
    private boolean Day;
    private boolean Distance;
    private int downloads;


    public BandModel(String objectId, String image, String author, String specification, String language, String timeformat, String title, String type, String url, String background, String category, String createdAt, String updatedAt, boolean steps, boolean calorie, boolean pulse, boolean time, boolean date, boolean weather, boolean battery, boolean alarm, boolean PAI, boolean day, boolean distance, int downloads) {
        this.objectId = objectId;
        this.image = image;
        this.author = author;
        this.specification = specification;
        this.language = language;
        this.timeformat = timeformat;
        this.title = title;
        this.type = type;
        this.url = url;
        this.background = background;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        Steps = steps;
        Calorie = calorie;
        Pulse = pulse;
        Time = time;
        Date = date;
        Weather = weather;
        Battery = battery;
        Alarm = alarm;
        this.PAI = PAI;
        Day = day;
        Distance = distance;
        this.downloads = downloads;
    }

    public BandModel() {
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public boolean isSteps() {
        return Steps;
    }

    public void setSteps(boolean steps) {
        Steps = steps;
    }

    public boolean isCalorie() {
        return Calorie;
    }

    public void setCalorie(boolean calorie) {
        Calorie = calorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isPulse() {
        return Pulse;
    }

    public void setPulse(boolean pulse) {
        Pulse = pulse;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimeformat() {
        return timeformat;
    }

    public void setTimeformat(String timeformat) {
        this.timeformat = timeformat;
    }

    public boolean isTime() {
        return Time;
    }

    public void setTime(boolean time) {
        Time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isDate() {
        return Date;
    }

    public void setDate(boolean date) {
        Date = date;
    }

    public boolean isWeather() {
        return Weather;
    }

    public void setWeather(boolean weather) {
        Weather = weather;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public boolean isBattery() {
        return Battery;
    }

    public void setBattery(boolean battery) {
        Battery = battery;
    }

    public boolean isAlarm() {
        return Alarm;
    }

    public void setAlarm(boolean alarm) {
        Alarm = alarm;
    }

    public boolean isPAI() {
        return PAI;
    }

    public void setPAI(boolean PAI) {
        this.PAI = PAI;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isDay() {
        return Day;
    }

    public void setDay(boolean day) {
        Day = day;
    }

    public boolean isDistance() {
        return Distance;
    }

    public void setDistance(boolean distance) {
        Distance = distance;
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
}
