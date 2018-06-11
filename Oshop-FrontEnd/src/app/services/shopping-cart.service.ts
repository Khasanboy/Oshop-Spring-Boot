import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';
import { ShoppingCartItem } from '../models/shopping-cart-item';
import { ShoppingCart } from '../models/shopping-cart';
import {tap} from 'rxjs/operators';

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
  private updateShoppingCartItem(cartId: string, productId: string, item: ShoppingCartItem) {
    return this.http.put(this.url + cartId + '/items/' + productId, item);
  }

  private deleteShoppingCartItem() {}

  private createCart() {
    return this.http.post(this.url, {}).pipe(
      tap(
        (cart: ShoppingCart) => {
        localStorage.setItem('cartId', cart.id.toString());
      },
      error => {
        console.log(error);
        return;
      }
      )
    );
  }

  private addProductToCard(cartId, product) {
    this.getShoppingCartItem(cartId, product.id).subscribe(
      (data: ShoppingCartItem) => {
        if (data != null) {
          data.quantity = data.quantity + 1;
          this.updateShoppingCartItem(cartId, product.id, data).subscribe(
            (updatedCartItem: ShoppingCartItem) => {
              console.log(updatedCartItem);
              this.getCart();
            },
            error => {
              console.log(error);
            }
          );
        } else {
          this.createShoppingCartItem(cartId, {'quantity': 1, 'productId': product.id}).subscribe(
            (data1: ShoppingCartItem) => {
              console.log(data1);
              this.getCart();
            },
            error => {
              console.log(error);
              return;
            }
          );
        }
      },
      error => {
        console.log(error);
        return;
      }
    );
  }

  getCart() {
    const cartId = localStorage.getItem('cartId');
    if (cartId) {
      return this.http.get(this.url + cartId);
    } else {
      return this.createCart();
    }
  }

  addToCart(product: Product) {
    this.getCart().subscribe(
      (cart: ShoppingCart) => {
        this.addProductToCard(cart.id, product);
      },
      error => {
        console.log(error);
        return;
      }
    );
  }
}
