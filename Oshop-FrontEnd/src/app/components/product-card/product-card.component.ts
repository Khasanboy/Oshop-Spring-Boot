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
  quantity = 0;
  found = false;

  constructor(private shoppingCartService: ShoppingCartService) {
    // this.shoppingCartService
  }

  ngOnInit() {
  }

  addToCart(product: Product) {
    this.shoppingCartService.addToCart(product);
    this.shoppingCartService.getCart().subscribe(
      (data: ShoppingCart) => {
        this.shoppingCart = data;
        this.getQuantity();
        console.log(this.shoppingCart);
      },
      error => console.log(error)
  );

  }

  getQuantity() {
    if (!this.shoppingCart) {
      this.quantity = 0;
      return;
    } else {
      for (let i = 0; i < this.shoppingCart.items.length; i++) {
        if (this.shoppingCart.items[i].product.id == this.product.id) {
          this.found =  true;
          console.log(this.shoppingCart.items[i]);
          this.quantity =  this.shoppingCart.items[i].quantity;
        }
      }

     // if (!this.found) { return 0; }
    }
  }

}
