package com.turing.patttern.creational;

import com.turing.patttern.creational.builder.LunchOrder;
import com.turing.patttern.creational.prototype.Movie;
import com.turing.patttern.creational.prototype.Registry;
import com.turing.patttern.creational.singleton.Singleton;

abstract public class CreationalDPTestRunner {
    public static void testSingletonPattern(){
        Singleton singletonInstance = Singleton.getInstance();
        System.out.println(singletonInstance);
        Singleton anotherSingletonInstance = Singleton.getInstance();
        System.out.println(anotherSingletonInstance);
    }

    public static void testBuilderPattern(){
        LunchOrder.Builder lunchOrderBuilder = new LunchOrder.Builder();
        lunchOrderBuilder.bread("Wheat").meat("Ham");

        LunchOrder lunchOrder = lunchOrderBuilder.build();

        System.out.println(lunchOrder.getBread());
        System.out.println(lunchOrder.getMeat());
        System.out.println(lunchOrder.getCondiments());
        System.out.println(lunchOrder.getDressing());

    }

    public static void testPrototypePattern(){
        /*String sql = "select * from xyz where name = ?";
        List<String> parameters = new ArrayList<>();
        parameters.add("Jaskirat Uppal");
        Statement firstStatement = new Statement(sql, parameters, new Record());
        System.out.println(firstStatement.getSqlQuery());
        System.out.println(firstStatement.getParameters());
        System.out.println(firstStatement.getRecord());

        Statement secondStatement = firstStatement.clone();
        System.out.println(secondStatement.getSqlQuery());
        System.out.println(secondStatement.getParameters());
        System.out.println(secondStatement.getRecord());*/

        Registry registry = new Registry();
        Movie movie = (Movie) registry.createItem("Movie");
        movie.setTitle("Star Wars: Return of the Jedi");
        movie.setRuntime(205);

        System.out.println(movie.getTitle());
        System.out.println(movie.getUrl());
        System.out.println(movie.getRuntime());
        System.out.println(movie.getPrice());
    }
}
