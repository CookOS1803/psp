package com.cookos.films;

import com.cookos.IPrintable;

public class Musical implements IFilm, IPrintable {
    
    protected String director;
    protected int duration;
    protected Auditory auditory;

    protected String instrument;
    public String getInstrument() {
        return instrument;
    }
    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    protected int songsCount;
    public int getSongsCount() {
        return songsCount;
    }
    public void setSongsCount(int songsCount) {
        this.songsCount = songsCount;
    }

    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public Auditory getAuditory() {
        return auditory;
    }

    @Override
    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void setAuditory(Auditory auditory) {
        this.auditory = auditory;
    }

    @Override
    public String getName() {
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
            return director.equals(temp.getDirector())
                && duration == temp.getDuration()
                && auditory.equals(temp.getAuditory())
                && instrument.equals(temp.getInstrument())
                && songsCount == temp.getSongsCount();
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return "Тип: " + getName() + "; Режиссёр: " + director + "; Продолжительность: "
            + duration + "мин; Аудитория: " + auditory + "; Инструмент: " + instrument + "; Количество песен: " + songsCount;
    }
}
