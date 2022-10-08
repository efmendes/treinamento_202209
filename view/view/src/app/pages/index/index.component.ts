import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  formExtrato: FormGroup = new FormGroup({
    agencia: new FormControl('', Validators.required),
    conta: new FormControl('', Validators.required),
  });
  formCPF: FormGroup = new FormGroup({
    cpf: new FormControl('', Validators.required),
  });

  constructor(private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {    
  }

  consultarContaCpf(){
    return this.router.navigate(['/contas/'+this.formCPF.get('cpf')?.value]);
  }

  consultarExtrato(){
    return this.router.navigate(['/contas/extrato/'+this.formExtrato.get('agencia')?.value+"/"+this.formExtrato.get('conta')?.value]);
  }

}
