import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { environment } from '../../../environments/environment';
import { Product, ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {
  private readonly productService = inject(ProductService);

  loading = true;
  error: string | null = null;
  products: Product[] = [];

  constructor() {
    console.log('ProductListComponent constructor called');
  }

  ngOnInit() {
    console.log('ProductListComponent ngOnInit - calling API:', `${environment.apiBaseUrl}/products`);
    this.productService.getProducts().subscribe({
      next: (products) => {
        console.log('API success:', products);
        this.products = products ?? [];
        this.loading = false;
      },
      error: (err) => {
        console.error('API error:', err);
        this.error = err?.message ?? 'Failed to load products.';
        this.loading = false;
      },
    });
  }
}