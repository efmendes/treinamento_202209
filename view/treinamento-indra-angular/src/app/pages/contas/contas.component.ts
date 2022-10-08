import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ICliente } from 'src/app/interfaces/cliente';
import { AlertaService } from 'src/app/services/alerta.service';
import { ClientesService } from 'src/app/services/clientes.service';
import { ContasService } from 'src/app/services/contas.service';
import { DataService } from 'src/app/services/data.service';
import { ExtratoService } from 'src/app/services/extrato.service';
import { IConta } from '../../interfaces/conta';

interface GetClientByCpfProps {
  cpfMascarado: string,
  observacoes: string,
  nome: string
}
@Component({
  selector: 'app-contas',
  templateUrl: './contas.component.html',
  styleUrls: ['./contas.component.css']
})
export class ContasComponent {

  @ViewChild('submit_button') submit_button: ElementRef;

  status: string = "";
  error_message: string = "";

  accounts: IConta[] = [];
  clients: ICliente[] = [];

  actual_account_id: number = 0;
  actual_account_data: { number: string, agency: string } = {
    number: "",
    agency: ""
  }
  modal_type: "create" | "update" | "delete" | "transaction" | "";
  is_modal_open: boolean = false;
  is_client_fetched: boolean = false;

  formInitialValue: IConta = {
    agencia: "",
    numero: "",
    saldo: 0,
    cliente: {
      nome: "",
      email: "",
      cpf: "",
      observacoes: "",
      ativo: true,
      id: 0
    }
  }

  constructor(
    private contaService: ContasService,
    private clienteService: ClientesService,
    private alertsService: AlertaService,
    private fb: FormBuilder,
    private renderer: Renderer2,
    private dataeService: DataService,
    private extratoService: ExtratoService,
  ) {
    this.dataeService.clickCreate().subscribe(() => { this.handleCreateModalOpen() });
    this.getAllAccounts();
    this.getAllClients();
  }

  accountForm = this.fb.group({
    agencia: ['', Validators.required],
    numero: ['', Validators.required],
    saldo: [0, Validators.required],
    cliente: this.fb.group({
      nome: [''],
      email: [''],
      cpf: ['', [
        Validators.required,
        Validators.minLength(11),
        Validators.maxLength(11),
      ]],
      observacoes: [''],
      ativo: [true],
      id: [0]
    })
  });

  resetForm() {
    this.modal_type = "";
    this.actual_account_data = {
      number: "",
      agency: ""
    };
    this.formInitialValue = {
      agencia: "",
      numero: "",
      saldo: 0,
      cliente: {
        nome: "",
        email: "",
        cpf: "",
        observacoes: "",
        ativo: true,
        id: 0
      }
    }
    this.handleCloseModal();
    this.actual_account_id = 0;
    this.accountForm.patchValue(this.formInitialValue);
  }

  handleCloseModal() {
    this.is_modal_open = false;
    this.modal_type = "";
  }

  onSubmit() {
    if (!this.accountForm.valid) {
      this.alertsService.errorAlert("Formulario Invalido");
      return;
    }

    if (this.modal_type === "update") {
      return this.handleAccountEdition();
    }

    if (this.modal_type === "create") {
      return this.handleAccountCreation();
    }

    if (this.modal_type === "delete") {
      return this.handleAccountDeletion();
    }

    if (this.modal_type === "transaction") {
      this.handleTransactions();
    }

  }

  handleTransactions() {
    const { agencia, numero, saldo, cliente } = this.accountForm.value
    if (this.accountForm.value['cliente']!['nome'] === 'income') {
      this.extratoService.depositFromAccount({ agencia: agencia!, numeroConta: numero!, valor: saldo! }).subscribe({
        next: () => {
          this.status = 'Operation successful';

          this.getAllAccounts();

          this.alertsService.successAlert(this.status);

          this.accountForm.patchValue(this.formInitialValue);

          this.resetForm();
        },
        error: error => {
          this.error_message = error.message;
          console.error(this.error_message);
          this.alertsService.errorAlert("There was an error while doing the transaction")
        }
      })
    }

    if (this.accountForm.value['cliente']!['nome'] === 'outcome') {
      this.extratoService.withdrawFromAccount({ agencia: agencia!, numeroConta: numero!, valor: saldo! }).subscribe({
        next: () => {
          this.status = 'Operation successful';

          this.getAllAccounts();

          this.alertsService.successAlert(this.status);

          this.accountForm.patchValue(this.formInitialValue);

          this.resetForm();
        },
        error: error => {
          this.error_message = error.message;
          console.error(this.error_message);
          this.alertsService.errorAlert("There was an error while doing the transaction")
        }
      })
    }

    if (this.accountForm.value['cliente']!['nome'] === 'transference') {
      this.extratoService.transferenceFromAccountToAccount({ agenciaOrigem: agencia!, numeroContaOrigem: numero!, valor: saldo!, numeroContaDestino: cliente?.cpf!, agenciaDestino: cliente?.email! }).subscribe({
        next: () => {
          this.status = 'Operation successful';

          this.getAllAccounts();

          this.alertsService.successAlert(this.status);

          this.accountForm.patchValue(this.formInitialValue);

          this.resetForm();
        },
        error: error => {
          this.error_message = error.message;
          console.error(this.error_message);
          this.alertsService.errorAlert("There was an error while doing the transaction")
        }
      })
    }
  }

  getAllAccounts() {
    this.contaService.getAllAccounts().subscribe((accounts: IConta[]) => {
      this.accounts = accounts;
      this.orderAccountsByField();
    });
  }

  getAllClients() {
    this.clienteService.getAllClients().subscribe((clients: ICliente[]) => {
      this.clients = clients;
    });
  }

