import { Routes } from "@angular/router";
import { ProductDetailComponent } from "./components/product-detail/product-detail.component";
import { ProductListComponent } from "./components/product-list/product-list.component";

export const routes: Routes = [
  {
    path: "",
    redirectTo: "/products",
    pathMatch: "full",
  },
  {
    path: "products",
    component: ProductListComponent,
  },
  {
    path: "products/:id",
    component: ProductDetailComponent,
  },
  {
    path: "products/:id/edit",
    component: ProductDetailComponent,
  },
  {
    path: "**",
    redirectTo: "/products",
  },
];
