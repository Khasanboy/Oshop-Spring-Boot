import { ActivatedRoute } from '@angular/router';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { ProductService } from '@shared/services/product.service';
import { Product } from '@shared/models/product';
import 'rxjs/add/operator/switchMap';
import { ShoppingCartService } from '@shared/services/shopping-cart.service';
import { ShoppingCart } from '@shared/models/shopping-cart';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  category: string;
  shoppingCart: ShoppingCart = null;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private shoppingCartService: ShoppingCartService
  ) {

    this.populateProducts();

  }

  private populateProducts() {
    this.productService
      .getAllProducts()
      .switchMap((products: Product[]) => {
        this.products = products;
        return this.route.queryParamMap;
      })
      .subscribe(params => {
        this.category = params.get('category');
        this.applyFilter();
      });
  }

  private applyFilter() {
    this.filteredProducts = this.category
      ? this.products.filter(p => p.category === this.category)
      : this.products;
  }

  async ngOnInit() {
    if (this.shoppingCartService.cartExists()) {
      this.shoppingCart = await this.shoppingCartService.getCart();
    }
  }

  reassignCart(cart: ShoppingCart) {
    this.shoppingCart = cart;
  }
}
