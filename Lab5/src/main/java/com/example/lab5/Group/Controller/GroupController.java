package com.example.lab5.Group.Controller;

import com.example.lab5.Group.Dto.CreateGroupDto;
import com.example.lab5.Group.Dto.GroupDto;
import com.example.lab5.Group.Service.GroupService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/groups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroupController {

    private final GroupService groupService = new GroupService();

    @GET
    public Response listGroups() {
        List<GroupDto> response = groupService.listGroups();

        return Response.ok(response).build();
    }

    @GET
    @Path("/{id}")
    public Response getGroupById(@PathParam("id") long groupId) {
        try {
            GroupDto response = groupService.getGroupById(groupId);
            return Response.ok(response).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createGroup(@Valid CreateGroupDto createGroupDto) {
        GroupDto response = groupService.createGroup(createGroupDto);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateGroup(@PathParam("id") long groupId, CreateGroupDto body) {
        try {
            GroupDto response = groupService.updateGroup(groupId, body);
            return Response.ok(response).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}