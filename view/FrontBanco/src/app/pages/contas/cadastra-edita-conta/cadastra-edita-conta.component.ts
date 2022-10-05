import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { IConta } from 'src/app/interfaces/conta';
import { IContaRequest } from 'src/app/interfaces/contaRequest';
import { ClientesService } from 'src/app/services/clientes.service';
import { ContasService } from 'src/app/services/contas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cadastra-edita-conta',
  templateUrl: './cadastra-edita-conta.component.html',
  styleUrls: ['./cadastra-edita-conta.component.css']
})
export class CadastraEditaContaComponent implements OnInit {

  formConta: FormGroup = new FormGroup({
    id: new FormControl(null),
    agencia: new FormControl('', Validators.required),
    numero: new FormControl('', Validators.required),
    saldo: new FormControl('', Validators.required),
    idCliente: new FormControl('', Validators.required),
  });
  constructor(
    private clienteService: ClientesService,
    private contaService: ContasService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (Number(id)) {
      this.contaService.buscarPorId(Number(id)).subscribe(result => {
        this.preencherFormValue(result);
      }, error => {
        console.error(error);
      });
    }
  }

  preencherFormValue(conta: IConta) {
    this.formConta =  new FormGroup({
      id: new FormControl(conta.id),
      agencia: new FormControl(conta.agencia, Validators.required),
      numero: new FormControl(conta.numero, Validators.required),
      saldo: new FormControl(conta.saldo, [Validators.required, Validators.email]),
      idCliente: new FormControl(conta.cliente.id, Validators.required),
    });
  }

  
  enviar() {
    const contaRequest: IContaRequest = this.formConta.value;

    this.clienteService.buscarPorId(Number(contaRequest.idCliente)).subscribe(
      (result) => {
        const conta: IConta = {
          id: contaRequest.id,
          agencia: contaRequest.agencia,
          numero: contaRequest.numero,
          saldo: contaRequest.saldo,
          cliente: result,
        };
        this.contaService.cadastrar(conta).subscribe(
          (result) => {
            console.log(result);
            Swal.fire('Cadastrado com sucesso!', 'Sucesso', 'success');
            this.router.navigate(['/contas']);
          },
          (error) => {
            console.error(error.message);
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );

  }

}
