package com.xdman.temporalpoc.activities.helloworld;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface HelloWorldActivity {
    String composeGreeting(String name);
    String sayGoodBye(String name);
    String bonjour(String name);
}
