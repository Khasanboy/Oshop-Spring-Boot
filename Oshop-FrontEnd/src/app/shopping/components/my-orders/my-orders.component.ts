import { Component, OnInit } from '@angular/core';
import { OrderService } from '@shopping/services/order.service';
import { AuthService } from '@membership/services/auth.service';
import { Order } from '@shopping/models/order';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {
  orders: Order[] = [];

  constructor(
    private orderService: OrderService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.authService.getCurrentUser();
    if (this.authService.currentUser) {
      this.orderService
        .getOrdersByUserId(this.authService.currentUser.id)
        .subscribe(
          (data: Order[]) => {
            this.orders = data;
          },
          error => {
            console.log(error);
          }
        );
    }
  }
}
