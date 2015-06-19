package com.cortexintelligence.app;

import com.cortexintelligence.app.helper.console.ConsoleHelper;

/**
 * Created by lennonjesus on 18/06/15.
 */
public class Main {

    public static void main(String [] args) {

        System.out.println("#### start ####");

        new ConsoleHelper().init(args);

        System.out.println("#### end ####");

    }

}
