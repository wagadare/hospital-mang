package com.edu.SpringBoot.HospitalManagementSystem.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edu.SpringBoot.HospitalManagementSystem.Entity.Admin;
import com.edu.SpringBoot.HospitalManagementSystem.Exception.ResourceNotFound;
import com.edu.SpringBoot.HospitalManagementSystem.Repository.AdminRepository;
import com.edu.SpringBoot.HospitalManagementSystem.Service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private AdminRepository adminRepository;
	
	public AdminServiceImpl(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

    @Override
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
    
    @Override
	public List<Admin> getAllAdmin() {
		return adminRepository.findAll();
	}

    @Override
	public Admin getAdminById(long id) {
		Optional<Admin> admin = adminRepository.findById(id);
		if(admin.isPresent()) {
			return admin.get();
		}
		else {
          throw new ResourceNotFound("Admin","Id",id);
		}
	}

    @Override
	public Admin updateAdmin(Admin admin, long id) {
		Admin adm = new Admin();
	 try {
		   adm = adminRepository.findById(id).orElseThrow(()->new ResourceNotFound("Employee","Id",id));
	} catch (ResourceNotFound e) {
		
		e.printStackTrace();
	}
	 adm.setFirstName(adm.getFirstName());
	 adm.setLastName(adm.getLastName());
	 adm.setEmail(adm.getEmail());
	 
	 adminRepository.save(adm);
	 return adm;
	}
    
    @Override
	public List<Admin> getAdminByFirstName(String firstName) {
		return adminRepository.findByFirstName(firstName);
	}

    @Override
	public List<Admin> getAdminByLastName(String lastName) {
		return adminRepository.findByLastName(lastName);
	}

    @Override
	public List<Admin> getAdminByFirstNameAndLastName(String firstName, String lastName) {
		return adminRepository.findByFirstNameAndLastName(firstName, lastName);
	}
    
    @Override
	public List<Admin> getAdminByFirstNameOrLastName(String firstName, String lastName) {
		return adminRepository.findByFirstNameOrLastName(firstName, lastName);
	}
    
    @Override
	public Admin getAdminById(Long id) {
		return adminRepository.findAdminById(id);
	}

    @Override
	public Admin getAdminFullNameById(long id) {
		return adminRepository.findAdminFullNameById(id);
    }
    
    @Override
	public List<Admin> getAdminInDescOrder() {
		return adminRepository.findAdminInDescOrder();
	}

    @Override
	public void deleteAdmin(long id) {
		adminRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Admin", "Id", id));
		adminRepository.deleteById(id);
    }
}








