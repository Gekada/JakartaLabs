package org.example.lab_3.ejb;

import jakarta.ejb.Stateless;
import org.example.lab_3.model.Group;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Stateless
public class GroupServiceBean implements GroupService {
    private final List<Group> groups = new ArrayList<>();

    public GroupServiceBean() {
        groups.add(new Group("ІТ-41мн", "Інженерія програмного забезпечення комп’ютерних систем"));
        groups.add(new Group("ІК-41мп", "Інформаційне забезпечення робототехнічних систем"));
        groups.add(new Group("ІМ-41мн", "Інженерія програмного забезпечення комп’ютерних систем"));
    }

    @Override
    public List<Group> getAllGroups() {
        return new ArrayList<>(groups);
    }

    @Override
    public void addGroup(Group group) {
        groups.add(group);
    }

    @Override
    public void deleteGroup(String groupName) {
        groups.removeIf(g -> g.getName().equalsIgnoreCase(groupName));
    }

    @Override
    public void updateGroup(Group updatedGroup) {
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getName().equalsIgnoreCase(updatedGroup.getName())) {
                groups.set(i, updatedGroup);
                break;
            }
        }
    }
}
