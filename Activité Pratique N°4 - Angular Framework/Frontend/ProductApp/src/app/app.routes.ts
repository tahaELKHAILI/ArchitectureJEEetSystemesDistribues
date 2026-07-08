import { Routes } from '@angular/router';
import { Products } from './products/products';
import { Home } from './home/home';

export const routes: Routes = [
  {path: "home", component: Home},
  {path: "products", component: Products}
];
