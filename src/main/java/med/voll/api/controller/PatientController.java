package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.model.patient.dto.PatientDTO;
import med.voll.api.model.patient.dto.PatientOutputDTO;
import med.voll.api.model.patient.entity.Patient;
import med.voll.api.service.PatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
