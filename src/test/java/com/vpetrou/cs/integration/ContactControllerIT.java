package com.vpetrou.cs.integration;

import com.vpetrou.cs.controller.ContactController;
import com.vpetrou.cs.exception.ResourceNotFoundException;
import com.vpetrou.cs.integration.config.AppConfig;
import com.vpetrou.cs.model.Contact;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
//@SpringBootTest   //Instead of using @WebAppConfiguration and @ContextConfiguration for Spring Boot Applications
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactControllerIT {

    @Autowired
    ContactController contactController;

    private Contact newContact1;
    private Contact newContact2;

    @BeforeAll
    public void setup() {
        Contact contact1 = Contact.builder()
                .name("Contact Name 1")
                .email("email1@test.com")
                .phone("+3-2101111111")
                .build();
        Contact contact2 = Contact.builder()
                .name("Contact Name 2")
                .email("email2@test.com")
                .phone("+3-2102222222")
                .build();
        newContact1 = contactController.createContact(contact1);
        newContact2 = contactController.createContact(contact2);
    }

    @Test()
    @Order(1)
    public void when_getting_all_contacts_then_it_should_return_all_contacts() {
        List<Contact> contactList = contactController.getAllContacts();

        RecursiveComparisonConfiguration configuration = RecursiveComparisonConfiguration.builder()
                .withIgnoredFields("id")
                .build();

        assertThat(contactList)
                .usingRecursiveFieldByFieldElementComparator(configuration)
                .contains(newContact1)
                .contains(newContact2);
    }

    @Test
    @Order(1)
    public void when_getting_a_contact_by_id_then_contact_should_be_retrieved() {
        Contact contact = contactController.getContactById(newContact1.getId()).getBody();
        assertThat(contact).hasFieldOrPropertyWithValue("email", "email1@test.com");
    }

    @Test
    @Order(1)
    public void when_getting_a_contact_by_non_existent_id_then_no_contact_should_be_retrieved() {
        ResourceNotFoundException thrown =
                assertThrows(ResourceNotFoundException.class, () ->
                        contactController.getContactById(Long.parseLong("777")));
        assertThat(thrown.getMessage()).isEqualTo("Contact not exist with id :777");
    }

    @Test
    @Order(1)
    public void when_updating_a_contact_by_id_then_contact_should_be_retrieved() {
        Contact contact = contactController.getContactById(newContact1.getId()).getBody();
        assertThat(contact).hasFieldOrPropertyWithValue("phone", "+3-2101111111");

        Contact updatedContact = Contact.builder()
                .id(newContact1.getId())
                .name("Contact Name 1")
                .email("email1@test.com")
                .phone("+3-2101212121")
                .build();
        contactController.updateContact(updatedContact);

        contact = contactController.getContactById(newContact1.getId()).getBody();
        assertThat(contact).hasFieldOrPropertyWithValue("phone", "+3-2101212121");
    }

    @Test
    @Order(1)
    public void when_updating_a_contact_by_non_existent_id_then_no_contact_should_be_updated() {
        Contact updatedContact = Contact.builder()
                .id(777)
                .name("Contact Name 1")
                .email("email1@test.com")
                .phone("+3-2101212121")
                .build();
        ResourceNotFoundException thrown =
                assertThrows(ResourceNotFoundException.class, () ->
                        contactController.updateContact(updatedContact));
        assertThat(thrown.getMessage()).isEqualTo("Contact not exist with id :777");
    }

    @Test
    @Order(2)
    public void when_deleting_a_contact_by_id_then_contact_should_be_deleted() {
        assertThat(contactController.getAllContacts()).hasSize(2);
        contactController.deleteContact(newContact1.getId());
        assertThat(contactController.getAllContacts()).hasSize(1);
    }

    @Test
    @Order(2)
    public void when_deleting_a_contact_by_non_existent_id_then_contact_should_be_deleted() {
        ResourceNotFoundException thrown =
                assertThrows(ResourceNotFoundException.class, () ->
                        contactController.deleteContact(Long.parseLong("777")));
        assertThat(thrown.getMessage()).isEqualTo("Contact not exist with id :777");
    }

    @Test
    @Order(3)
    public void when_deleting_all_contacts_then_no_contact_should_exist() {
        contactController.deleteAllContacts();
        assertThat(contactController.getAllContacts()).isEmpty();
    }

    @AfterAll
    public void tearDown() {
        contactController.deleteAllContacts();
        SoftAssertions.assertSoftly(softly -> assertThat(contactController.getAllContacts()).isEmpty());
    }

}
