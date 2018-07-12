import { ShoppingCartItem } from './shopping-cart-item';

export class Order {
    id: number;
    datePlaced: Date;
    items: ShoppingCartItem[] = [];
    constructor(id?, datePlaced?, items?) {
        this.id = id;
        this.datePlaced = datePlaced;
        this.items = items;
        /*
        for (const item of items) {
            this.items.push(new ShoppingCartItem(item.id, item.quantity, item.product));
        }
        */
    }
}
