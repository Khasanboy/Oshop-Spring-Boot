import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from '@shared/models/user';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {

  }


}
