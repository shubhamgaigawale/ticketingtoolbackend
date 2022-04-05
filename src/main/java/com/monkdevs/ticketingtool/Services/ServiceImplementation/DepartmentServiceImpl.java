package com.monkdevs.ticketingtool.Services.ServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkdevs.ticketingtool.Models.Department;
import com.monkdevs.ticketingtool.Repositories.DepartmentRepository;
import com.monkdevs.ticketingtool.Services.DepartmentService;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentRepository dept;

	@Override
	public Department findDepartmentbydeptId(long deptId) {
		// TODO Auto-generated method stub
		return  dept.findDepartmentbydeptId(deptId);
	
	}

	@Override
	public Department findDepartmentbydeptName(String deptName) {
		// TODO Auto-generated method stub
		return dept.findDepartmentbydeptName(deptName);
	}
	
	@Override
	public Department createDepartment(Department department){
		String deptName = "";
		
		department.setDeptName( deptName);
		return dept.save(department);
	}
	@Override
	public Department saveORUpdate(Department department){
		String deptName = "";
		if(department == null) {
			
			throw new NullPointerException("Department cannot be empty");
		}
		department.setDeptName( deptName);
		return dept.save(department);
	}
	
	
	@Override
    public void deleteDepartmenttById(Long deptId) {
      dept.deleteById(deptId);        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
