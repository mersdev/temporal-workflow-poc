package com.xdman.temporalpoc.activities.helloworld;

import com.xdman.temporalpoc.activities.helloworld.HelloWorldActivity;

public class HelloWorldActivityImpl implements HelloWorldActivity {
    @Override
    public String composeGreeting(String name) {
        return "Hello "+ name + "!";
    }

    @Override
    public String sayGoodBye(String name) {
        return "GoodBye "+name+"?!";
    }

    @Override
    public String bonjour(String name) {
        return "Bonjour "+name+"...";
    }
}
