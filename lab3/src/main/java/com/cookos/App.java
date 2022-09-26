package com.cookos;

import java.util.ArrayList;
import com.cookos.films.*;

public class App {
    public static void main(String[] args) {
        var films = new ArrayList<IFilm>();
        films.add(new HistoricalFilm("Лазарев А. И.", 120, Auditory.PG13, "Античность", 6));
        films.add(new Musical("Герасимов Г. С.", 120, Auditory.PG, "Вокал", 5));
        films.add(new DrawnCartoon("Бородина С. В.", 120, Auditory.R, "Минимализм", "Жмышенко В. А."));
        films.add(new PuppetCartoon("Федотов Ф. Т.", 120, Auditory.G, "Русский народный", 5));

        for (var f : films) {
            WriterInfo.print((IPrintable)f);
        }
    }
}
