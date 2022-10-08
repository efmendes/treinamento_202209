import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { IConta } from 'src/app/interfaces/conta';
import { IDepositoSaque } from 'src/app/interfaces/deposito-saque';
import { ContasService } from 'src/app/services/contas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-saque',
  templateUrl: './saque.component.html',
  styleUrls: ['./saque.component.css']
})
export class SaqueComponent implements OnInit {

  formSaque: FormGroup = new FormGroup({
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
        this.formSaque = this.preencheSacar(result);
      })
    }
  }

  saque() {
    const saque: IDepositoSaque = this.formSaque.value;
    this.contaService.saque(saque).subscribe((result => {
      Swal.fire('Sucesso!', 'Saque concluído!', 'success')
      this.router.navigate(['/contas']);
    }), error => {
      Swal.fire('Erro no saque', 'Aconteceu alguma coisa no seu saque', 'error');
    });
  }
  preencheSacar(conta?: IConta){
    return this.formSaque = new FormGroup({
      agencia: new FormControl(conta?.agencia, Validators.required),
      numeroConta: new FormControl(conta?.numero, Validators.required),
      valor: new FormControl(null, Validators.required),
    })
  }

}