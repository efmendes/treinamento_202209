export interface ICliente {
    id: number;
    ativo?: boolean;
    cpf: string;
    observacoes?: string;
    nome: string;
    email: string;
}