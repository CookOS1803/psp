package com.cookos.films;

import com.cookos.IPrintable;

public class Musical implements IFilm, IPrintable {
    
    protected String director;
    protected int duration;
    protected Auditory auditory;

    protected String instrument;
    public String get_instrument() {
        return instrument;
    }
    public void set_instrument(String instrument) {
        this.instrument = instrument;
    }

    protected int songsCount;
    public int get_songsCount() {
        return songsCount;
    }
    public void set_songsCount(int songsCount) {
        this.songsCount = songsCount;
    }

    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public String get_director() {
        return director;
    }

    @Override
    public int get_duration() {
        return duration;
    }

    @Override
    public Auditory get_auditory() {
        return auditory;
    }

    @Override
    public void set_director(String director) {
        this.director = director;
    }

    @Override
    public void set_duration(int duration) {
        this.duration = duration;
    }

    @Override
    public void set_auditory(Auditory auditory) {
        this.auditory = auditory;
    }

    @Override
    public String get_name() {
        return "Мюзикл";
    }
    
    public Musical() {
        this.director = "Unknown director";
        this.duration = -1;
        this.auditory = Auditory.RP;
        this.instrument = "Unknown instrument";
        this.songsCount = -1;
    }

    public Musical(String director, int duration, Auditory auditory) {
        this.director = director;
        this.duration = duration;
        this.auditory = auditory;
        this.instrument = "Unknown instrument";
        this.songsCount = -1;
    }

    public Musical(String director, int duration, Auditory auditory, String instrument, int songsCount) {
        this.director = director;
        this.duration = duration;
        this.auditory = auditory;
        this.instrument = instrument;
        this.songsCount = songsCount;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((auditory == null) ? 0 : auditory.hashCode());
        result = prime * result + ((director == null) ? 0 : director.hashCode());
        result = prime * result + duration;
        result = prime * result + ((instrument == null) ? 0 : instrument.hashCode());
        result = prime * result + songsCount;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Musical)
        {
            Musical temp = (Musical)obj;
            return director.equals(temp.get_director())
                && duration == temp.get_duration()
                && auditory.equals(temp.get_auditory())
                && instrument.equals(temp.get_instrument())
                && songsCount == temp.get_songsCount();
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return "Тип: " + get_name() + "; Режиссёр: " + director + "; Продолжительность: "
            + duration + "мин; Аудитория: " + auditory + "; Инструмент: " + instrument + "; Количество песен: " + songsCount;
    }
}
