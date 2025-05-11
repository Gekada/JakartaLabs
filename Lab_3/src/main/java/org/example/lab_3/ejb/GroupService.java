package org.example.lab_3.ejb;

import org.example.lab_3.model.Group;
import java.util.List;

public interface GroupService {
    List<Group> getAllGroups();
    void addGroup(Group group);
    void deleteGroup(String groupName);
    void updateGroup(Group group);

}
