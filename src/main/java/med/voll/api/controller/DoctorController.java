package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
        return doctorRepository.findAllByActiveTrue(page).map(DoctorOutputDTO::new);
    }

    @PatchMapping("/{id}")
    @Transactional
    public DoctorOutputDTO partiallyUpdate(@PathVariable Long id, @RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.partiallyUpdate(doctorUpdateDTO);
        return new DoctorOutputDTO(doctor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void remove(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.remove();
    }

}
