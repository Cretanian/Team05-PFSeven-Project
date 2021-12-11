package com.pfseven.eshop.repository;

import com.pfseven.eshop.model.OrderItem;

public interface OrderItemRepository {
    void saveOrderItemToDB(OrderItem orderItem);
}
