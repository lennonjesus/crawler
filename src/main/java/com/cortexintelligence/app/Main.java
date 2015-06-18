package com.cortexintelligence.app;

import com.cortexintelligence.app.helper.ConsoleHelper;

import static com.cortexintelligence.app.helper.ConsoleHelper.println;

/**
 * Created by lennonjesus on 18/06/15.
 */
public class Main {

    public static void main(String [] args) {

        println("#### start ####");

        new ConsoleHelper().init(args);

        println("#### end ####");

    }

}
