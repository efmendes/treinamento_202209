import { FormGroup, FormControl, Validators } from '@angular/forms';
import { IConta } from './../../../interfaces/conta';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertasService } from './../../../services/alertas.service';
import { ClientesService } from './../../../services/clientes.service';
import { ContasService } from './../../../services/contas.service';
import { Component, OnInit } from '@angular/core';
import { ICliente } from 'src/app/interfaces/cliente';

@Component({
  selector: 'app-editar-conta',
  templateUrl: './editar-conta.component.html',
  styleUrls: ['./editar-conta.component.css']
})
export class EditarContaComponent implements OnInit {

  idConta = 0;
  idCliente = 0;
  conta!: IConta;
  cliente!: ICliente;
  mensagemValidacao = '';

  contaForm = new FormGroup ({
    agencia: new FormControl('', [Validators.required, Validators.maxLength(4), Validators.minLength(4)]),
    cliente: new FormControl(this.cliente, Validators.required),
    numero: new FormControl('', Validators.required),
    saldo: new FormControl(0, [Validators.required, Validators.min(10)])
  })

  constructor(private contasService: ContasService,
              private alertasService: AlertasService, 
              private router: Router, 
              private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.idConta = Number(this.route.snapshot.paramMap.get('id'));
    if (this.idConta !== 0) {
      this.contasService.buscarContaPorId(this.idConta).subscribe((conta: IConta) => {
        this.contaForm.setValue({
          	agencia: conta.agencia,
            cliente: conta.cliente,
            numero: conta.numero,
            saldo: conta.saldo
        });
        this.cliente = conta.cliente;
      }, (error) => {
        console.error(error);
      });
    }
  }

  editarConta() {
    const conta = this.contaForm.value as IConta;

    if(this.idConta) {
      if(this.cliente !== undefined) {
        conta.id = this.idConta;
        conta.cliente = this.cliente;
        this.contasService.atualizarConta(conta).subscribe(() => {
          this.alertasService.alertaSucesso('Conta atualizada com sucesso!');
          this.router.navigateByUrl('/contas');
        }, (error) => {
          console.error(error);
        });
      } else {
          this.alertasService.alertaErro('Verifique as informações e tente novamente!');
      }
    }
  }

}
