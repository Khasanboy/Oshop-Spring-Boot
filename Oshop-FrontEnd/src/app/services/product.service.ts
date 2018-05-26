import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private getAllUrl = 'api/products/';
  private getOneByIdUrl = 'api/products/';
  private addProductUrl = 'api/products/';
  private deleteProductUrl = 'api/products/';
  private updateProductUrl = 'api/products/';

  constructor(private http: HttpClient) { }

  getAllProducts(){
    return this.http.get(this.getAllUrl);
  }

  getOneProductById(id:number){
    return this.http.get(this.getOneByIdUrl+id);
  }

  createProduct(product){
    return this.http.post(this.addProductUrl, product);
  }

  updateProduct(id, product){
    return this.http.put(this.updateProductUrl+id, product);
  }

  deleteProduct(id:number){
    return this.http.delete(this.deleteProductUrl+id);
  }
}
