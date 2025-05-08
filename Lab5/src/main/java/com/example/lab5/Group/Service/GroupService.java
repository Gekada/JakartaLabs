package com.example.lab5.Group.Service;

import com.example.lab5.Group.Dto.CreateGroupDto;
import com.example.lab5.Group.Dto.GroupDto;
import com.example.lb4.Common.Data.Dao.GroupDao;
import com.example.lb4.Common.Data.Entity.Group;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class GroupService {

    private final GroupDao groupDAO;

    @Inject
    public GroupService(GroupDao groupDAO) {
        this.groupDAO = groupDAO;
    }

    public List<GroupDto> listGroups() {
        return groupDAO.getAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public GroupDto getGroupById(Long groupId) {
        Group group = groupDAO.getById(groupId);

        return mapToResponseDTO(group);
    }

    public GroupDto createGroup(CreateGroupDto createGroupDto) {
        Group group = new Group();
        group.setName(createGroupDto.getName());

        groupDAO.create(group);

        return mapToResponseDTO(group);
    }

    public GroupDto updateGroup(Long groupId, CreateGroupDto groupUpdateDTO) {
        Group group = groupDAO.getById(groupId);

        if (groupUpdateDTO.getName() != null) {
            group.setName(groupUpdateDTO.getName());
        }

        groupDAO.update(group);

        return mapToResponseDTO(group);
    }

    private GroupDto mapToResponseDTO(Group group) {
        return new GroupDto(group.getId(), group.getName());
    }
}