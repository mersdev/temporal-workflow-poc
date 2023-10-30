package com.xdman.temporalpoc.workflow.hello;

import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface HelloWorldWorkFlow {
    public static final String QUEUE_NAME = "Hello_World";
    @WorkflowMethod
    String getGreeting(String name);
    @SignalMethod
    void signalGoodBye();
    @SignalMethod
    void signalFrance();
    @QueryMethod
    String getMessage();
}
