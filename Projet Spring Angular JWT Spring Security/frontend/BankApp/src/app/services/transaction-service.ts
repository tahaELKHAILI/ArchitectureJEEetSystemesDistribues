import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { TransactionModel } from '../models/transaction-model';
import { environment } from '../../environments/environment';
import { TransferModel } from '../models/transfer-model';

@Injectable({
  providedIn: 'root',
})
export class TransactionService {
  constructor(private http: HttpClient) {}

  public creditTransaction(transactionModel:TransactionModel){
      return this.http.post(environment.backendHost+"/accounts/credit", transactionModel);
  }

  public debitTransaction(transactionModel: TransactionModel){
    return this.http.post(environment.backendHost+"/accounts/debit", transactionModel);
  }

  public transfer(transferModel:TransferModel){
    return this.http.post(environment.backendHost+"/accounts/transfer", transferModel);
  }
}
