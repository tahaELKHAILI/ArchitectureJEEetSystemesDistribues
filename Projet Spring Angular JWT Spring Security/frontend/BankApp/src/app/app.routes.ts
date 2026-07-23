import { Routes } from '@angular/router';
import { CustomersComponent } from './components/customers-component/customers-component';
import { AddCustomerComponent } from './components/add-customer-component/add-customer-component';
import { AccountsComponent } from './components/accounts-component/accounts-component';
import { OperationsComponent } from './components/operations-component/operations-component';
import { UpdateCustomerComponent } from './components/update-customer-component/update-customer-component';
import { AddAccountComponent } from './components/add-account-component/add-account-component';
import { TransactionsComponent } from './components/transactions-component/transactions-component';

export const routes: Routes = [
  { component: CustomersComponent, path: 'customers' },
  { component: AddCustomerComponent, path: 'new-customer' },
  { component: UpdateCustomerComponent, path: 'customers/:id' },
  { component: AccountsComponent, path: 'accounts/:id' },
  { component: AddAccountComponent, path: 'accounts/:id/new-account' },
  { component: OperationsComponent, path: 'operations/:id' },
  { component: OperationsComponent, path: 'operations' },
];
