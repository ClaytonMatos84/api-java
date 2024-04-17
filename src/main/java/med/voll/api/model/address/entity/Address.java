package med.voll.api.model.address.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.model.address.dto.AddressDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String neighborhood;
    private String cep;
    private String city;
    private String uf;
    private String number;
    private String complement;

    public Address(AddressDTO address) {
        this.street = address.street();
        this.neighborhood = address.neighborhood();
        this.cep = address.cep();
        this.city = address.city();
        this.uf = address.uf();
        this.number = address.number();
        this.complement = address.complement();
    }

    public void partiallyUpdate(Address address) {
        if (address.getStreet() != null) this.street = address.getStreet();
        if (address.getNeighborhood() != null) this.neighborhood = address.getNeighborhood();
        if (address.getCep() != null) this.cep = address.getCep();
        if (address.getCity() != null) this.city = address.getCity();
        if (address.getUf() != null) this.uf = address.getUf();
        if (address.getNumber() != null) this.number = address.getNumber();
        if (address.getComplement() != null) this.complement = address.getComplement();
    }
}
