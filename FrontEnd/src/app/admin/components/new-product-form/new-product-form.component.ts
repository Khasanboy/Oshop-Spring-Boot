import { Component, OnInit } from '@angular/core';
import { CategoryService } from '@shopping/services/category.service';
import { ProductService } from '@shopping/services/product.service';
import { Router} from '@angular/router';

@Component({
  selector: 'app-new-product-form',
  templateUrl: './new-product-form.component.html',
  styleUrls: ['./new-product-form.component.css']
})
export class NewProductFormComponent implements OnInit {

  categories;
  product = {};
  selectedFile = null;
  url = '';

  constructor(
    private catergoryService: CategoryService,
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit() {
    this.catergoryService
      .getAllCategories()
      .subscribe(data => (this.categories = data), error => console.log(error));
  }

  onFileSelected(event) {
    this.selectedFile = event.target.files[0];
    const reader = new FileReader();

    reader.onload = (event1: any) => {
      this.url = event1.target.result;
    };

    reader.readAsDataURL(event.target.files[0]);
  }

  addProduct(product) {
     const data = new FormData();
     data.append('file', this.selectedFile, this.selectedFile.name);
     data.append('title', this.product['title']);
     data.append('price', this.product['price']);
     data.append('categoryId', this.product['category']);

      this.productService.createProduct(data).subscribe(
        returnedData => {
          this.router.navigate(['/admin/products']);
        },
        error => console.log(error)
      );
  }
}
