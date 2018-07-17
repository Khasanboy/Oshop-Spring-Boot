import { Component, OnInit } from '@angular/core';
import { Order } from '@shared/models/order';
import { OrderService } from '@shared/services/order.service';
import { ActivatedRoute } from '../../../../node_modules/@angular/router';
import { ShoppingCartItem } from '@shared/models/shopping-cart-item';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  order: Order;
  orderId: number;
  totalPrice: number;

  constructor(private orderService: OrderService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.orderId = this.route.snapshot.params['id'];
    this.orderService.getOrderById(this.orderId).subscribe(
      (data: Order) => {
        this.order =  data;
        this.totalPrice = 0;
        data['items'].forEach((item: ShoppingCartItem) => {
          this.totalPrice += item.quantity * item.product.price;
        });
      },
      error => {
        console.log(error);
      }
    );
  }

}
