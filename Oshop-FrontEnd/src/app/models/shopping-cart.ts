import { ShoppingCartItem } from './shopping-cart-item';

export class ShoppingCart {
    id: number;
    items: ShoppingCartItem [] = [];

    constructor(id, items) {
        this.id = id;
        this.items = items;
    }
}
