package com.xdman.temporalpoc.workflow.order;

import com.xdman.temporalpoc.activities.helloworld.HelloWorldActivity;
import com.xdman.temporalpoc.activities.order.OrderActivity;
import com.xdman.temporalpoc.model.request.OrderRequest;
import com.xdman.temporalpoc.model.response.OrderResponse;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class OrderWorkFlowImpl implements OrderWorkFlow {
    ActivityOptions options = ActivityOptions.newBuilder()
            // Set the start-to-close timeout to 30 seconds.
            .setStartToCloseTimeout(Duration.ofSeconds(30))
            .build();
    private final OrderActivity activity = Workflow.newActivityStub(OrderActivity.class,options);
    private OrderResponse response;
    public boolean isAccepted = false;
    public boolean isPickedUp = false;
    public boolean isReceived = false;
    @Override
    public OrderResponse processOrder(OrderRequest request) {
        response = activity.placeOrder(request);
        Workflow.await(() -> isAccepted);
        response = activity.setOrderAccepted();
        Workflow.await(() -> isPickedUp);
        response = activity.setOrderPickedUp();
        Workflow.await(() -> isReceived);
        response = activity.setOrderDelivered();
        return response;
    }

    @Override
    public void signalOrderAccepted() {
        this.isAccepted = true;
    }

    @Override
    public void signalOrderPickedUp() {
        this.isPickedUp = true;
    }

    @Override
    public void signalOrderReceived() {
        this.isReceived = true;
    }

    @Override
    public OrderResponse getOrderResponse() {
        return response;
    }
}
