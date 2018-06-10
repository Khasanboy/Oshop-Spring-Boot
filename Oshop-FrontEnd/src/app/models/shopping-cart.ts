import { ShoppingCartItem } from './shopping-cart-item';

export class ShoppingCart {
    id: number;
    createdAt: Date;
    updatedAt: Date;
    items: Set<ShoppingCartItem>;

    getQuantity(productId) {
        let i = 0;
        this.items.forEach(item => {
            if (item.product.id === productId) {
                i++;
            }
        });

        return i;
    }
}
