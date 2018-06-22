import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';
import { ShoppingCartItem } from '../models/shopping-cart-item';


@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  private url = 'api/shopping-carts/';

  constructor(private http: HttpClient) {}

  private async getShoppingCartItem(cartId: string, itemId: string): Promise<any> {
    return await this.http.get(this.url + cartId + '/items/' + itemId).toPromise();
  }

  private async createShoppingCartItem(cartId: string, cartItem): Promise<any> {
    return await this.http.post(this.url + cartId + '/items', cartItem).toPromise();
  }
  private async updateShoppingCartItem(
    cartId: string,
    productId: string,
    item: ShoppingCartItem
  ) {
    return await this.http.put(this.url + cartId + '/items/' + productId, item).toPromise();
  }

  private async createCart() {
    return await this.http.post(this.url, {}).toPromise();
  }

  private async getCartFromAPI(cartId) {
    return await this.http.get(this.url + cartId).toPromise();
  }

  async getCart(): Promise<any> {
    const cartId = localStorage.getItem('cartId');
    if (cartId) {
      return await this.getCartFromAPI(cartId);
    } else {
      const cart = await this.createCart();
      localStorage.setItem('cartId', cart['id']);
      return cart;
    }
  }

  async addToCart(product: Product) {

    let cart = await this.getCart();
    const cartItem = await this.getShoppingCartItem(cart.id, product.id);

    if (cartItem) {
      cartItem.quantity = cartItem.quantity + 1;
      cart = await this.updateShoppingCartItem(cart.id, product.id, cartItem);
    } else {
      cart = await this.createShoppingCartItem(cart.id, {quantity: 1, productId: product.id});
    }
    return cart;
  }
}
