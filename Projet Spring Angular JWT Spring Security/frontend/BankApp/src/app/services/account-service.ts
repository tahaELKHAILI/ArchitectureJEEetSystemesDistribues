import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { AccountModel } from '../models/account-model';

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
}
