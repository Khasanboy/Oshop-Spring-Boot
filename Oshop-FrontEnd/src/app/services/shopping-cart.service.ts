import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {

  private getAllUrl = 'api/shopping-carts/';
  private getOneByIdUrl = 'api/shopping-carts/';
  private addCartUrl = 'api/shopping-carts/';
  private deleteCartUrl = 'api/shopping-carts/';
  private updateCartUrl = 'api/shopping-carts/';

  constructor(private http: HttpClient) { }

  private getCart(cartId: string) {
    return this.http.get(this.getOneByIdUrl + { cartId });
  }

  private create() {

    return this.http.post(this.addCartUrl, {});
  }

  private getOrCreateCartId() {

    let cartId = localStorage.getItem('cartId');

    if (cartId) return cartId;

    this.create().subscribe(
      data => {
        console.log(data);
        localStorage.setItem('cartId', data['id']);
        return data['id'];
      },
      error => {
        console.log(error)
        return;
      })
  }

  async addToCart(product: Product) {
    let cart = await this.getOrCreateCartId();
    
  }
}
