import { FlashMessagesService } from 'angular2-flash-messages';
import { AuthService } from '@membership/services/auth.service';
import { CanActivate, Router } from '@angular/router';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthGuardService implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router,
    private flashMessages: FlashMessagesService,
    private jwtHelper: JwtHelperService
  ) {}

  canActivate() {
    if (this.authService.loggedIn()) {
      const token = this.authService.getToken();
      const decodedToken = this.jwtHelper.decodeToken(token);
      if (
        decodedToken.userRoles.find(
          role => role.authority === 'ROLE_ADMIN'
        )
      ) {
        return true;
      }
    }

    this.router.navigate(['/']);
    this.flashMessages.show('You don\'t have creadentials for this page', {
      cssClass: 'alert-danger',
      timeout: 3000
    });
    return false;
  }
}
