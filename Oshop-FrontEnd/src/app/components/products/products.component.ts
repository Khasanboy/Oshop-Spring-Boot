import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';
import "rxjs/add/operator/switchMap";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Product[] = [];
  filteredProducts: Product[] = [];
  category;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute
  ) {

    this.productService.getAllProducts()
    .switchMap(
      (products: Product[]) => {
        this.products = products;
        return route.queryParamMap;
       })
       .subscribe(params => {
          this.category = params.get('category');
          console.log(this.category);
          console.log(this.products)
          this.filteredProducts = (this.category) ? this.products.filter(p => p.category == this.category) : this.products;
        }),
      error => console.log(error);

  }

  ngOnInit() {
  }



}
