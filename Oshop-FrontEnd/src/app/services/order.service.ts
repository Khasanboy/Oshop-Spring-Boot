import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ShoppingCartService } from './shopping-cart.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private readonly url = '/api/orders/';

  constructor(private shoppingCartService: ShoppingCartService, private http: HttpClient) { }

  createOrder(order) {
    return this.http.post(this.url, order).do(
      () => this.call()
    );
  }

  private async call() {
    await this.shoppingCartService.clearCart();
  }
}
