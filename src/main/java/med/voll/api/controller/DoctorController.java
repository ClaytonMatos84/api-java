package med.voll.api.controller;

import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorDTO;
import med.voll.api.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public List<Doctor> list() {
        return doctorRepository.findAll();
    }

    @PostMapping
    public void insert(@RequestBody DoctorDTO doctorDTO) {
        doctorRepository.save(new Doctor(doctorDTO));
    }

}
