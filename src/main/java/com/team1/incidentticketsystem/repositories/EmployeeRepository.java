package com.team1.incidentticketsystem.repositories;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.team1.incidentticketsystem.models.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,UUID>
{
    Optional<List<Employee>> findByEmail(String email);
}
