package med.voll.api.domain.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.dto.AddressDTO;
import org.hibernate.validator.constraints.br.CPF;

public record PatientDTO(@NotBlank String name,
                         @NotBlank @Email String email,
                         @NotBlank @Pattern(regexp = "\\d{10,11}") String telephone,
                         @NotBlank @CPF String cpf,
                         @NotNull @Valid AddressDTO address) {
}
