import { Component, OnInit } from '@angular/core';
import { CustomerModel } from '../../models/customer-model';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountService } from '../../services/account-service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { disabled } from '@angular/forms/signals';
import { CustomerService } from '../../services/customer-service';

@Component({
  selector: 'app-update-customer-component',
  imports: [ReactiveFormsModule],
  templateUrl: './update-customer-component.html',
  styleUrl: './update-customer-component.css',
})
export class UpdateCustomerComponent implements OnInit {
  customerId!: string;
  customer!: CustomerModel;
  protected updateCustomerFormGroup!: FormGroup;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private customerService: CustomerService,
  ) {
    this.customer = this.router.getCurrentNavigation()?.extras.state as CustomerModel;
  }

  ngOnInit() {
    this.customerId = this.route.snapshot.params['id'];

    this.updateCustomerFormGroup = this.formBuilder.group({
      id: this.formBuilder.control({ value: this.customer.id, disabled: true }),
      name: this.formBuilder.control(this.customer.name, [Validators.required]),
      email: this.formBuilder.control(this.customer.email, [Validators.required, Validators.email]),
    });
  }

  handleUpdateCustomer(){
    let updatedCustomer:CustomerModel = this.updateCustomerFormGroup.value;
    updatedCustomer.id = this.customerId;
    this.customerService.updateCustomer(updatedCustomer).subscribe({
      next: (resp) => {
        alert("Update successful");
        this.router.navigateByUrl('/app/customers');
      },
      error: (err) => {
        console.error(err);
        alert(err["message"]);
      },
    });
  }
}
