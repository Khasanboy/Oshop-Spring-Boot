import { Component, OnInit } from '@angular/core';
import { ShoppingCartService } from '../../services/shopping-cart.service';
import { ShoppingCart } from '../../models/shopping-cart';
import { OrderService } from '../../services/order.service';

@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css']
})
export class CheckOutComponent implements OnInit {
  shipping = {};
  cart: ShoppingCart;

  constructor(private shoppingCartService: ShoppingCartService, private orderService: OrderService) {}

  async ngOnInit() {
    this.cart = await this.shoppingCartService.getCart();
  }

  checkout(value) {
    const order = {
      datePlaced: new Date().getTime(),
      shipping: this.shipping,
      items: this.cart.items

      /*this.cart.items.map(i => {
        return {
          product: {
            title: i.product.title,
            imageUrl: i.product.imgUrl,
            price: i.product.price
          },
          quantity: i.quantity,
          totalPrice: i.totalPrice
        };
      }); */
    };

    console.log(order);
    this.orderService.createOrder(order).subscribe(
      data => console.log(data),
      error => console.log(error)
    );

  }
}
