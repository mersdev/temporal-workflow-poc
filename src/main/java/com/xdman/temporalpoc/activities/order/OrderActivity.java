package com.xdman.temporalpoc.activities.order;

import com.xdman.temporalpoc.model.request.OrderRequest;
import com.xdman.temporalpoc.model.response.OrderResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface OrderActivity {
    @ActivityMethod
    OrderResponse placeOrder(OrderRequest request);
    @ActivityMethod
    OrderResponse setOrderAccepted();
    @ActivityMethod
    OrderResponse setOrderPickedUp();
    @ActivityMethod
    OrderResponse setOrderDelivered();
}
