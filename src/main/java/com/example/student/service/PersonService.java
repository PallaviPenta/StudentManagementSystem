package com.example.student.service;
import com.example.student.entity.PersonRecord;
import com.example.student.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import java.util.List;
@Service @Validated
public class PersonService {
    @Autowired private PersonRepo personRepo;
    public PersonRecord addPerson(PersonRecord person) { return personRepo.save(person); }
    public PersonRecord updatePerson(Long id, PersonRecord person) { person.setId(id); return personRepo.save(person); }
    public void deletePerson(Long id) { personRepo.deleteById(id); }
    public Page<PersonRecord> getAllPersons(int page, int size) { return personRepo.findAll(PageRequest.of(page, size)); }
    public Page<PersonRecord> searchByName(String name, int page, int size) { return personRepo.findByNameContainingIgnoreCase(name, PageRequest.of(page, size)); }
    public Page<PersonRecord> searchByGroup(String groupName, int page, int size) { return personRepo.findByGroupNameContainingIgnoreCase(groupName, PageRequest.of(page, size)); }
    public PersonRecord getPersonById(Long id) { return personRepo.findById(id).orElseThrow(() -> new RuntimeException("Person not found")); }
}
