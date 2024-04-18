package med.voll.api.service;

import med.voll.api.model.patient.entity.Patient;
import med.voll.api.model.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public Patient insert(Patient patient) {
        Patient savedPatient = patientRepository.save(patient);
        return savedPatient;
    }
}
