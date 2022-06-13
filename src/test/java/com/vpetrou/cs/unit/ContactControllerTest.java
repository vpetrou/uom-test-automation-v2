package com.vpetrou.cs.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.vpetrou.cs.controller.ContactController;
import com.vpetrou.cs.model.Contact;
import com.vpetrou.cs.repository.ContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ContactControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ContactController contactController;

    @Mock
    private ContactRepository contactRepository;

    ObjectWriter ow;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
        ow = new ObjectMapper().writer();
    }

    @Test
    public void when_creating_contact_with_all_fields_then_it_should_return_OK_200()
            throws Exception {
        Contact contact = Contact.builder()
                .name("Contact Name 1")
                .email("email1@test.com")
                .phone("+3-2101111111")
                .build();
        String jsonBody = ow.writeValueAsString(contact);

        when(contactRepository.countByEmail(contact.getEmail())).thenReturn(Long.valueOf(0));

        Mockito.when(contactRepository.save(contact)).thenReturn(contact);

        MvcResult result = this.mockMvc.perform(post("/api/v1/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(">>>>>>>>>>>> RESPONSE: " + result.getResponse().getContentAsString());
        Assertions.assertEquals(jsonBody, result.getResponse().getContentAsString());
    }

    @Test
    public void when_creating_contact_with_only_mandatory_fields_then_it_should_return_OK_200()
            throws Exception {
        Contact contact = Contact.builder()
                .name("Contact Name 1")
                .email("email1@test.com")
                .build();
        ObjectWriter ow = new ObjectMapper().writer();
        String jsonBody = ow.writeValueAsString(contact);

        when(contactRepository.countByEmail(contact.getEmail())).thenReturn(Long.valueOf(0));

        Mockito.when(contactRepository.save(contact)).thenReturn(contact);

        MvcResult result = this.mockMvc.perform(post("/api/v1/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(">>>>>>>>>>>> RESPONSE: " + result.getResponse().getContentAsString());
        Assertions.assertEquals(jsonBody, result.getResponse().getContentAsString());
    }

    @Test
    public void when_creating_contact_with_email_that_already_exist_then_it_should_return_OK_200_and_NULL_response_content()
            throws Exception {
        Contact contact = Contact.builder()
                .name("Contact Name 1")
                .email("email1@test.com")
                .phone("+3-2101111111")
                .build();
        ObjectWriter ow = new ObjectMapper().writer();
        String jsonBody = ow.writeValueAsString(contact);

        when(contactRepository.countByEmail(contact.getEmail())).thenReturn(Long.valueOf(1));

        Mockito.lenient().when(contactRepository.save(contact)).thenReturn(contact);

        MvcResult result = this.mockMvc.perform(post("/api/v1/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(">>>>>>>>>>>> RESPONSE: " + result.getResponse().getContentAsString());
        Assertions.assertEquals("", result.getResponse().getContentAsString());
    }

    @Test
    public void when_creating_contact_with_no_mandatory_field_name_then_it_should_return_BAD_REQUEST_400()
            throws Exception {
        Contact contact = Contact.builder()
                .email("email1@test.com")
                .phone("+3-2101111111")
                .build();
        ObjectWriter ow = new ObjectMapper().writer();
        String jsonBody = ow.writeValueAsString(contact);

        this.mockMvc.perform(post("/api/v1/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void when_creating_contact_with_no_mandatory_field_email_then_it_should_return_BAD_REQUEST_400()
            throws Exception {
        Contact contact = Contact.builder()
                .name("Contact Name 1")
                .phone("+3-2101111111")
                .build();
        ObjectWriter ow = new ObjectMapper().writer();
        String jsonBody = ow.writeValueAsString(contact);

        this.mockMvc.perform(post("/api/v1/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
