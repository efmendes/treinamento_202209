import { ICliente } from 'src/app/interfaces/cliente';
import { ClientesService } from 'src/app/services/clientes.service';
import { ActivatedRoute } from '@angular/router';
import { ContasService } from 'src/app/services/contas.service';
import { IConta } from 'src/app/interfaces/conta';
import { Component, OnInit } from '@angular/core';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-cliente-contas',
  templateUrl: './cliente-contas.component.html',
  styleUrls: ['./cliente-contas.component.css']
})
export class ClienteContasComponent implements OnInit {

  clienteNome = '';
  cliente!: ICliente;
  clienteCpf = '';
  contas: IConta[] = [];


  constructor(private contasService: ContasService, private clientesService: ClientesService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.clienteCpf = String(this.route.snapshot.paramMap.get('cpf'));
    if(this.clienteCpf) {
      this.contasService.buscarContaPorCpf(this.clienteCpf).subscribe((contas: IConta[]) => {
        this.contas = contas;
      });
      this.clientesService.buscarClientePorCpf(this.clienteCpf).subscribe((cliente: ICliente) => {
        this.cliente = cliente;
      })
    };
  }

}
