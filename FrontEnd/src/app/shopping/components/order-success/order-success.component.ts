import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '../../../../../node_modules/@angular/router';
import { ShoppingCartItem } from '@shopping/models/shopping-cart-item';
import { Order } from '@shopping/models/order';
import { OrderService } from '@shopping/services/order.service';

@Component({
  selector: 'app-order-success',
  templateUrl: './order-success.component.html',
  styleUrls: ['./order-success.component.css']
})
export class OrderSuccessComponent implements OnInit {

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
