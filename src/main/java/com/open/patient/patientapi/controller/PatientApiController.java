package com.open.patient.patientapi.controller;

import com.open.patient.patientapi.Exception.PatientNotFoundException;
import com.open.patient.patientapi.model.Patient;
import com.open.patient.patientapi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class PatientApiController implements PatientApi {

    @Autowired
    private PatientRepository repository;
    @Override
    public ResponseEntity<Patient> findById(long id, String patientAuthorization) throws Exception {
        Patient patient = repository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient not foundfor this id:: "+id));
        return ResponseEntity.ok().body(patient);
    }

    @Override
    public Collection<Patient> findPatients() {
        return repository.findAll();
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient updatePatient(@PathVariable("id") final String id, @RequestBody final Patient patient) {
        return patient;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient patchPatient(@PathVariable("id") final String id, @RequestBody final Patient patient) {
        return patient;
    }

    @RequestMapping(method = RequestMethod.HEAD, value = "/")
    @ResponseStatus(HttpStatus.OK)
    public Patient headPatient() {
        return new Patient();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public long deletePatient(@PathVariable final long id) {
        return id;
    }

    @Override
    public ResponseEntity<Patient> postPatient(
            Patient body,
            String patientAuthorization) throws Exception {
        return new ResponseEntity<Patient>(repository.save(body), HttpStatus.CREATED);
    }
}
