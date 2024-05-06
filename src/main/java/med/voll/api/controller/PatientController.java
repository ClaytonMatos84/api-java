package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import med.voll.api.domain.patient.dto.PatientDTO;
import med.voll.api.domain.patient.dto.PatientOutputDTO;
import med.voll.api.domain.patient.dto.PatientUpdateDTO;
import med.voll.api.domain.patient.entity.Patient;
import med.voll.api.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientOutputDTO insert(@RequestBody @Valid PatientDTO patientDTO) {
        Patient savedPatient = patientService.insert(new Patient(patientDTO));
        return new PatientOutputDTO(savedPatient);
    }

    @GetMapping
    public Page<PatientOutputDTO> list(@PageableDefault(size = 10, sort = {"name"}) Pageable page) {
        Page<Patient> patientPage = patientService.list(page);
        return patientPage.map(PatientOutputDTO::new);
    }

    @PatchMapping("/{id}")
    public PatientOutputDTO partiallyUpdate(@PathVariable Long id, @RequestBody @Valid PatientUpdateDTO patientUpdateDTO) {
        Patient updatePatient = patientService.partiallyUpdate(id, Patient.parsePatient(patientUpdateDTO));
        return new PatientOutputDTO(updatePatient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        patientService.remove(id);
    }

}
