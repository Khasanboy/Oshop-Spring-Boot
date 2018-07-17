import { ProductService } from '@shared/services/product.service';
import { Component, OnInit } from '@angular/core';
import { Product } from '@shared/models/product';
import { DataTableResource } from 'angular5-data-table';

@Component({
  selector: 'app-admin-products',
  templateUrl: './admin-products.component.html',
  styleUrls: ['./admin-products.component.css']
})
export class AdminProductsComponent implements OnInit {

  products: Product[];
  tableResource: DataTableResource<Product>;
  items: Product[] = [];
  itemCount: number;

  constructor(private productService: ProductService) {
    this.productService.getAllProducts().subscribe(
      (data: Product[]) => {
        this.products = data;
        this.initializeTable(data);
      },
      error => console.log(error)
    );
  }

  private initializeTable(products: Product[]) {
    this.tableResource = new DataTableResource(products);
    this.tableResource.query({ offset: 0 })
      .then(items => { this.items = items; });

    this.tableResource.count()
      .then(count => this.itemCount = count);
  }

  reloadItems(params) {

    if (!this.tableResource) { return; }

    this.tableResource.query(params)
      .then(items => { this.items = items; });
  }

  ngOnInit() {
  }

  filter(query: string) {
    const filteredProducts = (query) ?
      this.products.filter(product => product.title.toLowerCase().includes(query.toLowerCase())) :
      this.products;
    this.initializeTable(filteredProducts);
  }


}
