package com.monkdevs.ticketingtool.Controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monkdevs.ticketingtool.Models.Department;
import com.monkdevs.ticketingtool.Models.Ticket;
import com.monkdevs.ticketingtool.Services.DepartmentService;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentController {

	@Autowired
	private DepartmentService deptServices;
	
	@GetMapping("/{deptId")
	 public ResponseEntity<?> findDepartmentbydeptId(@PathVariable Long deptId){
	        Department dept= deptServices.findDepartmentbydeptId(deptId);

	        if(dept != null){
	            return new ResponseEntity<>(dept, HttpStatus.OK);
	        }else{
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	 
	@GetMapping("/{deptName")
	 public ResponseEntity<?> findDepartmentbydeptName(@PathVariable String deptName){
	        Department dept1= deptServices.findDepartmentbydeptName(deptName);

	        if(dept1 != null){
	            return new ResponseEntity<>(dept1, HttpStatus.OK);
	        }else{
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	@PostMapping("/createDept")
	 public Department createDepartment(@RequestBody Department department) {
	        return deptServices.createDepartment(department);
	    }
	
	@PutMapping("/updateDept/{deptId}")
	 public Department saveORUpdate(@RequestBody Department department , @PathVariable long deptId) {
	        return deptServices.saveORUpdate(department);
	    }
	 @DeleteMapping("/deleteDept/{deptId}")
	    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
	    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable Long deptId) {
	        try {
	            deptServices.deleteDepartmenttById(deptId);
	            return new ResponseEntity<>(HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
