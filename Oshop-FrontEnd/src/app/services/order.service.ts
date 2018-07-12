import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private readonly url = '/api/orders/';

  constructor(private http: HttpClient) { }

  createOrder(order) {
    return this.http.post(this.url, order);
  }
}
