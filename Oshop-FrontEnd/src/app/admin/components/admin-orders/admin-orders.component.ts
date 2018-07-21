import { Component, OnInit } from '@angular/core';
import { OrderService } from '@shopping/services/order.service';
import { Order } from '@shopping/models/order';

@Component({
  selector: 'app-admin-orders',
  templateUrl: './admin-orders.component.html',
  styleUrls: ['./admin-orders.component.css']
})
export class AdminOrdersComponent implements OnInit {

  orders: Order[] = [];

  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.orderService.getAllOrders().subscribe(
      (data: Order[]) => {
        this.orders = data;
      },
      error => {
        console.log(error);
      }
    );
  }

}
