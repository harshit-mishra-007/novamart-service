import { Routes } from "@angular/router";
import { LoginComponent } from "./components/pages/login-page/login-component";
import { NotFoundComponent } from './components/pages/not-found-page/not-found.component';
import { ProductDetailComponent } from './components/pages/product-details-page/product-detail.component';
import { ProductListComponent } from './components/pages/product-list-page/product-list.component';
import { RegisterComponent } from "./components/pages/register-page/register-component";

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
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: '**',
    component: NotFoundComponent,
  },
];