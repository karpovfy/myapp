package jman.service;

import jman.domain.DeptFiles;

import java.util.List;

public interface DeptFilesService {
    List<DeptFiles> getDeptFiles(int itemDeptId);
}
