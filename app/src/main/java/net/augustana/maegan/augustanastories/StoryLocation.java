package net.augustana.maegan.augustanastories;

public class StoryLocation {
    private final String name;
    private final String url;
    private final double lat;
    private final double lng;


    public StoryLocation(String name, String url, double lat, double lng) {
        this.name = name;
        this.url = url;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }


}
