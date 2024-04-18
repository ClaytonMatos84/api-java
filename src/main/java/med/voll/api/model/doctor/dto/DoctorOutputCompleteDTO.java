package med.voll.api.model.doctor.dto;

import med.voll.api.model.address.dto.AddressDTO;
import med.voll.api.model.address.entity.Address;
import med.voll.api.model.doctor.entity.Doctor;
import med.voll.api.model.doctor.entity.Specialty;

public record DoctorOutputCompleteDTO(Long id, String name, String email, String telephone, String crm, Specialty specialty, Address address) {

    public DoctorOutputCompleteDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getTelephone(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress());
    }
}
