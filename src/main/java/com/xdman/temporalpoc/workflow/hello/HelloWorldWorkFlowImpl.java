package com.xdman.temporalpoc.workflow.hello;

import com.xdman.temporalpoc.activities.helloworld.HelloWorldActivity;
import com.xdman.temporalpoc.workflow.hello.HelloWorldWorkFlow;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class HelloWorldWorkFlowImpl implements HelloWorldWorkFlow {
    public boolean isTimeBack = false;
    public boolean isFrance = false;
    private String message = "";
    ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(10))
            .build();
    private final HelloWorldActivity activity = Workflow.newActivityStub(HelloWorldActivity.class,options);
    @Override
    public String getGreeting(String name) {
        message = activity.composeGreeting(name);
        Workflow.await(()-> isTimeBack);
        message = activity.sayGoodBye(name);
        Workflow.await(()-> isFrance);
        message = activity.bonjour(name);
        return message;
    }

    @Override
    public void signalGoodBye() {
        this.isTimeBack = true;
    }

    @Override
    public void signalFrance() {
        this.isFrance = true;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
