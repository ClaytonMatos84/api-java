package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.doctor.dto.DoctorDTO;
import med.voll.api.model.doctor.dto.DoctorOutputDTO;
import med.voll.api.model.doctor.dto.DoctorUpdateDTO;
import med.voll.api.model.doctor.entity.Doctor;
import med.voll.api.service.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @Transactional
    public DoctorOutputDTO insert(@RequestBody @Valid DoctorDTO doctorDTO) {
        Doctor savedDoctor = doctorService.insert(new Doctor(doctorDTO));
        return new DoctorOutputDTO(savedDoctor);
    }

    @GetMapping
    public Page<DoctorOutputDTO> list(@PageableDefault(size = 12, sort = {"name"}) Pageable page) {
        Page<Doctor> doctorPage = doctorService.list(page);
        return doctorPage.map(DoctorOutputDTO::new);
    }

    @GetMapping("/{id}")
    public DoctorOutputDTO findById(@PathVariable Long id) {
        Doctor doctor = doctorService.findById(id);
        return doctor != null ? new DoctorOutputDTO(doctor) : null;
    }

    @PatchMapping("/{id}")
    @Transactional
    public DoctorOutputDTO partiallyUpdate(@PathVariable Long id, @RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        Doctor updateDoctor = doctorService.partiallyUpdate(id, Doctor.parseDoctor(doctorUpdateDTO));
        return new DoctorOutputDTO(updateDoctor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void remove(@PathVariable Long id) {
        doctorService.remove(id);
    }

}
