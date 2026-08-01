import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { AccountService } from '../../services/account-service';
import { ActivatedRoute, Router } from '@angular/router';
import { OperationModel } from '../../models/operation-model';
import { Observable } from 'rxjs';
import { DatePipe, DecimalPipe } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TransactionsComponent } from '../transactions-component/transactions-component';
import { AuthService } from '../../services/auth-service';
import { CustomerModel } from '../../models/customer-model';

@Component({
  selector: 'app-operations',
  imports: [DecimalPipe, DatePipe, ReactiveFormsModule, TransactionsComponent],
  templateUrl: './operations-component.html',
  styleUrl: './operations-component.css',
})
export class OperationsComponent implements OnInit {
  accountID!: string;
  accountHistory!: OperationModel;
  protected searchHistoryFormGroup!: FormGroup;
  currentPage: number = 0;
  pageSize: number = 5;

  constructor(
    private accountService: AccountService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private cd: ChangeDetectorRef,
    public authService: AuthService,
  ) {
  }

  ngOnInit() {
    this.accountID = this.route.snapshot.params['id'];
    if (this.accountID != null) {
      this.searchHistoryFormGroup = this.formBuilder.group({
        searchAccount: this.formBuilder.control(this.accountID, [Validators.required]),
      });
      this.handleGetHistory();
    } else {
      this.searchHistoryFormGroup = this.formBuilder.group({
        searchAccount: this.formBuilder.control(null, [Validators.required]),
      });
    }
  }

  handleGetHistory() {
    this.accountID = this.searchHistoryFormGroup.value['searchAccount'];
    this.accountService.accountHistory(this.accountID, this.currentPage, this.pageSize).subscribe({
      next: (resp) => {
        this.accountHistory = resp;
        this.cd.detectChanges();
      },
      error: (err) => {
        alert('Account does not exist.');
        this.searchHistoryFormGroup.reset();
      },
    });
  }

  gotoPage(page: number) {
    this.currentPage = page;
    this.handleGetHistory();
  }

  protected readonly Array = Array;
}
