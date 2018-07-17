import { ShoppingCartItem } from '@shared/models/shopping-cart-item';
import { Product } from '@shared/models/product';

export class ShoppingCart {
  id: number;
  items: ShoppingCartItem[] = [];

  constructor(id, items: ShoppingCartItem[]) {
    this.id = id;
    for (const item of items) {
      this.items.push(new ShoppingCartItem(item.id, item.quantity, item.product));
    }
  }

  get quantityOfItems() {
    let quantity = 0;
    this.items.forEach(item => {
      quantity += item.quantity;
    });

    return quantity;
  }

  get totalPrice() {
    let price = 0;
    this.items.forEach(item => {
      price += item.totalPrice;
    });
    return price;
  }

  getQuantity(product: Product) {
    for (let i = 0; i < this.items.length; i++) {
      if (this.items[i].product.id == product.id) {
        return this.items[i].quantity;
      }
    }
    return 0;
  }
}
