package com.cookos.films;

public class PuppetCartoon extends Cartoon {
    
    protected String director;
    protected int duration;
    protected Auditory auditory;

    protected int sceneCount;
    public int getSceneCount() {
        return sceneCount;
    }
    public void setSceneCount(int sceneCount) {
        this.sceneCount = sceneCount;
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
        return "Кукольный мультфильм";
    }

    
    public PuppetCartoon() {
        this.director = "Unknown director";
        this.duration = -1;
        this.auditory = Auditory.RP;
        this.graphicStyle = "Unknown graphic style";
        this.sceneCount = -1;
    }
    public PuppetCartoon(String director, int duration, Auditory auditory) {
        this.director = director;
        this.duration = duration;
        this.auditory = auditory;
        this.graphicStyle = "Unknown graphic style";
        this.sceneCount = -1;
    }
    public PuppetCartoon(String director, int duration, Auditory auditory, String graphicStyle, int sceneCount) {
        this.director = director;
        this.duration = duration;
        this.auditory = auditory;
        this.graphicStyle = graphicStyle;
        this.sceneCount = sceneCount;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + sceneCount;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj) && obj instanceof PuppetCartoon)
        {
            PuppetCartoon temp = (PuppetCartoon)obj;
            return sceneCount == temp.getSceneCount();
        }
        else
            return false;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "; Количество сцен: " + sceneCount;
    }
}
