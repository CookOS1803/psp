package com.cookos;

import java.util.ArrayList;
import com.cookos.films.*;

public class App {
    public static void main(String[] args) {
        var films = new ArrayList<IFilm>();
        films.add(new HistoricalFilm());
        films.add(new Musical());

        for (var f : films) {
            WriterInfo.print((IPrintable)f);
        }
    }
}
