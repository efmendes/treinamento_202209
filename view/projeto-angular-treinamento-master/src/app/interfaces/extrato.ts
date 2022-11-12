export interface Extrato {
    id?: number;
    agencia: string;
    conta: string;
    dataInicio: string;
    dataFim: string;
    tipoOperacao?: string;
    valor?: number;
    dataOperacao?: string;
}
