import { ICliente } from "./cliente";

export interface IConta {
  id: number;
  agencia: string;
  cliente: ICliente;
  numero: string;
  saldo: number;
}
