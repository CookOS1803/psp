package com.cookos.films;

import com.cookos.IPrintable;

public class HistoricalFilm implements IFilm, IPrintable {
    
    protected String director;
    protected int duration;
    protected Auditory auditory;

    protected String period;
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }

    protected int veracity;
    public int getVeracity() {
        return veracity;
    }
    public void setVeracity(int veracity) {
        this.veracity = veracity;
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
        return "Исторический фильм";
    }
    
    public HistoricalFilm() {
        this.director = "Unknown director";
        this.duration = -1;
        this.auditory = Auditory.RP;
        this.period = "Unknown period";
        this.veracity = -1;
    }

    public HistoricalFilm(String director, int duration, Auditory auditory) {
        this.director = director;
        this.duration = duration;
        this.auditory = auditory;
        this.period = "Unknown period";
        this.veracity = -1;
    }

    public HistoricalFilm(String director, int duration, Auditory auditory, String period, int veracity) {
        this.director = director;
        this.duration = duration;
        this.auditory = auditory;
        this.period = period;
        this.veracity = veracity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((auditory == null) ? 0 : auditory.hashCode());
        result = prime * result + ((director == null) ? 0 : director.hashCode());
        result = prime * result + duration;
        result = prime * result + ((period == null) ? 0 : period.hashCode());
        result = prime * result + veracity;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof HistoricalFilm)
        {
            HistoricalFilm temp = (HistoricalFilm)obj;
            return director.equals(temp.getDirector())
                && duration == temp.getDuration()
                && auditory.equals(temp.getAuditory())
                && period.equals(temp.getPeriod())
                && veracity == temp.getVeracity();
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return "Тип: " + getName() + "; Режиссёр: " + director + "; Продолжительность: "
            + duration + "мин; Аудитория: " + auditory + "; Период: " + period + "; Достоверность: " + veracity;
    }
}
