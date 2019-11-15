package jman.service;

import jman.domain.Message;

import java.util.List;

public interface MessageService {
    Message getMessage(int id);
    List<Message> getMessages();
    void saveMessage(Message message);
}
