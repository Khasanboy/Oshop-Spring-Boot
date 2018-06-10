import { Component, OnInit, Input } from '@angular/core';
import { Product } from '../../models/product';
import { ShoppingCartService } from '../../services/shopping-cart.service';
import { ShoppingCart } from '../../models/shopping-cart';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {
  @Input('product') product: Product;
  @Input('show-actions') showActions = true;
  @Input('shopping-cart') shoppingCart: ShoppingCart;
  constructor(private shoppingCartService: ShoppingCartService) {
    // this.shoppingCartService
  }

  ngOnInit() {}

  addToCart(product: Product) {
    this.shoppingCartService.addToCart(product);
  }

  getQuantity() {
    if (!this.shoppingCart) {
      return 0;
    }
    this.shoppingCart.getQuantity(this.product.id);
  }
}
