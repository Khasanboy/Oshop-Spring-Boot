import { CategoryService } from './../../../services/category.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  categories;

  constructor(catergoryService: CategoryService) {
      catergoryService.getCategories().subscribe(
        data => this.categories = data,
        error => console.log(error)
     )
   }

  ngOnInit() {

  }

  addProduct(product){
    console.log(product);
  }

}
