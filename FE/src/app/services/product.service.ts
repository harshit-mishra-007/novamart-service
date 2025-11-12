import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { ApiResponse } from "../models/api-response.model";
import { Product } from "../models/product.model";

@Injectable({
  providedIn: "root",
})
export class ProductService {
  private apiUrl = environment.apiUrl;

  // Basic auth credentials from your application.yaml
  private username = "admin";
  private password = "admin123";

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    const credentials = btoa(`${this.username}:${this.password}`);
    return new HttpHeaders({
      Authorization: `Basic ${credentials}`,
      "Content-Type": "application/json",
    });
  }

  /**
   * Fetch all products from the backend
   */
  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/products`, {
      headers: this.getHeaders(),
    });
  }

  /**
   * Fetch a single product by ID
   */
  getProductById(productId: string): Observable<ApiResponse<Product>> {
    return this.http.get<ApiResponse<Product>>(
      `${this.apiUrl}/products/${productId}`,
      {
        headers: this.getHeaders(),
      }
    );
  }

  /**
   * Create a new product (you'll need to add this endpoint to your backend)
   */
  createProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${this.apiUrl}/products`, product, {
      headers: this.getHeaders(),
    });
  }

  /**
   * Update an existing product (you'll need to add this endpoint to your backend)
   */
  updateProduct(productId: string, product: Product): Observable<Product> {
    return this.http.put<Product>(
      `${this.apiUrl}/products/${productId}`,
      product,
      {
        headers: this.getHeaders(),
      }
    );
  }

  /**
   * Delete a product (you'll need to add this endpoint to your backend)
   */
  deleteProduct(productId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/products/${productId}`, {
      headers: this.getHeaders(),
    });
  }
}
