package com.edu.SpringBoot.HospitalManagementSystem.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edu.SpringBoot.HospitalManagementSystem.Entity.Patient;
import com.edu.SpringBoot.HospitalManagementSystem.Exception.ResourceNotFound;
import com.edu.SpringBoot.HospitalManagementSystem.Repository.PatientRepository;
import com.edu.SpringBoot.HospitalManagementSystem.Service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	private PatientRepository patientRepository;
	
	
	public PatientServiceImpl(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}


	@Override
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}


	@Override
	public List<Patient> getAllPatient() {
		return patientRepository.findAll();
		
	}


	@Override
	public Patient getPatientById(long id) {
		Optional<Patient> patient = patientRepository.findById(id);
		if(patient.isPresent()) {
			return patient.get();
		}
		else {
          
			throw new ResourceNotFound("Patient","Id",id);
		}
		
	}


	@Override
	public Patient updatePatient(Patient patient, long id) {
		Patient pnt = new Patient();
	 try {
		   pnt = patientRepository.findById(id).orElseThrow(
				 ()-> 		 new ResourceNotFound("Patient","Id",id));
	} catch (ResourceNotFound e) {
		
		e.printStackTrace();
	}
	 pnt.setFirstName(pnt.getFirstName());
	 pnt.setLastName(pnt.getLastName());
	 pnt.setEmail(pnt.getEmail());
	 pnt.setAddress(pnt.getAddress());
	 pnt.setGender(pnt.getGender());
	 pnt.setMobile_no(pnt.getMobile_no());
	 pnt.setDOB(pnt.getDOB());
	 pnt.setBlood_group(pnt.getBlood_Group());
	 pnt.setWeight(pnt.getWeight());
	 pnt.setPatient_Disease(pnt.getPatient_Disease());
	 
	patientRepository.save(pnt);
	return pnt;
	}
    
	@Override
	public List<Patient> getPatientByFirstName(String firstName) {
		return patientRepository.findByFirstName(firstName);
	}

    @Override
	public List<Patient> getPatientByLastName(String lastName) {
		return patientRepository.findByLastName(lastName);
	}

    @Override
	public List<Patient> getPatientByFirstNameAndLastName(String firstName, String lastName) {
		return patientRepository.findByFirstNameAndLastName(firstName, lastName);
	}
    
    @Override
	public List<Patient> getPatientByFirstNameOrLastName(String firstName, String lastName) {
		return patientRepository.findByFirstNameOrLastName(firstName, lastName);
	}

    @Override
	public Patient getPatientFullNameById(long id) {
		return patientRepository.findPatientFullNameById(id);
    }
    
    @Override
	public List<Patient> getPatientInDescOrder() {
		return patientRepository.findPatientInDescOrder();
	}

    @Override
	public void deletePatient(long id) {
		patientRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Admin", "Id", id));
		patientRepository.deleteById(id);
	}
}



	