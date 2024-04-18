package med.voll.api.model.patient.dto;

import med.voll.api.model.address.dto.AddressDTO;

public record PatientUpdateDTO(String name, String telephone, AddressDTO address) {
}
