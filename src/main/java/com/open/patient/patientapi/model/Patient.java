package com.open.patient.patientapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table (name = "patients")
public class Patient {
    @JsonProperty(value = "id", required = true, index = 10)
    @Schema(description = "Unique identifier of the patient", example = "1", required = true)
    private long id;
    @JsonProperty(value = "fName", required = true, index = 20)
    @Schema(description = "First Name of the patient", example = "John", required = true)
    @NotBlank
    @Size(min=0, max=20)
    private String fName;
    @JsonProperty(value = "lName", required = true,index = 30)
    @Schema(description = "Last Name of the patient", example = "Doe", required = true)
    @NotBlank
    @Size(min = 0, max=30)
    private String lName;

    public Patient() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "fName", nullable = false)
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    @Column(name = "lName", nullable = false)
    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
