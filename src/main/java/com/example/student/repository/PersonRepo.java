package com.example.student.repository;
import com.example.student.entity.PersonRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PersonRepo extends JpaRepository<PersonRecord, Long> {
    Page<PersonRecord> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<PersonRecord> findByGroupNameContainingIgnoreCase(String groupName, Pageable pageable);
}
