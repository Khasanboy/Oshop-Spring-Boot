import { Component, OnInit, Input } from '@angular/core';
import { Product } from '../../models/product';
import { ShoppingCartService } from '../../services/shopping-cart.service';
import { ShoppingCart } from '../../models/shopping-cart';
import { ShoppingCartItem } from '../../models/shopping-cart-item';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {
  @Input('product') product: Product;
  @Input('show-actions') showActions = true;
  @Input('shopping-cart') shoppingCart;
  item: ShoppingCartItem;

  constructor(private shoppingCartService: ShoppingCartService) {}

  ngOnInit() {}

  async addToCart(product: Product) {
    this.shoppingCart = await this.shoppingCartService.addToCart(product);
    this.getQuantity();
  }

  getQuantity() {
    if (!this.shoppingCart) {
      return 0;
    } else {
      for (let i = 0; i < this.shoppingCart.items.length; i++) {
        if (this.shoppingCart.items[i].product.id == this.product.id) {
          return this.shoppingCart.items[i].quantity;
        }
      }
      return 0;
    }
  }
}
