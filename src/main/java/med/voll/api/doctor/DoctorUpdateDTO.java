package med.voll.api.doctor;

import med.voll.api.address.AddressDTO;

public record DoctorUpdateDTO(String name, String telephone, AddressDTO address) {
}
