package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorDTO;
import med.voll.api.doctor.DoctorOutputDTO;
import med.voll.api.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public DoctorOutputDTO insert(@RequestBody @Valid DoctorDTO doctorDTO) {
        Doctor savedDoctor = doctorRepository.save(new Doctor(doctorDTO));
        return new DoctorOutputDTO(savedDoctor);
    }

    @GetMapping
    public Page<DoctorOutputDTO> list(@PageableDefault(size = 12, sort = {"name"}) Pageable page) {
        return doctorRepository.findAll(page).map(DoctorOutputDTO::new);
    }

}
