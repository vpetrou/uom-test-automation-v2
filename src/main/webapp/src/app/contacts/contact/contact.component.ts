import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Contact} from "./contact.model";

import {Consts} from '../../utils/consts.util';
import {SelectItem} from 'primeng/api';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})

export class ContactComponent implements OnInit {

  cities: SelectItem[];
  status: SelectItem[];

  constructor(private http: HttpClient) {
  }

  loading: boolean;
  contact: Contact;

  ngOnInit() {
    this.http.get(Consts.API_URL + Consts.CONTACTS).subscribe((data: Contact) => {
      this.contact = data;
    });
  }

}
