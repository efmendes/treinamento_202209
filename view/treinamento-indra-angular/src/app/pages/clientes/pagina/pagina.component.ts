import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ICliente } from 'src/app/interfaces/cliente';
import { AlertaService } from 'src/app/services/alerta.service';
import { ClientesService } from 'src/app/services/clientes.service';

@Component({
  selector: 'app-pagina',
  templateUrl: './pagina.component.html',
  styleUrls: ['./pagina.component.css']
})
export class PaginaComponent implements OnInit {

  client_id: number = 0;
  client: ICliente = {
    cpf: "",
    email: "",
    nome: "",
    ativo: true,
    id: 0,
    observacoes: "",
  }

  constructor(private route: ActivatedRoute, private alertsService: AlertaService, private clienteService: ClientesService) { }

  ngOnInit(): void {
    this.client_id = Number(this.route.snapshot.paramMap.get('id'))
    if (this.client_id > 0) {
      this.getClient()
    }
  }

  getClient() {
    this.clienteService.getClientById(this.client_id).subscribe({
      next: (client: ICliente) => {
        this.client = client
      },
      error: (err) => {
        console.error(err);
      }
    })
  }

  getClientAccounts() {

  }


}
