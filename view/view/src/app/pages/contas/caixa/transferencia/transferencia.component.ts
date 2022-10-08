import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { IConta } from 'src/app/interfaces/conta';
import { ITransferencia } from 'src/app/interfaces/transferencia';
import { ContasService } from 'src/app/services/contas.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.css']
})
export class TransferenciaComponent implements OnInit {
  formTransfe: FormGroup = new FormGroup({
    agenciaOrigem: new FormControl('', Validators.required),
    agenciaDestino: new FormControl('', Validators.required),
    numeroContaOrigem: new FormControl('', Validators.required),
    numeroContaDestino: new FormControl('', Validators.required),
    valor: new FormControl('', Validators.required)
  });
  constructor(    private transferenciaService: ContasService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id){
      this.transferenciaService.buscarPorIdString(id).subscribe((result: IConta) => {
        this.formTransfe = this.preencheTransf(result);
      })
    }
  }

  transferir() {
    const transferencia: ITransferencia = this.formTransfe.value;
    this.transferenciaService.transferencia(transferencia).subscribe(result => {
      Swal.fire("Tudo certo!", 'Transferência realizada com sucesso!', 'success');
      this.router.navigate(['/contas']);
    }, error => {
      Swal.fire('Digitou algo errado?', 'Aconteceu um erro na sua transferência', 'error');
      console.error(error);
    });
  }

  preencheTransf(conta?: IConta){
    return this.formTransfe = new FormGroup({
      agenciaOrigem: new FormControl(conta?.agencia, Validators.required),
      numeroContaOrigem: new FormControl(conta?.numero, Validators.required),
      valor: new FormControl(null, Validators.required),
      agenciaDestino: new FormControl(null, Validators.required),
      numeroContaDestino: new FormControl(null, Validators.required),
    })
  }



}
