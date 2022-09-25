package com.cookos.films;

public interface IFilm {
    String get_director();
    int get_duration();
    Auditory get_auditory();

    void set_director(String director);
    void set_duration(int duration);
    void set_auditory(Auditory auditory);

    String get_name();
}
