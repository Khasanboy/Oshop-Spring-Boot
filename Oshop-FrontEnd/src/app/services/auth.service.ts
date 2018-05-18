import { User } from './../models/user';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  user: User;

  constructor(private http: HttpClient) { }

  register(user: User) {

    const headers: HttpHeaders = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    return this.http.post('api/auth/signup', user, { headers: headers });
  }

  login(email: string, password: string){

    const headers: HttpHeaders = new HttpHeaders();
    headers.append('Content-Type', 'application/json');

    const body = {email: email, password: password};

    console.log("Hello bitch")

    return this.http.post('api/auth/signin', body, { headers: headers });

  }

}
