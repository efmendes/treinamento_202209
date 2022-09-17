import { ICliente } from './cliente';
export interface IConta {
  id: number;
  cliente: ICliente;
  agencia: string;
  numero: string;
  saldo: number;
}
