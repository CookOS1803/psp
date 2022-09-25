package com.cookos.films;

import com.cookos.IPrintable;

public class HistoricalFilm implements IFilm, IPrintable {
    
    protected String director;
    protected int duration;
    protected Auditory auditory;

    protected String period;
    public String get_period() {
        return period;
    }
    public void set_period(String period) {
        this.period = period;
    }

    protected int veracity;
    public int get_veracity() {
        return veracity;
    }
    public void set_veracity(int veracity) {
        this.veracity = veracity;
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
            return director.equals(temp.get_director())
                && duration == temp.get_duration()
                && auditory.equals(temp.get_auditory())
                && period.equals(temp.get_period())
                && veracity == temp.get_veracity();
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return "Тип: " + get_name() + "; Режиссёр: " + director + "; Продолжительность: "
            + duration + "мин; Аудитория: " + auditory + "; Период: " + period + "; Достоверность: " + veracity;
    }
}
