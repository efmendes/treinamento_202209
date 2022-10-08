import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { IConta } from 'src/app/interfaces/conta';
import { IDepositoSaque } from 'src/app/interfaces/deposito-saque';
import { ContasService } from 'src/app/services/contas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-deposito',
  templateUrl: './deposito.component.html',
  styleUrls: ['./deposito.component.css']
})
export class DepositoComponent implements OnInit {

  formConta: FormGroup = new FormGroup({
    agencia: new FormControl('', Validators.required),
    numeroConta: new FormControl('', Validators.required),
    valor: new FormControl('', Validators.required),
  });
  constructor(
    private contaService: ContasService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id){
      this.contaService.buscarPorIdString(id).subscribe((result: IConta) => {
        this.formConta = this.preencheDeposito(result);
      })
    }
  }

  deposito() {
    const deposito: IDepositoSaque = this.formConta.value;
    this.contaService.deposito(deposito).subscribe((result => {
      Swal.fire('Sucesso!', 'Depósito concluído!', 'success')
      this.router.navigate(['/contas']);
    }), error => {
      console.error(error);
    });
  }

  preencheDeposito(conta?: IConta){
    return this.formConta = new FormGroup({
      agencia: new FormControl(conta?.agencia, Validators.required),
      numeroConta: new FormControl(conta?.numero, Validators.required),
      valor: new FormControl(null, Validators.required),
    })
  }
}
