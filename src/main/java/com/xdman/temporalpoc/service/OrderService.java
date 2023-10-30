package com.xdman.temporalpoc.service;

import com.xdman.temporalpoc.activities.order.OrderActivityImpl;
import com.xdman.temporalpoc.model.request.OrderRequest;
import com.xdman.temporalpoc.model.response.OrderResponse;
import com.xdman.temporalpoc.repository.OrderRepository;
import com.xdman.temporalpoc.util.RandomWorkFlowIdGenerator;
import com.xdman.temporalpoc.workflow.order.OrderWorkFlow;
import com.xdman.temporalpoc.workflow.order.OrderWorkFlowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    private WorkflowClient client = WorkflowClient.newInstance(service);
    private String workflowId = "";
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public void createWorkerAndAssign(){
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(OrderWorkFlow.QUEUE_NAME);
        worker.registerWorkflowImplementationTypes(OrderWorkFlowImpl.class);
        worker.registerActivitiesImplementations(new OrderActivityImpl(repository));
        factory.start();
    }

    public OrderResponse startOrderWorkFlow(OrderRequest request){
        createWorkerAndAssign();
        workflowId = RandomWorkFlowIdGenerator.generateRandomWorkFlowId();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(OrderWorkFlow.QUEUE_NAME)
                .setWorkflowId(workflowId)
                .build();
        OrderWorkFlow flow = client.newWorkflowStub(OrderWorkFlow.class, options);
        OrderResponse response = flow.processOrder(request);
        //factory.shutdown();
        return response;
    }
    public void sendOrderAccepted(){
        OrderWorkFlow workFlow = client.newWorkflowStub(OrderWorkFlow.class,workflowId);
        workFlow.signalOrderAccepted();
    }
    public void sendOrderPickedUp(){
        OrderWorkFlow workFlow = client.newWorkflowStub(OrderWorkFlow.class,workflowId);
        workFlow.signalOrderPickedUp();
    }
    public void sendOrderReceived(){
        OrderWorkFlow workFlow = client.newWorkflowStub(OrderWorkFlow.class,workflowId);
        workFlow.signalOrderReceived();
    }
    public OrderResponse getOrderResponse(){
        OrderWorkFlow workFlow = client.newWorkflowStub(OrderWorkFlow.class,workflowId);
        return workFlow.getOrderResponse();
    }

}