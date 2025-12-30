import { Routes } from "@angular/router";
import { NotFoundComponent } from './components/not-found/not-found.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';
import { ProductListComponent } from './components/product-list/product-list.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/products',
    pathMatch: 'full',
  },
  {
    path: 'products',
    component: ProductListComponent,
  },
  {
    path: 'products/:productId',
    component: ProductDetailComponent,
  },
  {
    path: '**',
    component: NotFoundComponent,
  },
];