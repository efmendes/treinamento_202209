import { Cliente } from './cliente';
export interface ContaBancaria {
  id: string,
  agencia: string,
  numero: string,
  saldo: number,
  cliente: Cliente;
}
