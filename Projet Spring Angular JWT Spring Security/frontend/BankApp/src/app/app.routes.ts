import { Routes } from '@angular/router';
import { CustomersComponent } from './components/customers-component/customers-component';
import { AddCustomerComponent } from './components/add-customer-component/add-customer-component';

export const routes: Routes = [
  { component: CustomersComponent, path: 'customers' },
  { component: AddCustomerComponent, path: 'new-customer' },
];
