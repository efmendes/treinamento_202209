export interface ICliente {
  id: number;
  nome: string;
  cpf: string;
  email: string;
  ativo?: boolean;
  observacoes?: string;
}
