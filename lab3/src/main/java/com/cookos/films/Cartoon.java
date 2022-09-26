package com.cookos.films;

import com.cookos.IPrintable;

public abstract class Cartoon implements IFilm, IPrintable {
    protected String graphicStyle;
    public String getGraphicStyle() {
        return graphicStyle;
    }
    public void setGraphicStyle(String graphicStyle) {
        this.graphicStyle = graphicStyle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((graphicStyle == null) ? 0 : graphicStyle.hashCode());
        result = prime * result + ((getAuditory() == null) ? 0 : getAuditory().hashCode());
        result = prime * result + ((getDirector() == null) ? 0 : getDirector().hashCode());
        result = prime * result + getDuration();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof Cartoon)
        {
            Cartoon temp = (Cartoon)obj;
            return getDirector().equals(temp.getDirector())
                && getDuration() == temp.getDuration()
                && getAuditory().equals(temp.getAuditory())
                && graphicStyle.equals(temp.getGraphicStyle());
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return "Тип: " + getName() + "; Режиссёр: " + getDirector() + "; Продолжительность: "
            + getDuration() + "мин; Аудитория: " + getAuditory() + "; Графический стиль: " + graphicStyle;
    }
}
