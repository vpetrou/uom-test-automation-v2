package com.vpetrou.cs.controller;

import com.vpetrou.cs.exception.ResourceNotFoundException;
import com.vpetrou.cs.model.Contact;
import com.vpetrou.cs.repository.ContactRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST, RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1/")
@Api(value = "Contact Management System")
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    // get all contacts via rest api
    @ApiOperation(value = "View all contacts")
    @GetMapping("/contacts")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // create contact via rest api
    @ApiOperation(value = "Create a contact")
    @PostMapping("/contacts")
    public Contact createContact(
            @ApiParam(value = "Contact object to add in database table", required = true)
            @Valid @RequestBody Contact contact) {
        if (contactRepository.countByEmail(contact.getEmail()) == 0) {
            return contactRepository.save(contact);
        } else {
            return null;
        }
    }

    // get contact by id via rest api
    @ApiOperation(value = "View a contact")
    @GetMapping("/contacts/{id}")
    public ResponseEntity<Contact> getContactById(
            @ApiParam(value = "Contact id from which contact object will retrieve", required = true)
            @PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id :" + id));
        return ResponseEntity.ok(contact);
    }

    // update contact rest api
    @ApiOperation(value = "Update a contact")
    @RequestMapping(method = RequestMethod.POST, value = "/contacts/{id}")
    public Contact updateContact(
            @ApiParam(value = "Contact object to update in database table", required = true)
            @RequestBody Contact contactDetails) {
        Contact contact = contactRepository.findById(contactDetails.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id :" + contactDetails.getId()));
        contact.setName(contactDetails.getName());
        contact.setEmail(contactDetails.getEmail());
        contact.setPhone(contactDetails.getPhone());
        contactRepository.save(contact);
        return contact;
    }

    // delete contact via rest api
    @ApiOperation(value = "Delete a contact")
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteContact(
            @ApiParam(value = "Contact id from which contact object will retrieve", required = true)
            @PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not exist with id :" + id));
        contactRepository.delete(contact);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // delete all contacts via rest api
    @ApiOperation(value = "Delete all contacts")
    @DeleteMapping("/contacts/delete")
    public ResponseEntity<String> deleteAllContacts() {
        contactRepository.deleteAll();
        return new ResponseEntity<>("All Contacts Deleted", HttpStatus.OK);
    }

}
