import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private categoryUrl="/api/categories/";

  constructor(private http: HttpClient) { }

  getAllCategories(){
    return this.http.get(this.categoryUrl);
  }
}
