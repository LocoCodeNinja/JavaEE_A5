package com.prog3060.kitlouanglath_assignment_5.resource;
import com.prog3060.kitlouanglath_assignment_5.entity.Employee;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("/employees")
public class EmployeeResource {

    private Map<Integer, Employee> employeeMap = new HashMap<>();

    public EmployeeResource() {
        employeeMap.put(1, new Employee(1, "Kit", "Louanglath", "klouanglath@email.com", new Date(), 120000));
        employeeMap.put(2, new Employee(2, "Jim", "Smith", "jsmith@email.com", new Date(), 60000));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String welcome() {
        return "WELCOME TO EMPLOYEE MANAGEMENT SYSTEM";
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Employee> getAllEmployees() {
        return employeeMap.values();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployee(@PathParam("id") int id) {
        return employeeMap.get(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee, @Context UriInfo uriInfo) {
        employee.setId(employeeMap.size() + 1);
        employeeMap.put(employee.getId(), employee);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(Integer.toString(employee.getId()));
        URI location = uriBuilder.build();
        return Response.created(location).build();
    }

    @DELETE
    @Path("/{id}")
    public void deleteEmployee(@PathParam("id") int id) {
        employeeMap.remove(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateEmployee(Employee employee) {
        employeeMap.put(employee.getId(), employee);
    }

}
