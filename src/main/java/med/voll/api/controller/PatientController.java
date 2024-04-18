package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.patient.dto.PatientDTO;
import med.voll.api.model.patient.dto.PatientOutputDTO;
import med.voll.api.model.patient.dto.PatientUpdateDTO;
import med.voll.api.model.patient.entity.Patient;
import med.voll.api.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
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

}
