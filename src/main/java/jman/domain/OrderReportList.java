package jman.domain;


import java.util.List;

public class OrderReportList {
    private int orderId;
    private List<OrderReport> orderReportList;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderReport> getOrderReportList() {
        return orderReportList;
    }

    public void setOrderReportList(List<OrderReport> orderReportList) {
        this.orderReportList = orderReportList;
    }
}
