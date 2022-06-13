import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import {Contact} from "../contact/contact.model";

import { Consts } from '../../utils/consts.util';
import {SelectItem} from 'primeng/api';

@Component({
  selector: 'app-contact-create',
  templateUrl: './contact-create.component.html',
  styleUrls: ['./contact-create.component.css']
})
export class ContactCreateComponent implements OnInit {

  loggedInUser: String;

  contact : Contact;

  emailAlreadyExists: Boolean = false;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
    this.contact = new Contact();
    this.emailAlreadyExists = false;
  }

  saveContact() {
    this.http.post(Consts.API_URL + Consts.CONTACTS, this.contact)
      .subscribe(res => {
          if(res==null) {
            this.emailAlreadyExists = true;
          } else {
            this.router.navigate(['/contact']);
          }
        }, (err) => {
          console.log(err);
        }
      );
  }

}
