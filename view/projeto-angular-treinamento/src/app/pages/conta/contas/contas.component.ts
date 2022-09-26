import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { ContasService } from 'src/app/services/contas.service';
import { IConta } from 'src/app/interfaces/conta';

@Component({
  selector: 'app-contas',
  templateUrl: './contas.component.html',
  styleUrls: ['./contas.component.css']
})
export class ContasComponent implements OnInit {

  contas: IConta[] = [];

  constructor(private contaService: ContasService,
    private router: Router,
    private location: Location,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.buscarTodosContas();
  }

  buscarTodosContas() {
    this.contaService.listarTodasContas().subscribe((contas: IConta[]) => {
      this.contas = contas;
    });
  }
  onExcluir(id: string) {
    this.contaService.removerConta(id).subscribe();
  }

  cadastro: boolean = false;

  onCadastro(){
    this.cadastro = !this.cadastro;
  }
  closed(isClosed: boolean){
    this.cadastro = isClosed;
  }

}
