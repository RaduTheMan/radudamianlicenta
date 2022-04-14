package com.damian.gemixqueapi.projection;

import com.damian.gemixqueapi.entity.game.CustomReleaseDate;

import java.util.List;

public interface DetailsInterfaceProjection {
    List<String> getGameModes();
    List<String> getInvolvedCompanies();
    List<String> getPlatforms();
    List<CustomReleaseDate> getReleaseDates();
}
