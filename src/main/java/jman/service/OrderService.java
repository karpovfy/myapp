package jman.service;

import jman.domain.Order;

public interface OrderService {
    Order getOrder(int id);
    void saveOrder(Order order);
}
