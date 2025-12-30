import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

export type ProductId = string;

export interface Product {
  id: string;
  productId: ProductId;
  name: string;
  description: string;
  price: number;
  category: string;
  stock: number;
  active: boolean;
}

@Injectable({ providedIn: 'root' })
export class ProductService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = environment.apiBaseUrl.replace(/\/+$/, '');

  getProducts() {
    return this.http.get<Product[]>(`${this.baseUrl}/products`);
  }

  getProductById(productId: ProductId) {
    return this.http.get<Product>(`${this.baseUrl}/products/${encodeURIComponent(productId)}`);
  }
}