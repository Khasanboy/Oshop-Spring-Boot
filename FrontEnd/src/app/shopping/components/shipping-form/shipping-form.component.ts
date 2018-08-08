import { Component, OnInit, Input } from '@angular/core';
import { OrderService } from '@shopping/services/order.service';
import { Router } from '@angular/router';
import { AuthService } from '@membership/services/auth.service';
import { ShoppingCart } from '@shopping/models/shopping-cart';
import { Shipping } from '@shopping/models/shipping';
import { Order } from '@shopping/models/order';

@Component({
  selector: 'app-shipping-form',
  templateUrl: './shipping-form.component.html',
  styleUrls: ['./shipping-form.component.css']
})
export class ShippingFormComponent implements OnInit {
  @Input() cart: ShoppingCart;

  shipping: Shipping = new Shipping();
  constructor(
    private authService: AuthService,
    private orderService: OrderService,
    private router: Router
  ) {}

  ngOnInit() {}

  checkout() {
    if (this.authService.currentUser) {
      const order = new Order(
        this.authService.currentUser.id,
        this.shipping,
        this.cart
      );
      this.orderService.createOrder(order).subscribe(
        (data: Order) => {
          this.router.navigate(['/order-success', data.id]);
        },
        error => {
          console.log(error);
        }
      );
    } else {
      this.authService.getCurrentUser().subscribe(
        user => {
          const order = new Order(
            this.authService.currentUser.id,
            this.shipping,
            this.cart
          );
          this.orderService.createOrder(order).subscribe(
            (data: Order) => {
              this.router.navigate(['/order-success', data.id]);
            },
            error => {
              console.log(error);
            }
          );
        },
        error => {
          console.log(error);
        }
      );
    }
  }
}