  getAccountById(id: number) {
    this.modal_type = "update";
    this.is_modal_open = true;
    this.actual_account_id = id;
    this.accountForm.patchValue(this.formInitialValue);

    this.contaService.getAccountById(id).subscribe({
      next: (account: IConta) => {
        this.is_client_fetched = true;

        const { numero, agencia, cliente, saldo } = account;
        this.actual_account_data = {
          number: numero,
          agency: agencia,
        }
        this.accountForm.patchValue({
          numero,
          agencia,
          cliente,
          saldo
        });
      },
      error: error => {
        this.error_message = error.message;
        console.error(this.error_message);
        this.alertsService.errorAlert("Ocorreu um erro ao buscar as informacoes da conta")
      }
    })
  }

  orderAccountsByField(campo: any = { value: "id" }) {
    const filtered_field: "id" | "agencia" | "numero" | "saldo" = campo.value
    this.accounts.sort((a, b) => {
      if (a[filtered_field]! < b[filtered_field]!) {
        return -1;
      }
      if (a[filtered_field]! > b[filtered_field]!) {
        return 1;
      }
      return 0;
    });
  }

  checkIfAccountAndNumberExists() {
    const { cliente, ...rest } = this.accountForm.value
    if (this.modal_type === 'transaction') {
      if (this.accountForm.value['cliente']!['cpf'] && this.accountForm.value['cliente']!['email']) {
        const filteredAccount: IConta = this.accounts.filter((account) => (account.agencia === cliente!.email) && (account.numero === cliente!.cpf))[0]
        if (!filteredAccount) {
          this.alertsService.errorAlert("This Acc. Number and Agency does not match with any existing account.")
        }
      }
      if (this.accountForm.value['agencia'] && this.accountForm.value['numero']) {
        const filteredAccount: IConta = this.accounts.filter((account) => (account.agencia === rest!.agencia) && (account.numero === rest!.numero))[0]
        if (!filteredAccount) {
          this.alertsService.errorAlert("This Acc. Number and Agency does not match with any existing account.")
        }
      }
    }
  }

  getClientByCpf() {
    this.is_client_fetched = false;
    this.clienteService.getClientByCpf(this.accountForm.value['cliente']!['cpf'] as string).subscribe({
      next: (cliente: GetClientByCpfProps) => {

        const filteredClient: ICliente = this.clients.filter(client =>
        (client.cpf.substring(0, 3) === cliente.cpfMascarado.substring(0, 3)
          && (client.nome === cliente.nome)
          && (client.observacoes === cliente.observacoes))
        )[0]

        this.accountForm.patchValue({
          ...this.accountForm.value,
          cliente: {
            ...filteredClient
          }
        });

        this.is_client_fetched = true;
      },
      error: error => {
        this.is_client_fetched = false;
        this.error_message = error.message;
        console.error("There was an error!" + error.message);
        this.alertsService.errorAlert("This CPF does not match with any existing client.");
      }
    })
  }

  handleAccountCreation() {
    this.contaService.createAccount({
      ...this.accountForm.value
    } as IConta)
      .subscribe({
        next: data => {
          this.status = 'Creation successful';

          this.getAllAccounts();

          this.alertsService.successAlert(this.status);

          this.accountForm.patchValue(this.formInitialValue);

          this.resetForm();
        },
        error: error => {
          this.error_message = error.message;
          console.error('There was an error!', error);
        }
      });
  }

  handleAccountEdition() {
    this.contaService.updateAccountById(this.actual_account_id, {
      ...this.accountForm.value,
      id: this.actual_account_id
    } as IConta)
      .subscribe({
        next: data => {
          this.status = 'Edition successful';

          this.getAllAccounts();

          this.alertsService.successAlert(this.status);

          this.accountForm.patchValue(this.formInitialValue);

          this.resetForm();
        },
        error: error => {
          this.error_message = error.message;
          console.error('There was an error!', error);
        }
      });
  }

  handleAccountDeletion() {
    this.contaService.deleteAccountById(this.actual_account_id)
      .subscribe({
        next: data => {
          this.status = 'Delete successful';
          this.alertsService.successAlert(this.status)
          this.getAllAccounts();
          this.resetForm();
        },
        error: error => {
          this.error_message = error.message;
          console.error('There was an error!', error);
        }
      });
  }

  handleSubmitModal() {
    this.renderer.selectRootElement(this.submit_button["nativeElement"]).click();
  }

  handleDeleteModalOpen(id: number) {
    this.resetForm();
    this.actual_account_id = id;
    this.modal_type = "delete";
    this.is_modal_open = true;

    this.contaService.getAccountById(id)
      .subscribe({
        next: (conta: IConta) => {
          const { id, ...rest } = conta
          this.actual_account_data = {
            number: rest.numero,
            agency: rest.agencia
          };
          this.accountForm.patchValue(rest);
        },
        error: error => {
          this.error_message = error.message;
          console.error('There was an error!', this.error_message);
        }
      })
  }

  handleCreateModalOpen() {
    this.is_modal_open = true;
    this.modal_type = "create";
  }

  handleTransactionModalOpen(id: number) {
    this.actual_account_id = id;
    this.modal_type = "transaction";
    this.is_modal_open = true;

    this.contaService.getAccountById(id)
      .subscribe({
        next: (conta: IConta) => {
          const { id, numero, saldo, agencia, ...rest } = conta
          this.actual_account_data = {
            number: numero,
            agency: agencia
          };
          this.accountForm.patchValue({
            numero,
            agencia
          });
        },
        error: error => {
          this.error_message = error.message;
          console.error('There was an error!', this.error_message);
        }
      })
  }
}
