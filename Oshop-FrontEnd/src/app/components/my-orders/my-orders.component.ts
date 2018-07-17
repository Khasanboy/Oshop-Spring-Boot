import { Component, OnInit } from '@angular/core';
import { Order } from '@shared/models/order';
import { OrderService } from '@shared/services/order.service';
import { AuthService } from '@shared/services/auth.service';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css']
})
export class MyOrdersComponent implements OnInit {

  orders: Order[] = [];

  constructor(private orderService: OrderService, private authService: AuthService) { }

  ngOnInit() {
    this.authService.getCurrentUser();
    if (this.authService.currentUser) {
    this.orderService.getOrdersByUserId(this.authService.currentUser.id).subscribe(
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
