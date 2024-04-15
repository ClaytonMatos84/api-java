package med.voll.api.doctor;

public record DoctorOutputDTO(String name, String email, String crm, Specialty specialty) {

    public DoctorOutputDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
