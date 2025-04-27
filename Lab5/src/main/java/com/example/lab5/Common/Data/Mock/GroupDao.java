package com.example.lab5.Common.Data.Mock;

import com.example.lab5.Common.Data.Entity.Group;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class GroupDao {

    private static final Map<Long, Group> GROUPS = new HashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(100);

    static {
        GROUPS.put(1L, new Group(1L, "Computer Science"));
        GROUPS.put(2L, new Group(2L, "Mathematics"));
        GROUPS.put(3L, new Group(3L, "Physics"));
    }

    public Optional<Group> getById(Long groupId) {
        return Optional.ofNullable(GROUPS.get(groupId));
    }

    public List<Group> getAll() {
        return new ArrayList<>(GROUPS.values());
    }

    public boolean existsById(Long groupId) {
        return GROUPS.containsKey(groupId);
    }

    public Group create(Group group) {
        long id = ID_GENERATOR.incrementAndGet();
        group.setId(id);
        GROUPS.put(id, group);
        return group;
    }

    public Group update(Group group) {
        if (!GROUPS.containsKey(group.getId())) {
            throw new NoSuchElementException("Group with ID " + group.getId() + " not found.");
        }

        GROUPS.put(group.getId(), group);
        return group;
    }
}