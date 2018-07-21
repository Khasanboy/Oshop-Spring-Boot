import { Component, OnInit } from '@angular/core';
import { AuthService } from '@membership/services/auth.service';
import { ShoppingCartService } from '@shopping/services/shopping-cart.service';
import { FlashMessagesService } from 'angular2-flash-messages';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  shoppingCartItemsCount: number;

  constructor(
    public authService: AuthService,
    public shoppingCartService: ShoppingCartService,
    private flashMessages: FlashMessagesService
  ) {}

  async ngOnInit() {}

  isAdmin() {
    if (this.authService.currentUser) {
      if (
        this.authService.currentUser.roles.find(
          role => role.authority === 'ROLE_ADMIN'
        )
      ) {
        return true;
      } else {
        return false;
      }
    }
  }

  logout() {
    this.authService.logout();
    this.flashMessages.show('Logged out', {
      cssClass: 'alert-success',
      timeout: 2000
    });
  }
}
