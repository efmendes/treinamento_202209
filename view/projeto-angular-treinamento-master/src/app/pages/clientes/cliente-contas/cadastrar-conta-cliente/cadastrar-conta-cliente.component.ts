import { ContasService } from './../../../../services/contas.service';
import { ClientesService } from './../../../../services/clientes.service';
import { AlertasService } from './../../../../services/alertas.service';
import { Router, ActivatedRoute } from '@angular/router';
import { IConta } from './../../../../interfaces/conta';
import { ICliente } from './../../../../interfaces/cliente';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-cadastrar-conta-cliente',
  templateUrl: './cadastrar-conta-cliente.component.html',
  styleUrls: ['./cadastrar-conta-cliente.component.css']
})
export class CadastrarContaClienteComponent implements OnInit {

  constructor(private contasService: ContasService,
    private clientesService: ClientesService,
    private alertaService: AlertasService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

 
  idCliente = 0;

  cliente: ICliente = {
      ativo: true,
      cpf: '',
      email: '',
      id: 0,
      nome: '',
      observacoes: ''
  };
  conta!: IConta;
  mensagemValidacao: string = '';

  contaForm = new FormGroup({
    agencia: new FormControl('', [Validators.required, Validators.maxLength(4), Validators.minLength(4)]),
    cliente: new FormControl(this.cliente, Validators.required),
    numero: new FormControl('', Validators.required),
    saldo: new FormControl(Number(''), [Validators.required, Validators.min(10)])
  });

  ngOnInit(): void {
    this.idCliente = Number(this.route.snapshot.paramMap.get('id'));
    if (this.idCliente !== 0) {
       this.clientesService.buscarClientePorId(this.idCliente).subscribe((cliente: ICliente) => {
        this.cliente = cliente;
        console.log(this.cliente);
      }, (error) => {
        console.error(error);
      });
    };
  }

  cadastrarConta() {

      const conta = this.contaForm.value as IConta;

      if(this.cliente !== undefined) {
        conta.cliente = this.cliente;
        console.log(conta);
        this.contasService.cadastrarConta(conta).subscribe(() => {
          this.alertaService.alertaSucesso('Conta cadastrada com sucesso!');
          this.router.navigateByUrl(`/cliente/minhas-contas/${this.cliente.cpf}`);
        }, (error) => {
          console.error(error);
          this.alertaService.alertaErro('Verifique as informações e tente novamente!');
        });
      }
  };

}
