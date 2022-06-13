package com.vpetrou.cs.integration;

import com.vpetrou.cs.integration.config.AppConfig;
import com.vpetrou.cs.model.Contact;
import com.vpetrou.cs.repository.ContactRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
//@DataJpaTest   //Instead of using @WebAppConfiguration and @ContextConfiguration for Spring Boot Applications
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactRepositoryIT {

    @Autowired
    ContactRepository contactRepository;

    @BeforeAll
    public void setup() {
        Contact contact = Contact.builder()
                .name("Contact Name 1")
                .email("email1@test.com")
                .phone("+3-2101111111")
                .build();
        contactRepository.save(contact);
        List<Contact> contactList = contactRepository.findAll();
        assertThat(contactList).extracting(Contact::getEmail).containsOnly("email1@test.com");
    }

    @Test
    public void when_searching_by_email_it_should_return_a_contact() {
        Contact newContact = contactRepository.findByEmail("email1@test.com");
        assertThat(newContact).isNotNull();
    }

    @Test
    public void when_retrieving_count_of_contacts_by_email_it_should_return_a_contact() {
        Long total = contactRepository.countByEmail("email1@test.com");
        assertThat(total).isEqualTo(1);
    }

    @Test
    public void when_searching_by_name_ignoring_case_it_should_return_a_contact() {
        List<Contact> contactListByNameIgnoringCase = contactRepository.findByNameIgnoreCaseContaining("contact name 1");
        assertThat(contactListByNameIgnoringCase.size()).isEqualTo(1);
    }

    @AfterAll
    public void tearDown() {
        contactRepository.deleteAll();
        assertThat(contactRepository.findAll()).isEmpty();
    }

}
