package net.augustana.maegan.augustanastories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StoryCollection {
    // Singleton pattern
    private static StoryCollection defaultStoryCollection;
    public static StoryCollection getDefaultStoryCollection() {
        if (defaultStoryCollection == null) {
            defaultStoryCollection = new StoryCollection();
        }
        return defaultStoryCollection;
    }


    private Map<String,Story> stories = new TreeMap<>();

    private StoryCollection() {
        Story[] storyArray = new Story[] {
                new Story("Sorenson","Sorenson's ghost", "http://lovelace.augustana.edu/augustanastories/story1.html"),
                new Story("Olin", "The Olin Goat", "http://lovelace.augustana.edu/augustanastories/story2.html")
        };
        for (Story story : storyArray) {
            stories.put(story.getPlace(),story);
        }
    }

    public Story getStoryByPlace(String place) {
        return stories.get(place);
    }

    public List<Story> getStoryList() {
        return new ArrayList<>(stories.values());
    }

}
