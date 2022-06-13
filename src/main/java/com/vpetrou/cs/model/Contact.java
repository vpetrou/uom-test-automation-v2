package com.vpetrou.cs.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Entity
@Table(name = "contacts")
@ApiModel(description = "All details about the Contact.")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated contact ID", hidden = true)
    private long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 1, max = 90)
    @Column(name = "name")
    @ApiModelProperty(notes = "The contact's name", required = true)
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Size(min = 1, max = 90)
    @Column(name = "email")
    @ApiModelProperty(notes = "The contact's email (must be unique)", required = true, allowableValues = "mustBeUnique@mail.com")
    String email;

    @Size(min = 0, max = 30)
    @Column(name = "phone")
    @ApiModelProperty(notes = "The contact's phone")
    String phone;

}
