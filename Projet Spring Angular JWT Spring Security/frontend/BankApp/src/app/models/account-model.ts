export interface AccountModel {
  id: string;
  balance: number;
  type: string;
  accountStatus: string;
  customerDto: CustomerDto;
  interestRate: number;
  overdraft: number;
}

export interface CustomerDto {
  email: string,
  id: string;
  name: string;
}
