import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CustomerModel } from '../../models/customer-model';
import { CustomerService } from '../../services/customer-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-customer-component',
  imports: [ReactiveFormsModule],
  templateUrl: './add-customer-component.html',
  styleUrl: './add-customer-component.css',
})
export class AddCustomerComponent implements OnInit {
  protected newCustomerFormGroup!: FormGroup;


  constructor(private formBuilder: FormBuilder,
              private customerService: CustomerService,
              private router:Router) {}

  ngOnInit() {
    this.newCustomerFormGroup = this.formBuilder.group({
      name: this.formBuilder.control(null, [Validators.required]),
      email: this.formBuilder.control(null, [Validators.required, Validators.email]),
    });
  }

  handleAddCustomer(){
    let customer:CustomerModel = this.newCustomerFormGroup.value
    this.customerService.addCustomer(customer).subscribe({
      next: resp => {
        alert(`${customer.name} was added successfully`)
        this.newCustomerFormGroup.reset()
        this.router.navigateByUrl("/customers")

      },
      error: err => {
        console.error(err)
        alert(err["message"])
      }
    })
  }
}
