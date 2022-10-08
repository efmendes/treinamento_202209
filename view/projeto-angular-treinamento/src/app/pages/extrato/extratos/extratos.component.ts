import { NonNullableFormBuilder, Validators } from '@angular/forms';
import { ExtratoService } from 'src/app/services/extrato.service';

import { LocalStorageService } from './../../../services/local-storage.service';
import { Extrato } from './../../../interfaces/extrato';
import { Component, OnInit } from '@angular/core';
import { AlertService } from 'src/app/services/alert.service';



@Component({
  selector: 'app-extratos',
  templateUrl: './extratos.component.html',
  styleUrls: ['./extratos.component.css']
})
export class ExtratosComponent implements OnInit {

  extratos: Extrato[] = [];
  cpf: string = '';

  data = this.formBuilder.group({
    date: ['', Validators.required]
  });

  intervalo = this.formBuilder.group({
    dataIni: ['',Validators.required],
    dataFim: ['',Validators.required]
  });


  constructor(private extratoService: ExtratoService,
    private localStorage: LocalStorageService,
    private formBuilder: NonNullableFormBuilder,
    private alert: AlertService) {}

  ngOnInit(): void {
    this.buscarTodosExtratos();
  }

  buscarTodosExtratos() {
    this.cpf = this.localStorage.get('cpf');
    this.extratoService.listarTodosExtratos(this.cpf).subscribe((extratos: Extrato[]) => {
      if(extratos){
        this.extratos = extratos;
      }}, (erro: Error) => {
        this.alert.alertaErro('Nenhum extrato encontrado ou você não está loggado no sistema!')
      });
  }

  onData(){
    const data:string = String(this.data.value.date).replace("/", "").replace("/", "");
    console.log(data);
    this.extratoService.listarExtratosPorData(this.cpf, data).subscribe((extratos: Extrato[]) => {
      if(extratos){
        this.extratos = extratos;
        this.alert.alertaSucesso('Busca efetuada com sucesso!')
      }}, (erro: Error) => {
        this.alert.alertaErro('Digite uma data válida!')
      });
  }

  onIntervalo(){
    const dataIni:string = String(this.intervalo.value.dataIni).replace("/", "").replace("/", "");
    const dataFim:string = String(this.intervalo.value.dataFim).replace("/", "").replace("/", "");
    this.extratoService.listarExtratosPorIntervalo(this.cpf, dataIni, dataFim).subscribe((extratos: Extrato[]) => {
      if(extratos){
        this.extratos = extratos;
        this.alert.alertaSucesso('Busca efetuada com sucesso!')
      }}, (erro: Error) => {
        this.alert.alertaErro('Digite uma data válida!')
      });
  }
}
