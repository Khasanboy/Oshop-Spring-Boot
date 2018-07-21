import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '@shopping/services/shopping-cart.service';
import { Router } from '@angular/router';
import { AuthService } from '@membership/services/auth.service';
import { ShoppingCart } from '@shopping/models/shopping-cart';

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
      this.router.navigate(['/check-out']);
  }
}