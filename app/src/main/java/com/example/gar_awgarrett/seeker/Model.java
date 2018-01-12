package com.example.gar_awgarrett.seeker;

public class Model{

    private int icon;
    private String title;
    private String distance;

    private boolean isGroupHeader = false;

    public Model(String title) {
        this(-1,title,"2");
        isGroupHeader = true;
    }
    public Model(int icon, String title, String distance) {
        super();
        this.icon = icon;
        this.title = title;
        this.distance = distance;
    }
    //getters & setters...

    public boolean isGroupHeader() {
        return isGroupHeader;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDistance() {

        return distance;
    }

}
