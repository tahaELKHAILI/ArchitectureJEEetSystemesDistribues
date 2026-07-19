import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AccountService } from '../../services/account-service';
import { ActivatedRoute, Router } from '@angular/router';
import { CurrentAccountCreation } from '../../models/current-account-creation';
import { SavingAccountCreation } from '../../models/saving-account-creation';

@Component({
  selector: 'app-add-account-component',
  imports: [ReactiveFormsModule],
  templateUrl: './add-account-component.html',
  styleUrl: './add-account-component.css',
})
export class AddAccountComponent implements OnInit {
  protected newAccountFormGroup!: FormGroup;
  accountType!: string;

  constructor(
    private formBuilder: FormBuilder,
    private accountService: AccountService,
    private router: Router,
    private route: ActivatedRoute,
  ) {}

  ngOnInit() {
    this.newAccountFormGroup = this.formBuilder.group({
      initialBalance: this.formBuilder.control(0),
      accountType: this.formBuilder.control('Current'),
    });

    this.accountType = this.newAccountFormGroup.get('accountType')!.value;
    this.updateForm(this.accountType);

    this.newAccountFormGroup.get('accountType')?.valueChanges.subscribe((accountType) => {
      this.accountType = accountType;
      this.updateForm(this.accountType);
    });
  }

  updateForm(type: string) {
    this.newAccountFormGroup.removeControl('rate');
    this.newAccountFormGroup.removeControl('overdraft');

    if (type == 'Saving') {
      this.newAccountFormGroup.addControl('rate', this.formBuilder.control(0));
    } else {
      this.newAccountFormGroup.addControl('overdraft', this.formBuilder.control(0));
    }
  }

  handleAddAccount(){
    let type = this.newAccountFormGroup.value['accountType'];
    if(type =="Saving"){
      this.createSavingAccount()
    }
    else{
      this.createCurrentAccount()
    }
  }

  createCurrentAccount() {
    let currentAccount: CurrentAccountCreation = this.newAccountFormGroup.value;
    currentAccount.customerID = this.route.snapshot.params['id'];
    console.log(currentAccount);
    this.accountService.addCurrentAccount(currentAccount).subscribe({
      next: (resp) => {
        alert(`Account was added successfully`);
        this.newAccountFormGroup.reset();
        this.router.navigateByUrl('/accounts/' + currentAccount.customerID);
      },
      error: (err) => {
        console.error(err);
        alert(err['message']);
      },
    });
  }

  createSavingAccount() {
    let savingAccount: SavingAccountCreation = this.newAccountFormGroup.value;
    savingAccount.customerID = this.route.snapshot.params['id'];
    console.log(savingAccount);
    this.accountService.addSavingAccount(savingAccount).subscribe({
      next: (resp) => {
        alert(`Account was added successfully`);
        this.newAccountFormGroup.reset();
        this.router.navigateByUrl('/accounts/' + savingAccount.customerID);
      },
      error: (err) => {
        console.error(err);
        alert(err['message']);
      },
    });
  }
}
