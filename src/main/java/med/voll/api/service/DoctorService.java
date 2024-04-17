package med.voll.api.service;

import med.voll.api.model.doctor.entity.Doctor;
import med.voll.api.model.doctor.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor insert(Doctor doctor) {
        Doctor savedDoctor = doctorRepository.save(doctor);
        return savedDoctor;
    }

    public Page<Doctor> list(Pageable page) {
        return doctorRepository.findAllByActiveTrue(page);
    }

    public Doctor findById(Long doctorId) {
        return doctorRepository.findByIdAndActiveTrue(doctorId).orElse(null);
    }

    public Doctor partiallyUpdate(Long doctorId, Doctor updateDoctor) {
        Doctor doctorUpdated = doctorRepository.getReferenceById(doctorId);
        doctorUpdated.partiallyUpdate(updateDoctor);
        return doctorUpdated;
    }

    public void remove(Long doctorId) {
        Doctor removeDoctor = doctorRepository.getReferenceById(doctorId);
        removeDoctor.remove();
    }

}