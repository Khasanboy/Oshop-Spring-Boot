import { Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string;
  password: string;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  onLogin(){
    this.authService.login(this.email, this.password).subscribe(
      data => {
        console.log("Logged in");
        this.router.navigate["/"];
      },

      error => {
        console.log(error);
      }
    )

  }

}
