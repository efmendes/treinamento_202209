export interface ISaqueDeposito {
  agencia: string;
  numeroConta: string;
  valor: number;
}

export interface ITransferencia {
  agenciaDestino: string;
  agenciaOrigem: string;
  numeroContaDestino: string;
  numeroContaOrigem: string;
  valor: number;
}

export interface IOperacao {
  id: number;
  agencia: string;
  numero: string;
  date: Date;
  operationType: string;
  value: number;
}
