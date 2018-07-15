import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../../services/shopping-cart.service';
import { ShoppingCart } from '../../models/shopping-cart';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
   cart: ShoppingCart = null;

  constructor(public shoppingCartService: ShoppingCartService, private router: Router, private authService: AuthService) {}

  async ngOnInit() {
    if (this.shoppingCartService.cartExists()) {
       this.cart = await this.shoppingCartService.getCart();
    }
  }

   async clearCart() {
    await this.shoppingCartService.clearCart();
  }

  checkout() {
    if (this.authService.currentUser) {
      this.router.navigate(['/checkout']);
    } else {
      this.router.navigate(['/login', { queryParams: { returnUrl: '/checkout' } }]);
    }
  }
}
