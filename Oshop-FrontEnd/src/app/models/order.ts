import { ShoppingCartItem } from './shopping-cart-item';
import { ShoppingCart } from './shopping-cart';

export class Order {
    id: number;
    datePlaced: Date;
    items: ShoppingCartItem[] = [];
    constructor(public userId: number, public shipping: any, shoppingCart: ShoppingCart) {
        for (const item of shoppingCart.items) {
            this.items.push(item);
        }
    }
}
