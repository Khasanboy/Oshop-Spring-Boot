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
  found = false;
  quantity: number;
  item: ShoppingCartItem;

  constructor(private shoppingCartService: ShoppingCartService) {
  }

  ngOnInit() {
  }

  addToCart(product: Product) {
    this.shoppingCartService.addToCart(product);
  }

  getQuantity() {
    this.shoppingCartService.getCart().subscribe(
      (cart: ShoppingCart) => {
        if (!cart) {
          return 0;
        } else {
          for (let i = 0; i < cart.items.length; i++) {
            if (cart.items[i].product.id == this.product.id) {
              this.found =  true;
              return cart.items[i].quantity;
            }
          }
         if (!this.found) { return 0; }
        }
      },
      error => {
        console.log(error);
      }
    );
  }

}
