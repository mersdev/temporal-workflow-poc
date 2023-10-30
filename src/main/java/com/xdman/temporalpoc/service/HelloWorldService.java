package com.xdman.temporalpoc.service;

import com.xdman.temporalpoc.activities.helloworld.HelloWorldActivityImpl;
import com.xdman.temporalpoc.util.RandomWorkFlowIdGenerator;
import com.xdman.temporalpoc.workflow.hello.HelloWorldWorkFlow;
import com.xdman.temporalpoc.workflow.hello.HelloWorldWorkFlowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HelloWorldService {
    private WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    private WorkflowClient client = WorkflowClient.newInstance(service);
    private String workflowId = "";

    public void createWorkerAndAssign(){
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(HelloWorldWorkFlow.QUEUE_NAME);
        worker.registerWorkflowImplementationTypes(HelloWorldWorkFlowImpl.class);
        worker.registerActivitiesImplementations(new HelloWorldActivityImpl());
        factory.start();
    }

    public String startWorkFlow(){
        createWorkerAndAssign();
        workflowId = RandomWorkFlowIdGenerator.generateRandomWorkFlowId();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(HelloWorldWorkFlow.QUEUE_NAME)
                .setWorkflowId(workflowId)
                .build();
        HelloWorldWorkFlow flow = client.newWorkflowStub(HelloWorldWorkFlow.class,options);
        String greeting = flow.getGreeting("Ali");
        //factory.shutdown();
        return greeting;
    }
    public void sendBye(){
        HelloWorldWorkFlow workFlow = client.newWorkflowStub(HelloWorldWorkFlow.class,workflowId);
        workFlow.signalGoodBye();
    }
    public void sendFrance(){
        HelloWorldWorkFlow workFlow = client.newWorkflowStub(HelloWorldWorkFlow.class,workflowId);
        workFlow.signalFrance();
    }
    public String getMessage(){
        HelloWorldWorkFlow workFlow = client.newWorkflowStub(HelloWorldWorkFlow.class,workflowId);
        return workFlow.getMessage();
    }

}
