import { AccountModel } from './account-model';

export interface SavingAccountCreation{
  initialBalance: number;
  interestRate: number;
  customerID: string;
}
