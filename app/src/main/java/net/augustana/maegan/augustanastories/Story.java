package net.augustana.maegan.augustanastories;

public class Story {
    String place;
    String title;
    String url;
    //location


    public Story(String place, String title, String url) {
        this.place = place;
        this.title = title;
        this.url = url;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
