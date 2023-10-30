package com.xdman.temporalpoc.workflow.order;

import com.xdman.temporalpoc.model.request.OrderRequest;
import com.xdman.temporalpoc.model.response.OrderResponse;
import io.temporal.workflow.QueryMethod;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderWorkFlow {
    public static final String QUEUE_NAME = "Customer_Order";
    @WorkflowMethod
    OrderResponse processOrder(OrderRequest request);
    @SignalMethod
    void signalOrderAccepted();
    @SignalMethod
    void signalOrderPickedUp();
    @SignalMethod
    void signalOrderReceived();
    @QueryMethod
    OrderResponse getOrderResponse();
}
