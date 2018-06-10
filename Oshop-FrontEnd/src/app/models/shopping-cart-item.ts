import { Product } from './product';

export class ShoppingCartItem {
    id: number;
    quantity: number;
    productId: string;
    product: Product;

    constructor(quantity: number, productId?: string, product?: Product) {
        this.quantity = quantity;
        this.productId = productId;
        this.product = product;
    }
}
