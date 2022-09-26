import { ICliente } from "./cliente";

export interface IConta {
  id: string;
  agencia: string;
  cliente: ICliente;
  numero: string;
  saldo: number;
}
