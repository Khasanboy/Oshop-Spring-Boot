import { Product } from './product';

export class ShoppingCartItem {
    id: number;
    quantity: number;
    product: Product;

    constructor(id: number, quantity: number, product: Product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    get totalPrice() {
        console.log(this.quantity * this.product.price);
        return this.quantity * this.product.price;
    }
}
