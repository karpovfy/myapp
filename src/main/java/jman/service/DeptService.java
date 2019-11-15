package jman.service;

import jman.domain.DayStatusCard;
import jman.domain.DayStatusCardOut;
import jman.domain.Dept;

import java.util.List;

public interface DeptService {
    Dept getDept(int id);
    List<Dept> getAllDepts();
    List<Dept> getDepts();
    void saveDept(Dept dept);
    DayStatusCard getDayStatusCard(int id);
    DayStatusCardOut getDayStatusCardOut(int id);
}
