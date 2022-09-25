package com.cookos.films;

import com.cookos.IPrintable;

public abstract class Cartoon implements IFilm, IPrintable {
    protected String graphicStyle;
    public String get_graphicStyle() {
        return graphicStyle;
    }
    public void set_graphicStyle(String graphicStyle) {
        this.graphicStyle = graphicStyle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((graphicStyle == null) ? 0 : graphicStyle.hashCode());
        result = prime * result + ((get_auditory() == null) ? 0 : get_auditory().hashCode());
        result = prime * result + ((get_director() == null) ? 0 : get_director().hashCode());
        result = prime * result + get_duration();
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
            return get_director().equals(temp.get_director())
                && get_duration() == temp.get_duration()
                && get_auditory().equals(temp.get_auditory())
                && graphicStyle.equals(temp.get_graphicStyle());
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return "Тип: " + get_name() + "; Режиссёр: " + get_director() + "; Продолжительность: "
            + get_duration() + "мин; Аудитория: " + get_auditory() + "; Графический стиль: " + graphicStyle;
    }
}
