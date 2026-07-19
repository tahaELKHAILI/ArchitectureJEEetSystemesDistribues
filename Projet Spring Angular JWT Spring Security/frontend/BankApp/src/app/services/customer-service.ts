import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { CustomerModel } from '../models/customer-model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  constructor(private http: HttpClient) {}

  public getCusomters(): Observable<Array<CustomerModel>> {
    return this.http.get<Array<CustomerModel>>(environment.backendHost + '/customers');
  }

  public searchCustomers(keyword: string): Observable<Array<CustomerModel>> {
    return this.http.get<Array<CustomerModel>>(
      environment.backendHost + '/customers/search?keyword=' + keyword,
    );
  }

  public deleteCustomer(id: string) {
    return this.http.delete(environment.backendHost + '/customers/' + id);
  }

  public addCustomer(customer: CustomerModel){
    return this.http.post<CustomerModel>(environment.backendHost+"/customers", customer)
  }

  public updateCustomer(customer: CustomerModel){
    return this.http.put<CustomerModel>(
      environment.backendHost + '/customers/' + customer.id, customer,
    );
  }
}
