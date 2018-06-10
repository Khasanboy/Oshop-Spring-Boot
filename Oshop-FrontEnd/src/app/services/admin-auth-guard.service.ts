import { FlashMessagesService } from 'angular2-flash-messages';
import { AuthService } from './auth.service';
import { CanActivate, Router } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthGuardService implements CanActivate {

  constructor(private authService: AuthService, private router: Router, private flashMessages: FlashMessagesService) { }

  canActivate() {

    if (this.authService.loggedIn()) {

      if (this.authService.currentUser.roles.find(role => role.authority === 'ROLE_ADMIN')) {

        return true;

      }
    }



    this.router.navigate(['/']);
    this.flashMessages.show('You don\'t have creadentials for this page', { cssClass: 'alert-danger', timeout: 3000 });
    return false;
  }

}
