import { ActivatedRoute } from '@angular/router';
import { CategoryService } from './../../services/category.service';
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products:Product[]=[];
  filteredProducts: Product[]=[];
  categories$;
  category;

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private route: ActivatedRoute
  ) {
    this.productService.getAllProducts().subscribe(
      (products:Product[]) => this.products = products,
      error => console.log(error)
    );
    this.categories$ = this.categoryService.getAllCategories();

    route.queryParamMap.subscribe(params => {
      this.category = params.get('category');
      console.log(this.category);
      console.log(this.products)
      this.filteredProducts = (this.category) ? this.products.filter(p=>p.category == this.category):this.products;
    })

  }

  ngOnInit() {
  }



}
