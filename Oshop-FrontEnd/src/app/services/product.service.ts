import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private url = 'api/products/';

  constructor(private http: HttpClient) { }

  getAllProducts() {
    return this.http.get(this.url);
  }

  getOneProductById(id: number) {
    return this.http.get(this.url + id);
  }

  createProduct(product) {
    return this.http.post(this.url, product);
  }

  updateProduct(id, product) {
    return this.http.put(this.url + id, product);
  }

  deleteProduct(id: number) {
    return this.http.delete(this.url + id);
  }
}
