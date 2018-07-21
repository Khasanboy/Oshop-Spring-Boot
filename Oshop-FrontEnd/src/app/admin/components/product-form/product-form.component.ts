import { ProductService } from '@shopping/services/product.service';
import { CategoryService } from '@shopping/services/category.service';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  categories;
  product = {};
  id;

  constructor(private catergoryService: CategoryService,
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.catergoryService.getAllCategories().subscribe(
      data => this.categories = data,
      error => console.log(error)
    );

    this.id = this.route.snapshot.paramMap.get('id');

    if (this.id) {
      this.productService.getOneProductById(+this.id).subscribe(
        data => this.product = data,
        error => console.log(error)
      );
    }


  }

  addProduct(product) {
    this.id = this.route.snapshot.paramMap.get('id');
    if (this.id) {
      this.productService.updateProduct(this.id, product).subscribe(
        data => {
          this.router.navigate(['/admin/products']);
        },
        error => {
          console.log(error);
        }
      );
    } else {
      this.productService.createProduct(product).subscribe(
        data => {
          this.router.navigate(['/admin/products']);
        },
        error => console.log(error)
      );
    }
  }

  deleteProduct() {
    this.productService.deleteProduct(this.id).subscribe(
      data => {
        this.router.navigate(['/admin/products']);
    },
      error => console.log(error)
    );
  }

}
