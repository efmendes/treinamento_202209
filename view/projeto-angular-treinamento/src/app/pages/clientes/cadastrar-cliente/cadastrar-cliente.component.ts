import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { onlyCharacteresRegex } from 'src/app/constants/regexExpressions';
import { ICliente } from 'src/app/interfaces/cliente';
import { AlertasService } from 'src/app/services/alertas.service';
import { ClientesService } from 'src/app/services/clientes.service';

@Component({
  selector: 'app-cadastrar-cliente',
  templateUrl: './cadastrar-cliente.component.html',
  styleUrls: [
    './cadastrar-cliente.component.css',
    '../../../app.component.css',
  ],
})
export class CadastrarEditarClienteComponent implements OnInit {
  constructor(
    private clientesService: ClientesService,
    private route: ActivatedRoute,
    private router: Router,
    private alertaService: AlertasService
  ) {}

  newUser = new FormGroup({
    nome: new FormControl('', [
      Validators.required,
      Validators.pattern(onlyCharacteresRegex),
    ]),
    email: new FormControl('', [Validators.required, Validators.email]),
    cpf: new FormControl('', Validators.required),
    observacoes: new FormControl(''),
    ativo: new FormControl(true),
  });

  idCliente = 0;

  ngOnInit(): void {
    this.idCliente = Number(this.route.snapshot.paramMap.get('id'));
    if (this.idCliente)
      this.clientesService.getCliente(this.idCliente).subscribe((cliente) => {
        this.newUser.setValue({
          nome: cliente.nome,
          email: cliente.email,
          cpf: cliente.cpf,
          observacoes: cliente.observacoes || '',
          ativo: cliente.ativo || false,
        });
      });
  }

  onSubmit() {
    const cliente: ICliente = this.newUser.value as ICliente;
    if (this.idCliente) {
      this.clientesService
        .editarCliente({ ...cliente, id: this.idCliente })
        .subscribe(
          () => {
            this.alertaService.alertaSucess(
              'Edição realizada com sucesso!',
              () => this.router.navigateByUrl('/clientes')
            );
          },
          (error) =>
            this.alertaService.alertaFalha('Não foi possível editar o usuário')
        );
    } else {
      this.clientesService.cadastrarCliente(cliente).subscribe(
        () => {
          this.alertaService.alertaSucess(
            'Criação realizada com sucesso!',
            () => location.replace('/clientes')
          );
        },
        (error) =>
          this.alertaService.alertaFalha('Não foi possível criar o usuário')
      );
    }
  }
}
