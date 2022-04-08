package com.edu.SpringBoot.HospitalManagementSystem.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edu.SpringBoot.HospitalManagementSystem.Entity.Doctor;
import com.edu.SpringBoot.HospitalManagementSystem.Exception.ResourceNotFound;
import com.edu.SpringBoot.HospitalManagementSystem.Repository.DoctorRepository;
import com.edu.SpringBoot.HospitalManagementSystem.Service.DoctorService;

@Service
public  class DoctorServiceImpl implements DoctorService {

	private DoctorRepository doctorRepository;
	
	
	public DoctorServiceImpl(DoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}


	@Override
	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}


	@Override
	public List<Doctor> getAllDoctor() {
		return doctorRepository.findAll();
		
	}


	@Override
	public Doctor getDoctorById(long id) {
		Optional<Doctor> doctor = doctorRepository.findById(id);
		if(doctor.isPresent()) {
			return doctor.get();
		}
		else {
          
			throw new ResourceNotFound("Doctor","Id",id);
		}
		
	}


	@Override
	public Doctor updateDoctor(Doctor doctor, long id) {
		Doctor doc = new Doctor();
	 try {
		   doc = doctorRepository.findById(id).orElseThrow(
				 ()-> 		 new ResourceNotFound("Doctor","Id",id));
	} catch (ResourceNotFound e) {
		
		e.printStackTrace();
	}
	 doc.setFirstName(doc.getFirstName());
	 doc.setLastName(doc.getLastName());
	 doc.setEmail(doc.getEmail());
	
	doctorRepository.save(doc);
	return doc;
	}
    
	@Override
	public List<Doctor> getDoctorByFirstName(String firstName) {
		return doctorRepository.findByFirstName(firstName);
	}

    @Override
	public List<Doctor> getDoctorByLastName(String lastName) {
		return doctorRepository.findByLastName(lastName);
	}

    @Override
	public List<Doctor> getDoctorByFirstNameAndLastName(String firstName, String lastName) {
		return doctorRepository.findByFirstNameAndLastName(firstName, lastName);
	}
    
    @Override
	public List<Doctor> getDoctorByFirstNameOrLastName(String firstName, String lastName) {
		return doctorRepository.findByFirstNameOrLastName(firstName, lastName);
	}

    @Override
	public Doctor getDoctorFullNameById(long id) {
		return doctorRepository.findDoctorFullNameById(id);
    }
    
    @Override
	public List<Doctor> getDoctorInDescOrder() {
		return doctorRepository.findDoctorInDescOrder();
	}

    @Override
	public void deleteDoctor(long id) {
		doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Admin", "Id", id));
		doctorRepository.deleteById(id);
	}
}

