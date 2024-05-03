package med.voll.api.domain.doctor.dto;

import med.voll.api.domain.address.dto.AddressDTO;

public record DoctorUpdateDTO(String name, String telephone, AddressDTO address) {
}
