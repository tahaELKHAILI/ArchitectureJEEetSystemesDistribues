export interface OperationModel {
  accountId: string;
  balance: number;
  currentPage: number;
  totalPages: number;
  pageSize: number;
  operationsDTOS: OperationDto[];
}

export interface OperationDto {
  id: number;
  date: Date;
  amount: number;
  operationType: string;
  description: string;
  transactionID: string;
}
