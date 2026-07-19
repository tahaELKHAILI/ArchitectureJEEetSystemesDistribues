import { Routes } from '@angular/router';
import { CustomersComponent } from './components/customers-component/customers-component';
import { AddCustomerComponent } from './components/add-customer-component/add-customer-component';
import { AccountsComponent } from './components/accounts-component/accounts-component';
import { Operations } from './components/operations/operations';

export const routes: Routes = [
  { component: CustomersComponent, path: 'customers' },
  { component: AddCustomerComponent, path: 'new-customer' },
  { component: AccountsComponent, path: 'accounts/:id'},
  { component: Operations, path: 'operations'}
];
