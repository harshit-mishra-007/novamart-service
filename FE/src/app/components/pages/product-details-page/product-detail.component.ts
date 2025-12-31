import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { Product, ProductId, ProductService } from '../../services/product.service';

@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.scss']
})
export class ProductDetailComponent {
  private readonly route = inject(ActivatedRoute);
  private readonly productService = inject(ProductService);

  loading = true;
  error: string | null = null;
  product: Product | null = null;

  constructor() {
    const productId = this.route.snapshot.paramMap.get('productId') as ProductId | null;
    if (!productId) {
      this.error = 'Missing productId route parameter.';
      this.loading = false;
      return;
    }

    this.productService.getProductById(productId).subscribe({
      next: (product) => {
        this.product = product;
        this.loading = false;
      },
      error: (err) => {
        this.error = err?.message ?? 'Failed to load product.';
        this.loading = false;
      },
    });
  }
}