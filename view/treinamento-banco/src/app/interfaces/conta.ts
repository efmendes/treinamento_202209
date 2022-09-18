import { ICliente } from "./cliente";

export interface IConta {
    agencia: string,
    cliente: ICliente,
    id: number,
    numero: string,
    saldo: number
}