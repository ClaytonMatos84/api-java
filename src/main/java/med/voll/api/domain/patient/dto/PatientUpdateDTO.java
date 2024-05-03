package med.voll.api.domain.patient.dto;

import med.voll.api.domain.address.dto.AddressDTO;

public record PatientUpdateDTO(String name, String telephone, AddressDTO address) {
}
