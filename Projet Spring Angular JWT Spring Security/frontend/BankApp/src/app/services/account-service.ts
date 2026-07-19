import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { AccountModel } from '../models/account-model';
import { CurrentAccountCreation } from '../models/current-account-creation';
import { OperationModel } from '../models/operation-model';
import { SavingAccountCreation } from '../models/saving-account-creation';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  constructor(private http: HttpClient) {}

  public getAccountsByCustomer(customerID: String): Observable<Array<AccountModel>> {
    return this.http.get<Array<AccountModel>>(
      environment.backendHost + '/accounts/customer/' + customerID,
    );
  }
  public addCurrentAccount(currentAccount: CurrentAccountCreation): Observable<AccountModel> {
    return this.http.post<AccountModel>(
      environment.backendHost + '/accounts/current',
      currentAccount,
    );
  }

  public addSavingAccount(savingAccount: SavingAccountCreation): Observable<AccountModel> {
    return this.http.post<AccountModel>(
      environment.backendHost + '/accounts/saving',
      savingAccount,
    );
  }

  public accountHistory(accountID: string, page: number, size: number): Observable<OperationModel> {
    return this.http.get<OperationModel>(
      environment.backendHost +
        '/accounts/transactions/' +
        accountID +
        '/?page=' +
        page +
        '&size=' +
        size,
    );
  }
}
