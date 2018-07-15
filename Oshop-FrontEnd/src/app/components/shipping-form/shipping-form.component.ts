import { Component, OnInit, Input } from '@angular/core';
import { OrderService } from '../../services/order.service';
import { Router } from '../../../../node_modules/@angular/router';
import { Order } from '../../models/order';
import { Shipping } from '../../models/shipping';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-shipping-form',
  templateUrl: './shipping-form.component.html',
  styleUrls: ['./shipping-form.component.css']
})
export class ShippingFormComponent implements OnInit {
  @Input() cart;

  user: User;

  shipping: Shipping = new Shipping();
  constructor(
    private authService: AuthService,
    private orderService: OrderService,
    private router: Router) { }

  ngOnInit() {
    this.user = this.authService.currentUser;
  }

  checkout() {
    const order = new Order(this.user.id, this.shipping, this.cart);
    this.orderService.createOrder(order).subscribe(
      (data: Order) => {
        console.log(data);
        this.router.navigate(['/order-success', data.id]);
      },
      error => {
        console.log(error);
      }
    );
  }

}
