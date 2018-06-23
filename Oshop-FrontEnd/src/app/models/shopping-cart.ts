import { ShoppingCartItem } from './shopping-cart-item';

export class ShoppingCart {
    id: number;
    items: ShoppingCartItem [] = [];

    constructor(id, items) {
        this.id = id;
        this.items = items;
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
}
