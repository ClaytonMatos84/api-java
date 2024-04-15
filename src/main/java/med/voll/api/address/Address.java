package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public void partiallyUpdate(AddressDTO address) {
        if (address.street() != null) this.street = address.street();
        if (address.neighborhood() != null) this.neighborhood = address.neighborhood();
        if (address.cep() != null) this.cep = address.cep();
        if (address.city() != null) this.city = address.city();
        if (address.uf() != null) this.uf = address.uf();
        if (address.number() != null) this.number = address.number();
        if (address.complement() != null) this.complement = address.complement();
    }
}
