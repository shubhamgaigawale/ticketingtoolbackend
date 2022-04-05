package com.monkdevs.ticketingtool.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monkdevs.ticketingtool.Models.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	Department findDepartmentbydeptId (long deptId);
	Department findDepartmentbydeptName(String deptName);
	@SuppressWarnings("unchecked")
	Department save(Department department);
	
	Department deleteById(long deptId);

}
