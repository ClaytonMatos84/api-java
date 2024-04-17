package med.voll.api.model.doctor.dto;

import med.voll.api.model.address.dto.AddressDTO;

public record DoctorUpdateDTO(String name, String telephone, AddressDTO address) {
}
