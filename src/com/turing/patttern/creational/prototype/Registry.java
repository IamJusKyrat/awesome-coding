package com.turing.patttern.creational.prototype;

import java.util.HashMap;
import java.util.Map;

public class Registry {

    private Map<String, Item> items = new HashMap<>();

    public Registry(){
        loadItems();
    }

    public Item createItem(String type){
        Item item = null;
        try {
            item = (items.get(type)).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return item;
    }

    private void loadItems(){
        Movie movie = new Movie();
        movie.setTitle("Star Wars");
        movie.setPrice(24.99);
        movie.setUrl("https://amazon.com/star-wars");
        movie.setRuntime(137);
        items.put("Movie", movie);

        Book book = new Book();
        book.setTitle("Dune: Messiah");
        book.setPrice(6.99);
        book.setUrl("https://amazon.com/dune-messiah");
        book.setNumberOfPages(467);
        items.put("Book", book);
    }
}
