import { LocalStorageService } from './../../../services/local-storage.service';

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { ContasService } from 'src/app/services/contas.service';
import { IConta } from 'src/app/interfaces/conta';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-contas',
  templateUrl: './contas.component.html',
  styleUrls: ['./contas.component.css']
})
export class ContasComponent implements OnInit {

  contas: IConta[] = [];
  nomeCliente : boolean = false;
  cpf: string = '';

  createConta = new FormGroup({
    cpf: new FormControl('', Validators.required)
  });

  constructor(private contaService: ContasService,
    private router: Router,
    private location: Location,
    private route: ActivatedRoute,
    private localStorage: LocalStorageService) { }

  ngOnInit(): void {
    this.buscarTodosContas();
  }

  buscarTodosContas() {
    this.cpf = this.localStorage.get('cpf');
    this.contaService.listarContasPorCpf(this.cpf).subscribe((contas: IConta[]) => {
      this.contas = contas;
    });
  }
  onExcluir(id: number) {
    this.contaService.removerConta(id).subscribe();
  }

  onSave(){
    const cpf: string = this.localStorage.get('cpf');
    this.contaService.adcionarConta(cpf).subscribe((conta: IConta) =>
    Swal.fire({title: 'Conta Bancária Criada',
    text: `Conta: ${conta.numero} e Agência: ${conta.agencia}`,
    icon: 'success'})
    );
  }

}
