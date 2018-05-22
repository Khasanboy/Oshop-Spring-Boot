import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }

  canActivate() {

    if (this.authService.currentUser) {
      console.log(this.authService.currentUser)
      return true;
    }
    else {
      console.log(this.authService.currentUser)
      this.router.navigate(['/login']);
      return false;
    }

  }
}
