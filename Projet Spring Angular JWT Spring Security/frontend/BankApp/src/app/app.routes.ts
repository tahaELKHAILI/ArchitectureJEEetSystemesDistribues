import { Routes } from '@angular/router';
import { CustomersComponent } from './components/customers-component/customers-component';
import { AddCustomerComponent } from './components/add-customer-component/add-customer-component';
import { AccountsComponent } from './components/accounts-component/accounts-component';
import { OperationsComponent } from './components/operations-component/operations-component';
import { UpdateCustomerComponent } from './components/update-customer-component/update-customer-component';
import { AddAccountComponent } from './components/add-account-component/add-account-component';
import { LoginComponent } from './components/login-component/login-component';
import { AppTemplateComponent } from './components/app-template-component/app-template-component';
import { authenticationGuard } from './guards/authentication-guard';
import { authorizationGuard } from './guards/authorization-guard';
import { NotAuthorizedComponent } from './components/not-authorized-component/not-authorized-component';
import { HomeScreenComponent } from './components/home-screen-component/home-screen-component';

export const routes: Routes = [
  { component: LoginComponent, path: 'login' },
  //{ path: '', redirectTo: '/login', pathMatch: 'full' },
  {
    path: '',
    component: AppTemplateComponent,
    canActivate: [authenticationGuard],
    children: [
      // Automatically show the Home Screen when landing on the root URL
      { component: HomeScreenComponent, path: '' },
    ],
  },
  {
    component: AppTemplateComponent,
    path: 'app',
    canActivate: [authenticationGuard],
    children: [
      { component: CustomersComponent, path: 'customers' },
      {
        component: AddCustomerComponent,
        path: 'new-customer',
        canActivate: [authorizationGuard],
        data: { role: 'ADMIN' },
      },
      { component: UpdateCustomerComponent, path: 'customers/:id' },
      { component: AccountsComponent, path: 'accounts/:id' },
      {
        component: AddAccountComponent,
        path: 'accounts/:id/new-account',
        canActivate: [authorizationGuard],
        data: { role: 'ADMIN' },
      },
      { component: OperationsComponent, path: 'operations/:id' },
      { component: OperationsComponent, path: 'operations' },
      { component: NotAuthorizedComponent, path: 'notAuthorized' },
      { component: HomeScreenComponent, path: 'home' },
    ],
  },
];
