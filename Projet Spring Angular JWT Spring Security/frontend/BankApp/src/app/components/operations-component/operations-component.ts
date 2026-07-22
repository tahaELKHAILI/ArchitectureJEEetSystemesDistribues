import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../services/account-service';
import { ActivatedRoute } from '@angular/router';
import { OperationModel } from '../../models/operation-model';
import { Observable } from 'rxjs';
import { DatePipe, DecimalPipe } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-operations',
  imports: [DecimalPipe, DatePipe, ReactiveFormsModule],
  templateUrl: './operations-component.html',
  styleUrl: './operations-component.css',
})
export class OperationsComponent implements OnInit {
  accountID!: string;
  accountHistory!:OperationModel;
  protected searchHistoryFormGroup!: FormGroup;

  constructor(
    private accountService: AccountService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit() {

    this.accountID = this.route.snapshot.params['id'];
    if(this.accountID != null){
      this.searchHistoryFormGroup = this.formBuilder.group({
        searchAccount: this.formBuilder.control(this.accountID, [Validators.required]),
      });
      this.handleGetHistory();
    }
    else{
      this.searchHistoryFormGroup = this.formBuilder.group({
        searchAccount: this.formBuilder.control(null, [Validators.required]),
      });
    }
  }

  handleGetHistory(){
    this.accountID = this.searchHistoryFormGroup.value["searchAccount"];
    this.accountService.accountHistory(this.accountID, 0, 5).subscribe({
      next: resp => {
        this.accountHistory = resp;
      },
      error: err => {
        alert("Account does not exist.")
        this.searchHistoryFormGroup.reset()
      }
    });
  }
}
