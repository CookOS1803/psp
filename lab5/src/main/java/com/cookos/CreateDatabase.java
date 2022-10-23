package com.cookos;

import java.sql.*;
import java.util.*;

public class CreateDatabase {

    public static void main( String[] args ) throws SQLException {
        
        System.out.println("This will DELETE all data, do you want to continue? (y/n) ");
        try (var in = new Scanner(System.in)) {
            var input = in.nextLine();

            if (!input.equals("y") && !input.equals("Y"))
                return;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try (var jdbc = new JDBC("root", "12345678", "lab5")) {

            var statement = jdbc.getConnection().createStatement();

            createTables(statement);
            updateAuthorsTable(statement);
            updateAuthorIsbnTable(statement);
            updateTitlesTable(statement);
            updatePublishersTable(statement);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    private static void createTables(Statement statement) throws SQLException {
        var drops = """
        drop table if exists %s
        drop table if exists %s
        drop table if exists %s
        drop table if exists %s
        """.formatted(TableNames.authors, TableNames.titles, TableNames.publishers, TableNames.isbn);

        drops.lines().forEach(s -> {
            try {
                statement.executeUpdate(s);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        final var authorsTable = String.format(
            "CREATE TABLE %s " +
            "(authorID INTEGER NOT NULL AUTO_INCREMENT, " +
            " firstName CHAR(20), " +
            " lastName CHAR(20), " +
            " PRIMARY KEY (authorID))", TableNames.authors
        );

        final var titlesTable = String.format(
            "CREATE TABLE %s " +
            "(isbn CHAR(13) not NULL, " +
            " title VARCHAR(255), " +
            " editionNumber INTEGER, " +
            " year CHAR(4), " +
            " publisherID INTEGER REFERENCES Publishers(publisherID), " +
            " price DECIMAL(8,2), " +
            " PRIMARY KEY (isbn))", TableNames.titles
        );

        final var publishersTable = String.format(
            "CREATE TABLE %s " +
            "(publisherID INTEGER NOT NULL AUTO_INCREMENT, " +
            " publisherName CHAR(100), " +
            " PRIMARY KEY (publisherID))", TableNames.publishers
        );

        final var authorISBNTable = String.format(
            "CREATE TABLE %s " +
            "(authorID INTEGER REFERENCES Authors(authorID), " +
            " isbn CHAR(10) REFERENCES Titles(isbn))", TableNames.isbn
        );

        statement.executeUpdate(authorsTable);
        statement.executeUpdate(titlesTable);
        statement.executeUpdate(publishersTable);
        statement.executeUpdate(authorISBNTable);
    }

    private static void updateAuthorsTable(Statement stmt) throws SQLException {
        String authorFirstNames[] = { "Jane", "Dan", "Ralph Waldo", "F.Scott","John", "Ernest", "Walter", "Stephen", "Stieg",
              "George", "Joanne K.", "John", "John R. R.", "Kurt", "Andy" };
        String authorLastNames[] = { "Austen", "Brown", "Emerson", "Firtzgerald", "Grisham", "Hemingway", "Isaacson",
              "King", "Larsson", "Orwell", "Rowling", "Steinbeck", "Tolkien", "Vonnegut", "Weir" };
        // reset the auto increment
        String updateAuthorsTable = "ALTER TABLE %s AUTO_INCREMENT = 1".formatted(TableNames.authors);
        
        stmt.executeUpdate(updateAuthorsTable);

        for (int i = 1; i <= authorFirstNames.length; i++) {
            updateAuthorsTable = "INSERT INTO " + TableNames.authors + " (firstName, lastName)" + "VALUES ('" + authorFirstNames[i - 1]
                  + "', '" + authorLastNames[i - 1] + "');";
            stmt.executeUpdate(updateAuthorsTable);            
        }
    }

    private static void updateAuthorIsbnTable(Statement stmt) throws SQLException {
        
        String isbns[] = { "0141439519", "0307474278", "0142437629", "0743273565", "0345543240", "0684801223",
              "1501127625", "1501175466", "0307949486", "0451524935", "0439708180", "0142000687", "0547928227",
              "0385333849", "0553418026" };
        for (int i = 1; i <= isbns.length; i++) {
            String updateAuthorISBNTable = "INSERT INTO " + TableNames.isbn +" (authorID, isbn)" + "VALUES (" + i + ", '" + isbns[i - 1] + "')";
            stmt.executeUpdate(updateAuthorISBNTable);
        }
    }
  
    private static void updateTitlesTable(Statement stmt) throws SQLException {
        String isbn[] = { "0141439519", "0307474278", "0142437629", "0743273565", "0345543240", "0684801223",
              "1501127625", "1501175466", "0307949486", "0451524935", "0439708180", "0142000687", "0547928227",
              "0385333849", "0553418026" };
        String editionNumber[] = { "10", "10", "8", "4", "5", "7", "3", "14", "5", "3", "1", "18", "12", "2", "3" };
        String year[] = { "2002", "2009", "2003", "2004", "2014", "1995", "2015", "2017", "2011", "1961", "1999",
              "2002", "2012", "1999", "2014" };
        String publisherID[] = { "1", "2", "1", "3", "4", "3", "5", "3", "6", "7", "8", "1", "9", "10", "11" };
        String price[] = { "8.00", "8.99", "13.58", "6.99", "8.49", "9.98", "11.99", "9.55", "8.60", "7.64", "7.47",
              "13.60", "11.16", "12.51", "10.76" };
        String title[] = { "Pride and Prejudice", "The Da Vinci Code", "Nature and Selected Essays", "The Great Gatsby",
              "Sycamore Row", "The Old Man and The Sea", "Steve Jobs", "It: A Novel",
              "The Girl with the Dragon Tattoo", "1984", "Harry Potter and the Sorcerer''s Stone", "Cannery Row",
              "The Hobbit", "Slaughterhouse-Five", "The Martian" };
        
        for (int i = 0; i < isbn.length; i++) {
            String updateTitlesTable = "INSERT INTO " + TableNames.titles +" (isbn, title, editionNumber, year, publisherID, price)"
                + "VALUES ('" + isbn[i] + "', '" + title[i] + "', " + editionNumber[i] + ",'" + year[i] + "',"
                + publisherID[i] + "," + price[i] + ")";
                
                stmt.executeUpdate(updateTitlesTable);
                
        }
    }
    
    private static void updatePublishersTable(Statement stmt) throws SQLException {
        String publishers[] = { "Penguin", "Anchor", "Scribner", "Dell Books", "Simon & Schuster", "Vintage Crime",
              "Signet", "Scholastic", "Houghton Mifflin", "Dial Press", "Broadway" };
        // reset the auto increment
        String updatePublishersTable = "ALTER TABLE " + TableNames.publishers + " AUTO_INCREMENT = 1";
        
        stmt.executeUpdate(updatePublishersTable);
        
        for (int i = 0; i < publishers.length; i++) {
            updatePublishersTable = "INSERT INTO " + TableNames.publishers + " (publisherName)" + "VALUES ('" + publishers[i] + "')";
            stmt.executeUpdate(updatePublishersTable);
        }
    }  
}
