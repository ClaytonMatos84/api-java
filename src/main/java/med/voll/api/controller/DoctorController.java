package med.voll.api.controller;

import med.voll.api.doctor.DoctorDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @GetMapping
    public String list() {
        return "Hello World";
    }

    @PostMapping
    public void insert(@RequestBody DoctorDTO doctorDTO) {
        System.out.println(doctorDTO);
    }

}
