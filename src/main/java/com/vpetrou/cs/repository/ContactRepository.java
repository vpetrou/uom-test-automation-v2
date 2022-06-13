package com.vpetrou.cs.repository;

import com.vpetrou.cs.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Long countByEmail(String email);

    Contact findByEmail(String email);

    List<Contact> findByNameIgnoreCaseContaining(String name);
}
