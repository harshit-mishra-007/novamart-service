import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AgGridAngular } from "ag-grid-angular";
import { ColDef, GridReadyEvent } from "ag-grid-community";
import { Product } from "../../models/product.model";
import { ProductService } from "../../services/product.service";

@Component({
  selector: "app-product-list",
  standalone: true,
  imports: [CommonModule, AgGridAngular],
  templateUrl: "./product-list.component.html",
  styleUrls: ["./product-list.component.scss"],
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  loading = false;
  error: string | null = null;

  // AG Grid configuration
  columnDefs: ColDef[] = [
    {
      field: "productId",
      headerName: "Product ID",
      sortable: true,
      filter: true,
      width: 150,
    },
    {
      field: "name",
      headerName: "Name",
      sortable: true,
      filter: true,
      flex: 1,
    },
    {
      field: "description",
      headerName: "Description",
      sortable: true,
      filter: true,
      flex: 2,
    },
    {
      field: "price",
      headerName: "Price",
      sortable: true,
      filter: true,
      width: 120,
      valueFormatter: (params) => `$${params.value?.toFixed(2) || "0.00"}`,
    },
    {
      field: "category",
      headerName: "Category",
      sortable: true,
      filter: true,
      width: 150,
    },
    {
      field: "stock",
      headerName: "Stock",
      sortable: true,
      filter: true,
      width: 100,
    },
    {
      field: "active",
      headerName: "Active",
      sortable: true,
      filter: true,
      width: 100,
      cellRenderer: (params: any) => (params.value ? "✓" : "✗"),
    },
    {
      headerName: "Actions",
      width: 150,
      cellRenderer: (params: any) => {
        return `
          <button class="btn-action btn-view" data-action="view" data-id="${params.data.productId}">View</button>
          <button class="btn-action btn-edit" data-action="edit" data-id="${params.data.productId}">Edit</button>
        `;
      },
      suppressHeaderMenuButton: true,
      suppressHeaderFilterButton: true,
    },
  ];

  defaultColDef: ColDef = {
    sortable: true,
    filter: true,
    resizable: true,
  };

  constructor(
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.loading = true;
    this.error = null;

    this.productService.getAllProducts().subscribe({
      next: (products) => {
        this.products = products;
        this.loading = false;
      },
      error: (error) => {
        console.error("Error loading products:", error);
        this.error = "Failed to load products. Please try again.";
        this.loading = false;
      },
    });
  }

  onGridReady(params: GridReadyEvent): void {
    params.api.sizeColumnsToFit();
  }

  onCellClicked(event: any): void {
    if (event.event.target.dataset.action) {
      const action = event.event.target.dataset.action;
      const productId = event.event.target.dataset.id;

      if (action === "view") {
        this.viewProduct(productId);
      } else if (action === "edit") {
        this.editProduct(productId);
      }
    }
  }

  viewProduct(productId: string): void {
    this.router.navigate(["/products", productId]);
  }

  editProduct(productId: string): void {
    this.router.navigate(["/products", productId, "edit"]);
  }

  addNewProduct(): void {
    this.router.navigate(["/products", "new"]);
  }

  refreshProducts(): void {
    this.loadProducts();
  }
}
