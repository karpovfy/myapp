package jman.service;

import jman.domain.OrderFile;

import java.util.List;

public interface OrderFileService {
    void addFile(OrderFile file);
    OrderFile getFile(int id);
    List<OrderFile> getUserFiles(int id);
}
