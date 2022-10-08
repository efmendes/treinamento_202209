import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ICliente } from 'src/app/interfaces/cliente';
import { IConta } from 'src/app/interfaces/conta';
import { AlertasService } from 'src/app/services/alertas.service';
import { ClientesService } from 'src/app/services/clientes.service';
import { ContasService } from 'src/app/services/contas.service';

@Component({
  selector: 'app-cadastrar-conta',
  templateUrl: './cadastrar-conta.component.html',
  styleUrls: ['./cadastrar-conta.component.css', '../../../app.component.css'],
})
export class CadastrarEditarContaComponent implements OnInit {
  constructor(
    private clienteService: ClientesService,
    private contasService: ContasService,
    private route: ActivatedRoute,
    private router: Router,
    private alertaService: AlertasService
  ) {}

  newConta = new FormGroup({
    agencia: new FormControl('', [Validators.required]),
    numero: new FormControl('', [Validators.required]),
    saldo: new FormControl(0, Validators.required),
    cliente: new FormControl('', Validators.required),
  });

  contaID = -1;
  clientes: ICliente[] = [];
  clienteSelecionado = {} as ICliente;

  ngOnInit(): void {
    this.buscarTodosClientes(() => {
      this.contaID = Number(this.route.snapshot.paramMap.get('id'));

      if (this.contaID) {
        this.contasService.getConta(this.contaID).subscribe((conta) => {
          this.newConta.setValue({
            agencia: conta.agencia,
            numero: conta.numero,
            saldo: conta.saldo,
            cliente: `${conta.cliente.id}`,
          });
          this.handleChange(conta.cliente.id || 0);
        });
      }
    });
  }

  buscarTodosClientes(successCallback: () => void) {
    this.clienteService.listarTodosClientes().subscribe(
      (clientes: ICliente[]) => {
        this.clientes = clientes;
        successCallback();
      },
      (err) =>
        this.alertaService.alertaFalha(
          'Não é possível cadastrar uma conta neste momento',
          () => location.replace('/contas')
        )
    );
  }

  handleChange(id: number) {
    this.newConta.controls['cliente'].setValue(`${id}`);
    this.clienteSelecionado =
      this.clientes.find((cliente) => cliente.id === id) || ({} as ICliente);
  }

  onSubmit() {
    const conta: IConta = {
      agencia: this.newConta.value.agencia,
      numero: this.newConta.value.numero,
      cliente: this.clienteSelecionado,
      saldo: this.newConta.value.saldo || 0,
    } as IConta;

    if (this.contaID) {
      this.contasService
        .editarConta(this.contaID, { ...conta, id: this.contaID })
        .subscribe(
          () =>
            this.alertaService.alertaSucess('Conta editada com sucesso!', () =>
              this.router.navigateByUrl('/contas')
            ),
          () =>
            this.alertaService.alertaFalha(
              'Não foi possível editar esta conta no momento.'
            )
        );
    } else {
      this.contasService.criarConta(conta).subscribe(
        () =>
          this.alertaService.alertaSucess('Conta criada com sucesso!', () =>
            this.router.navigateByUrl('/contas')
          ),
        () =>
          this.alertaService.alertaFalha(
            'Não foi possível criar esta conta no momento.'
          )
      );
    }
  }
}
