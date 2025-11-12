import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { Product } from "../../models/product.model";
import { ProductService } from "../../services/product.service";

@Component({
  selector: "app-product-detail",
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: "./product-detail.component.html",
  styleUrls: ["./product-detail.component.scss"],
})
export class ProductDetailComponent implements OnInit {
  productForm!: FormGroup;
  productId: string | null = null;
  isEditMode = false;
  isNewProduct = false;
  loading = false;
  error: string | null = null;
  successMessage: string | null = null;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.initForm();
  }

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.productId = params["id"];

      if (this.productId === "new") {
        this.isNewProduct = true;
        this.isEditMode = true;
      } else if (this.productId) {
        this.loadProduct(this.productId);
        // Check if we're in edit mode based on the URL
        this.isEditMode =
          this.route.snapshot.url[this.route.snapshot.url.length - 1]?.path ===
          "edit";

        if (!this.isEditMode) {
          this.productForm.disable();
        }
      }
    });
  }

  initForm(): void {
    this.productForm = this.fb.group({
      productId: ["", Validators.required],
      name: ["", Validators.required],
      description: ["", Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      category: ["", Validators.required],
      stock: [0, [Validators.required, Validators.min(0)]],
      active: [true],
    });
  }

  loadProduct(productId: string): void {
    this.loading = true;
    this.error = null;

    this.productService.getProductById(productId).subscribe({
      next: (response) => {
        if (response.data) {
          this.productForm.patchValue(response.data);
        }
        this.loading = false;
      },
      error: (error) => {
        console.error("Error loading product:", error);
        this.error = "Failed to load product details. Please try again.";
        this.loading = false;
      },
    });
  }

  onSubmit(): void {
    if (this.productForm.invalid) {
      this.error = "Please fill in all required fields correctly.";
      return;
    }

    this.loading = true;
    this.error = null;
    this.successMessage = null;

    const productData: Product = this.productForm.value;

    if (this.isNewProduct) {
      this.productService.createProduct(productData).subscribe({
        next: (product) => {
          this.successMessage = "Product created successfully!";
          this.loading = false;
          setTimeout(() => {
            this.router.navigate(["/products"]);
          }, 1500);
        },
        error: (error) => {
          console.error("Error creating product:", error);
          this.error = "Failed to create product. Please try again.";
          this.loading = false;
        },
      });
    } else if (this.productId) {
      this.productService.updateProduct(this.productId, productData).subscribe({
        next: (product) => {
          this.successMessage = "Product updated successfully!";
          this.loading = false;
          setTimeout(() => {
            this.router.navigate(["/products"]);
          }, 1500);
        },
        error: (error) => {
          console.error("Error updating product:", error);
          this.error = "Failed to update product. Please try again.";
          this.loading = false;
        },
      });
    }
  }

  toggleEditMode(): void {
    this.isEditMode = !this.isEditMode;

    if (this.isEditMode) {
      this.productForm.enable();
    } else {
      this.productForm.disable();
      // Reload the product to discard changes
      if (this.productId && this.productId !== "new") {
        this.loadProduct(this.productId);
      }
    }
  }

  cancel(): void {
    this.router.navigate(["/products"]);
  }

  deleteProduct(): void {
    if (!this.productId || this.productId === "new") return;

    if (confirm("Are you sure you want to delete this product?")) {
      this.loading = true;
      this.error = null;

      this.productService.deleteProduct(this.productId).subscribe({
        next: () => {
          this.successMessage = "Product deleted successfully!";
          this.loading = false;
          setTimeout(() => {
            this.router.navigate(["/products"]);
          }, 1500);
        },
        error: (error) => {
          console.error("Error deleting product:", error);
          this.error = "Failed to delete product. Please try again.";
          this.loading = false;
        },
      });
    }
  }
}
