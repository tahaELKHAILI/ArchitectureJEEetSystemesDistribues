import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { TransactionService } from '../../services/transaction-service';
import { TransactionModel } from '../../models/transaction-model';
import { ActivatedRoute } from '@angular/router';
import { TransferModel } from '../../models/transfer-model';
import { OperationsComponent } from '../operations-component/operations-component';

@Component({
  selector: 'app-transactions-component',
  imports: [ReactiveFormsModule],
  templateUrl: './transactions-component.html',
  styleUrl: './transactions-component.css',
})
export class TransactionsComponent implements OnInit {
  protected transactionFromGroup!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private transactionService: TransactionService,
    private route: ActivatedRoute,
    private operationsComponent: OperationsComponent,
  ) {}

  ngOnInit() {
    this.transactionFromGroup = this.formBuilder.group({
      operationType: this.formBuilder.control('CREDIT', [Validators.required]),
      amount: this.formBuilder.control(null, [Validators.required, Validators.min(1)]),
      description: this.formBuilder.control(''),
    });
    this.transactionFromGroup.get('operationType')?.valueChanges.subscribe((type) => {
      if (type == 'TRANSFER') {
        this.transactionFromGroup.addControl(
          'destination',
          this.formBuilder.control('', [Validators.required]),
        );
      } else {
        this.transactionFromGroup.removeControl('destination');
      }
    });
  }

  protected handleTransaction() {
    let accountID: string = this.route.snapshot.params['id'];
    let opType = this.transactionFromGroup.value['operationType'];
    if (opType != 'TRANSFER') {
      let transaction: TransactionModel = this.transactionFromGroup.value;
      transaction.accountID = accountID;
      transaction.transactionID = crypto.randomUUID();

      if (opType == 'CREDIT') {
        this.transactionService.creditTransaction(transaction).subscribe({
          next: (value) => {
            this.transactionFromGroup.reset();
            this.operationsComponent.handleGetHistory();
          },
          error: (err) => {
            alert(err['message']);
          },
        });
      } else {
        this.transactionService.debitTransaction(transaction).subscribe({
          next: (value) => {
            this.transactionFromGroup.reset();
            this.operationsComponent.handleGetHistory();
          },
          error: (err) => {
            alert(err['message']);
          },
        });
      }
    } else {
      let transfer: TransferModel = this.transactionFromGroup.value;
      transfer.sourceAccountID = accountID;
      transfer.destinationAccountID = this.transactionFromGroup.value['destination'];
      transfer.amount = this.transactionFromGroup.value['amount'];

      this.transactionService.transfer(transfer).subscribe({
        next: (value) => {
          this.transactionFromGroup.reset();
          this.operationsComponent.handleGetHistory();
        },
        error: (err) => {
          alert(err['message']);
        },
      });
    }
  }
}
