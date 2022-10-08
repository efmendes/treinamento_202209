import { Component, ElementRef, OnInit, Renderer2, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IExtrato } from 'src/app/interfaces/extrato';
import { DataService } from 'src/app/services/data.service';
import { ExtratoService } from 'src/app/services/extrato.service';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css']
})
export class ExtratoComponent implements OnInit {

  @ViewChild('submit_button') submit_button: ElementRef;

  error_message: string;

  is_modal_open: boolean = false;

  extrato: IExtrato[] = [];

  formInitialValue: { agency: string, account: string, startDate: string, endDate: string } = {
    agency: "",
    account: "",
    startDate: "",
    endDate: ""
  }

  searchExtractForm: FormGroup;
  constructor(
    private extratoService: ExtratoService,
    private fb: FormBuilder,
    private dataeService: DataService,
    private renderer: Renderer2
  ) {
    this.dataeService.clickCreate().subscribe(() => { this.handleSearchExtractModalOpen() });
    this.searchExtractForm = this.fb.group({
      agency: ['', Validators.required],
      account: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const { agency, account, startDate, endDate } = this.searchExtractForm.value
    this.getExtract(agency, account, startDate, endDate);
  }

  resetForm() {
    this.is_modal_open = false;
    this.searchExtractForm.patchValue(this.formInitialValue);
  }

  handleSearchExtractModalOpen() {
    this.is_modal_open = true;
  }

  handleSubmitModal() {
    this.renderer.selectRootElement(this.submit_button["nativeElement"]).click();
  }

  getExtract(agency: string, account: string, startDate: string, endDate: string) {
    this.extratoService.extractByDate(agency, account, startDate, endDate).subscribe({
      next: (extract: IExtrato[]) => {
        this.extrato = extract
      },
      error: error => {
        this.error_message = error.message;
        console.log("There was an error!", this.error_message);
      }
    })
  }

}
