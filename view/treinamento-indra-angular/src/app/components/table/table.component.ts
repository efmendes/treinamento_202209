import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ICliente } from 'src/app/interfaces/cliente';
import { IConta } from 'src/app/interfaces/conta';
import { faTrash, faPencil, faPlus, faDollar } from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';
import { IExtrato } from 'src/app/interfaces/extrato';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit {

  @Input() clients: ICliente[] = [];
  @Input() accounts: IConta[] = [];
  @Input() extract: IExtrato[] = [];

  @Output() clientDeletion: EventEmitter<any> = new EventEmitter();
  @Output() getClientById: EventEmitter<any> = new EventEmitter();
  @Output() accountCreation: EventEmitter<any> = new EventEmitter();
  @Output() accountDeletion: EventEmitter<any> = new EventEmitter();
  @Output() getAccountById: EventEmitter<any> = new EventEmitter();
  @Output() makeATransaction: EventEmitter<any> = new EventEmitter();

  trashIcon = faTrash;
  pencilIcon = faPencil;
  plusIcon = faPlus;
  transactionIcon = faDollar;

  page: string;

  constructor(private route: Router) {
    this.page = String(this.route.url);
  }

  ngOnInit(): void {
  }

  handleDeleteClient(id: number) {
    this.clientDeletion.emit(id);
  }

  handleGetClientById(id: number) {
    this.getClientById.emit(id);
  }

  handleCreateAccount(client: ICliente) {
    this.accountCreation.emit(client);
  }

  handleDeleteAccount(id: number) {
    this.accountDeletion.emit(id)
  }

  handleGetAccountById(id: number) {
    this.getAccountById.emit(id)
  }

  handleMakeATransaction(id: number) {
    this.makeATransaction.emit(id)
  }

}
