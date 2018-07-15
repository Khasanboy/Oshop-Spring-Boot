import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../../services/shopping-cart.service';
import { ShoppingCart } from '../../models/shopping-cart';
import { OrderService } from '../../services/order.service';
import { Order } from '../../models/order';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user';
import { Shipping } from '../../models/shipping';

@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css']
})
export class CheckOutComponent implements OnInit {
  shipping: Shipping = new Shipping();
  user: User;
  cart: ShoppingCart;

  constructor(private authService: AuthService, private shoppingCartService: ShoppingCartService, private orderService: OrderService) {}

  async ngOnInit() {
    this.cart = await this.shoppingCartService.getCart();
    this.user = this.authService.currentUser;
  }

  checkout() {
    const order = new Order(this.user.id, this.shipping, this.cart);
    console.log(order);

    this.orderService.createOrder(order).subscribe(
      data => console.log(data),
      error => console.log(error)
    );


  }
}
