import { Component, OnInit } from '@angular/core';
import { CustomerModel } from '../../models/customer-model';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of, startWith } from 'rxjs';
import { AccountModel } from '../../models/account-model';
import { AccountService } from '../../services/account-service';
import { AsyncPipe, DecimalPipe } from '@angular/common';

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

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private accountService: AccountService,
  ) {
    this.customer = this.router.getCurrentNavigation()?.extras.state as CustomerModel;
  }

  ngOnInit() {
    this.customerId = this.route.snapshot.params['id'];
    this.accounts = this.accountService.getAccountsByCustomer(this.customerId).pipe(
      startWith([]),
      catchError(err => of([]))
    );
  }
}
