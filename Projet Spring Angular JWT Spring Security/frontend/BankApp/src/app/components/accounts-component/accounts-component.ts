import { Component, OnInit } from '@angular/core';
import { CustomerModel } from '../../models/customer-model';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of, startWith } from 'rxjs';
import { AccountModel } from '../../models/account-model';
import { AccountService } from '../../services/account-service';
import { AsyncPipe, DecimalPipe } from '@angular/common';
import { CustomerService } from '../../services/customer-service';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-accounts-component',
  imports: [AsyncPipe, DecimalPipe],
  templateUrl: './accounts-component.html',
  styleUrl: './accounts-component.css',
})
export class AccountsComponent implements OnInit {
  customerId!: string;
  customer!: CustomerModel;
  accounts!: Observable<Array<AccountModel>>;
  customerBalance = 0;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private accountService: AccountService,
    private customerService: CustomerService,
    public authService:AuthService,
  ) {
  }

  ngOnInit() {
    this.customerId = this.route.snapshot.params['id'];
    this.customerService.getCustomer(this.customerId).subscribe({
      next: (customer) => {
        this.customer = customer;
      },
      error: (err) => {
        console.error(err);
      },
    });
    this.loadAccounts();
    this.calculateCustomerBalance();
  }

  loadAccounts() {
    this.accounts = this.accountService.getAccountsByCustomer(this.customerId).pipe(
      startWith([]),
      catchError((err) => of([])),
    );
  }

  calculateCustomerBalance() {
    this.accounts.subscribe((accounts) => {
      accounts.forEach((account) => {
        this.customerBalance += account.balance;
      });
    });
  }

  handleAddAccounts(accountID: string) {
    this.router.navigateByUrl('/app/accounts/' + accountID + '/new-account');
  }

  handleOperations(accountID: string) {
    this.router.navigateByUrl('/app/operations/' + accountID);
  }
}
