package com.damian.gemixqueapi.projection.game;


import java.util.List;

public interface GameInterfaceProjection {
    String getUuid();
    String getTitle();
    List<String> getGenres();
    VisualsInterfaceProjection getVisuals();
    DetailsInterfaceProjection getDetails();
    AverageInterfaceProjection getAverage();
}