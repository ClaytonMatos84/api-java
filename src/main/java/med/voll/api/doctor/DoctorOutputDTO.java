package med.voll.api.doctor;

public record DoctorOutputDTO(Long id, String name, String email, String crm, Specialty specialty) {

    public DoctorOutputDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
