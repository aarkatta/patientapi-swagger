package com.open.patient.patientapi.controller;

import com.open.patient.patientapi.model.Patient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Tag(name="patient",description = "the patient API")
@RequestMapping("/api/v1/patients")
public interface PatientApi {
    @Operation(summary = "Find Patient by ID", description = "Returns a single patient", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Patient.class))),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Patient not found", content = @Content)
    })
    @RequestMapping(value = "/{id}", produces = {"application/json", "application/vnd.api+json"}, method = RequestMethod.GET)
    public ResponseEntity<Patient> findById(
            @Parameter(description = "ID of the patient", required = true)
            @PathVariable long id,
            @NotNull @Parameter(description = "select which kind of data to fetch", required = true)
            @Valid @RequestHeader(value = "patientAuthorization", required = true) String patientAuthorization
    ) throws Exception;

    @Operation(summary = "Get List of Patients", description = "Returns a Patient Collection", tags = {"patients"})
    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Patient> findPatients();

    @PutMapping("/id{}")
    @ResponseStatus(HttpStatus.OK)
    public Patient updatePatient(@PathVariable("id") final String id, @RequestBody final Patient patient);

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient patchPatient(@PathVariable("id") final String id, @RequestBody final Patient patient);

    @RequestMapping(method = RequestMethod.HEAD, value="/")
    @ResponseStatus(HttpStatus.OK)
    public Patient headPatient();
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public long deletePatient(@PathVariable final long id);

    @Operation(summary = "Create Patient Record", description = "This can only be done by the logged in Patient", tags = {"patient"})
    @ApiResponses(value = {
            @ApiResponse(description = "successful operation", content = {@Content(mediaType = "application/json", schema =  @Schema(implementation = Patient.class)), @Content(mediaType = "application/xml", schema = @Schema(implementation = Patient.class))})
    })
    @PostMapping(value="/", consumes = {"application/json", "application/xml", "application/x-www-form-urlencoded"})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Patient> postPatient(
            @NotNull
            @Parameter(description = "Created patient object", required = true)
            @Valid @RequestBody Patient body,
            @NotNull @Parameter(description = "select whichkind of data to fetch", required = true)
            @Valid @RequestHeader(value = "patientAuthorization", required = true) String patientAuthorization
    ) throws Exception;

}
