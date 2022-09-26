package com.cookos.films;

public class DrawnCartoon extends Cartoon {
    
    protected String director;
    protected int duration;
    protected Auditory auditory;

    protected String artist;
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
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
        return "Рисованный мультфильм";
    }
    
    public DrawnCartoon() {
        this.director = "Unknown director";
        this.duration = -1;
        this.auditory = Auditory.RP;
        this.graphicStyle = "Unknown graphic style";
        this.artist = "Unknown artist";
    }
    public DrawnCartoon(String director, int duration, Auditory auditory) {
        this.director = director;
        this.duration = duration;
        this.auditory = auditory;
        this.graphicStyle = "Unknown graphic style";
        this.artist = "Unknown artist";
    }
    public DrawnCartoon(String director, int duration, Auditory auditory, String graphicStyle, String artist) {
        this.director = director;
        this.duration = duration;
        this.auditory = auditory;
        this.graphicStyle = graphicStyle;
        this.artist = artist;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((artist == null) ? 0 : artist.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof DrawnCartoon)
        {
            DrawnCartoon temp = (DrawnCartoon)obj;
            return artist.equals(temp.getArtist());
        }
        else
            return false;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "; Художник: " + artist;
    }
    
}
