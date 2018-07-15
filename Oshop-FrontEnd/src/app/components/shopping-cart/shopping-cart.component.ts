import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../../services/shopping-cart.service';
import { ShoppingCart } from '../../models/shopping-cart';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {
   cart: ShoppingCart = null;

  constructor(public shoppingCartService: ShoppingCartService) {}

  async ngOnInit() {
    if (this.shoppingCartService.cartExists()) {
       this.cart = await this.shoppingCartService.getCart();
    }
  }

   async clearCart() {
    await this.shoppingCartService.clearCart();
    console.log(this.shoppingCartService.currentCart.items);
  }
}
