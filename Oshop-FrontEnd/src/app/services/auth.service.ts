import { Router } from '@angular/router';
import { User } from './../models/user';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  currentUser: any;

  private _registerUrl = 'api/auth/signup';
  private _loginUrl = 'api/auth/signin';

  constructor(private http: HttpClient, private router: Router) {
  }

  register(user: User) {
    return this.http.post(this._registerUrl, user);
  }

  login(body: any) {
    return this.http.post(this._loginUrl, body);
  }

  loggedIn() {
    return !!localStorage.getItem('accessToken');
  }

  getCurrentUser() {
    this.http.get("api/user/me").subscribe(
      data => {
        this.currentUser = data;
      },
      error =>{
        console.log(error);
        this.currentUser = null;
      }
    )
  }

  getToken() {
    return localStorage.getItem('accessToken');
  }

  saveToken(token: string, tokenType: string) {
    localStorage.setItem("accessToken", token);
  }


  logout() {
    localStorage.removeItem("accessToken");
    this.currentUser = null;
    this.router.navigate(['/']);
  }

}
