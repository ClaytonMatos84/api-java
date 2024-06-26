package med.voll.api.service;

import med.voll.api.domain.patient.entity.Patient;
import med.voll.api.domain.patient.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<Patient> list(Pageable page) {
        return patientRepository.findAllByActiveTrue(page);
    }

    @Transactional
    public Patient partiallyUpdate(Long patientId, Patient updatePatient) {
        Patient patientUpdated = patientRepository.getReferenceById(patientId);
        patientUpdated.partiallyUpdate(updatePatient);
        return patientUpdated;
    }

    @Transactional
    public void remove(Long patientId) {
        Patient patient = patientRepository.getReferenceById(patientId);
        patient.remove();
    }

}
