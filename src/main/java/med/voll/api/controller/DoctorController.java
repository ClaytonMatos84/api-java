package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.doctor.dto.DoctorDTO;
import med.voll.api.model.doctor.dto.DoctorOutputCompleteDTO;
import med.voll.api.model.doctor.dto.DoctorOutputDTO;
import med.voll.api.model.doctor.dto.DoctorUpdateDTO;
import med.voll.api.model.doctor.entity.Doctor;
import med.voll.api.service.DoctorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity insert(@RequestBody @Valid DoctorDTO doctorDTO, UriComponentsBuilder uriBuilder) {
        Doctor savedDoctor = doctorService.insert(new Doctor(doctorDTO));
        URI uri = uriBuilder.path("/doctors/{id}").buildAndExpand(savedDoctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorOutputCompleteDTO(savedDoctor));
    }

    @GetMapping
    public Page<DoctorOutputDTO> list(@PageableDefault(size = 12, sort = {"name"}) Pageable page) {
        Page<Doctor> doctorPage = doctorService.list(page);
        return doctorPage.map(DoctorOutputDTO::new);
    }

    @GetMapping("/{id}")
    public DoctorOutputCompleteDTO findById(@PathVariable Long id) {
        Doctor doctor = doctorService.findById(id);
        return new DoctorOutputCompleteDTO(doctor);
    }

    @PatchMapping("/{id}")
    public DoctorOutputCompleteDTO partiallyUpdate(@PathVariable Long id, @RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        Doctor updateDoctor = doctorService.partiallyUpdate(id, Doctor.parseDoctor(doctorUpdateDTO));
        return new DoctorOutputCompleteDTO(updateDoctor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        doctorService.remove(id);
    }

}
