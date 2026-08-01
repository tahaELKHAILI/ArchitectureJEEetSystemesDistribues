import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { CustomerModel } from '../../models/customer-model';
import { CustomerService } from '../../services/customer-service';
import { AsyncPipe } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-customers-component',
  imports: [AsyncPipe, ReactiveFormsModule],
  templateUrl: './customers-component.html',
  styleUrl: './customers-component.css',
})
export class CustomersComponent implements OnInit {
  customers!: Observable<Array<CustomerModel>>;
  searchFormGroup: FormGroup | undefined;
  errorMessage!: string;

  constructor(
    private customerService: CustomerService,
    private formBuilder: FormBuilder,
    private changeDetection: ChangeDetectorRef,
    private router: Router,
    public authService:AuthService
  ) {}

  ngOnInit() {
    this.searchFormGroup = this.formBuilder.group({
      keyword: this.formBuilder.control(''),
    });
    this.handleCustomerSearch();
  }

  handleCustomerSearch() {
    let kw = this.searchFormGroup?.value.keyword;
    this.customers = this.customerService.searchCustomers(kw).pipe(
      catchError((err) => {
        this.errorMessage = err.message;
        return of([]);
      }),
    );
  }

  handleDeleteCustomer(c: CustomerModel) {
    const conf = confirm(`Do you want to delete customer ${c.name}?`);

    if (!conf) {
      return;
    }

    this.customerService.deleteCustomer(c.id).subscribe({
      next: () => {
        this.customers = this.customerService.getCusomters();
        this.changeDetection.detectChanges();
      },
      error: (err) => {
        console.error(err);
        alert(`Can't delete customer as they have associated bank accounts`);
      },
    });
  }

  handleCustomerAccounts(customer: CustomerModel) {
    this.router.navigateByUrl('/app/accounts/' + customer.id);
  }

  handleCustomerUpdate(customer: CustomerModel){
    this.router.navigateByUrl('/app/customers/' + customer.id, { state: customer });
  }
}
