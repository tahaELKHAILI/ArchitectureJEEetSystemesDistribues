import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../services/account-service';
import { ActivatedRoute } from '@angular/router';
import { OperationModel } from '../../models/operation-model';
import { Observable } from 'rxjs';
import { AsyncPipe, DatePipe, DecimalPipe } from '@angular/common';

@Component({
  selector: 'app-operations',
  imports: [AsyncPipe, DecimalPipe, DatePipe],
  templateUrl: './operations-component.html',
  styleUrl: './operations-component.css',
})
export class OperationsComponent implements OnInit {
  accountID!: string;
  operationsObservable!: Observable<OperationModel>;
  constructor(
    private accountService: AccountService,
    private route: ActivatedRoute,
  ) {}

  ngOnInit() {
    this.accountID = this.route.snapshot.params['id'];
    this.handleGetHistory(this.accountID);
  }

  handleGetHistory(accountID: String) {
    this.operationsObservable = this.accountService.accountHistory(this.accountID, 0, 5);
    console.log(this.operationsObservable);
  }
}
