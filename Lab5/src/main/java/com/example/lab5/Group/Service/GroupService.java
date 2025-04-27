package com.example.lab5.Group.Service;

import com.example.lab5.Common.Data.Entity.Group;
import com.example.lab5.Common.Data.Mock.GroupDao;
import com.example.lab5.Group.Dto.CreateGroupDto;
import com.example.lab5.Group.Dto.GroupDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class GroupService {

    private final GroupDao groupDAO = new GroupDao();

    public List<GroupDto> listGroups() {
        return groupDAO.getAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public GroupDto getGroupById(Long groupId) {
        Group group = groupDAO.getById(groupId)
                .orElseThrow(() -> new NoSuchElementException("Group with ID " + groupId + " not found."));

        return mapToResponseDTO(group);
    }

    public GroupDto createGroup(CreateGroupDto createGroupDto) {
        Group group = new Group();
        group.setName(createGroupDto.getName());

        Group createdGroup = groupDAO.create(group);

        return mapToResponseDTO(createdGroup);
    }

    public GroupDto updateGroup(Long groupId, CreateGroupDto groupUpdateDTO) {
        Group group = groupDAO.getById(groupId)
                .orElseThrow(() -> new NoSuchElementException("Group with ID " + groupId + " not found."));

        if (groupUpdateDTO.getName() != null) {
            group.setName(groupUpdateDTO.getName());
        }

        Group updatedGroup = groupDAO.update(group);

        return mapToResponseDTO(updatedGroup);
    }

    private GroupDto mapToResponseDTO(Group group) {
        return new GroupDto(group.getId(), group.getName());
    }
}