package med.voll.api.address;

public record AddressDTO(String street, String neighborhood, String cep, String city, String uf,
                         String number, String complement) {
}
