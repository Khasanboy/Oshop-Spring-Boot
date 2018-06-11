import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';
import 'rxjs/add/operator/take';
import { ShoppingCartItem } from '../models/shopping-cart-item';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  private url = 'api/shopping-carts/';

  constructor(private http: HttpClient) {}

  private getShoppingCartItem(cartId: string, itemId: string) {
    return this.http.get(this.url + cartId + '/items/' + itemId);
  }

  private createShoppingCartItem(cartId: string, cartItem) {
    return this.http.post(this.url + cartId + '/items', cartItem);
  }
  private updateShoppingCartItem(
    cartId: string,
    productId: string,
    item: ShoppingCartItem
  ) {
    return this.http.put(this.url + cartId + '/items/' + productId, item);
  }

  private deleteShoppingCartItem() {}

  getCart() {
    const cartId = localStorage.getItem('cartId');
    if (cartId) {
      return this.http.get(this.url + cartId);
    } else {
      this.createCart().subscribe(
        createdCart => {
          localStorage.setItem('cartId', createdCart['id']);
          return createdCart;

        },
        error => console.log(error)
      );
    }
  }

  private createCart() {
    return this.http.post(this.url, {});
  }

  private addProductToCard(cartId, product) {
    this.getShoppingCartItem(cartId, product.id).subscribe(
      (data: ShoppingCartItem) => {
        if (data != null) {
          data.quantity = data.quantity + 1;
          this.updateShoppingCartItem(cartId, product.id, data).subscribe(
            dataUp => console.log(dataUp),
            error => console.log(error)
          );
        } else {
          this.createShoppingCartItem(cartId, {'quantity': 1, 'productId': product.id}).subscribe(
            (data1: ShoppingCartItem) => console.log(data1),
            error => console.log(error)
          );
        }
      },
      error => {
        console.log(error);
      }
    );
  }

  addToCart(product: Product) {
    const cartId = localStorage.getItem('cartId');
    if (cartId) {
      this.addProductToCard(cartId, product);
      return;
    } else {
      this.createCart().subscribe(
        dataCart => {
          localStorage.setItem('cartId', dataCart['id']);
          this.addProductToCard(dataCart['id'], product);
        },
        error => console.log(error)
      );
    }
  }
}
