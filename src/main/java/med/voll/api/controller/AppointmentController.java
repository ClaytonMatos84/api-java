package med.voll.api.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import med.voll.api.domain.appointment.dto.AppointmentCancelDTO;
import med.voll.api.domain.appointment.dto.AppointmentSchedulingDTO;
import med.voll.api.domain.appointment.dto.AppointmentSchedullingOutputDTO;
import med.voll.api.domain.appointment.entity.Appointment;
import med.voll.api.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity schedule(@RequestBody @Valid AppointmentSchedulingDTO appointmentSchedulingDTO) {
        Appointment appointment = appointmentService.scheduler(appointmentSchedulingDTO.idDoctor(), appointmentSchedulingDTO.idPatient(),
                appointmentSchedulingDTO.date(), appointmentSchedulingDTO.specialty());
        return ResponseEntity.ok(new AppointmentSchedullingOutputDTO(appointment.getId(), appointment.getDoctor().getId(),
                appointment.getPatient().getId(), appointment.getDate(), appointment.getDoctor().getSpecialty()));
    }

    @DeleteMapping
    public ResponseEntity cancel(@RequestBody @Valid AppointmentCancelDTO appointmentCancelDTO) {
        appointmentService.cancel(appointmentCancelDTO.idAppointment(), appointmentCancelDTO.reason());
        return ResponseEntity.noContent().build();
    }

}
