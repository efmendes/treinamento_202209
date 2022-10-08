import { Component, ElementRef, EventEmitter, HostListener, Input, OnInit, Output, Renderer2, SimpleChanges, ViewChild } from '@angular/core';
import { FormGroup, FormGroupDirective } from '@angular/forms';
import { Router } from '@angular/router';
import { faX, faTrash, faArrowUp, faArrowDown, faArrowDownUpAcrossLine } from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-transaction-modal',
  templateUrl: './transaction-modal.component.html',
  styleUrls: ['./transaction-modal.component.css']
})
export class TransactionModalComponent implements OnInit {

  @Input() is_open: boolean = false;
  @Input() type: "create" | "update" | "delete" | "account" | "transaction" | "";
  @Input() client_name: string;
  @Input() account_data: { number: string, agency: string };
  @Input() is_client_fetched: boolean;

  @Output() onModalClose: EventEmitter<() => void> = new EventEmitter();
  @Output() onModalSubmit: EventEmitter<() => void> = new EventEmitter();
  @Output() checkClientCpf: EventEmitter<() => void> = new EventEmitter();
  @Output() checkAccount: EventEmitter<() => void> = new EventEmitter();

  @ViewChild('modal') modal: ElementRef;
  @ViewChild('modal_overlay') modal_overlay: ElementRef;

  @HostListener('window:keydown.escape', ['$event'])
  onKeydownHandler(event: KeyboardEvent) {
    this.handleCloseModal();
  }

  form: FormGroup;

  closeIcon = faX;
  trashIcon = faTrash;

  page: string;

  constructor(
    private renderer: Renderer2,
    private rootFormGroup: FormGroupDirective,
    private route: Router
  ) {
    this.page = String(this.route.url)
    this.renderer.listen('window', 'mousedown', (e: Event) => {
      if (e.target !== this.modal?.nativeElement && e.target === this.modal_overlay?.nativeElement) {
        this.handleCloseModal();
      }
    });
  }

  ngOnInit(): void {
    this.form = this.rootFormGroup.control;
  }

  handleModalSubmit() {
    this.onModalSubmit.emit();
  }

  handleCloseModal() {
    this.onModalClose.emit();
  }

  handleCheckClientCpf() {
    this.checkClientCpf.emit();
  }

  handleCheckIfAccountExists() {
    this.checkAccount.emit();
  }
}
