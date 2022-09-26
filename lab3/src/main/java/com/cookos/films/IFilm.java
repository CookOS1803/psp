package com.cookos.films;

public interface IFilm {
    String getDirector();
    int getDuration();
    Auditory getAuditory();

    void setDirector(String director);
    void setDuration(int duration);
    void setAuditory(Auditory auditory);

    String getName();
}
