import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormSaqueDepositoComponent } from './form-saque-deposito.component';

describe('FormSaqueDepositoComponent', () => {
  let component: FormSaqueDepositoComponent;
  let fixture: ComponentFixture<FormSaqueDepositoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormSaqueDepositoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormSaqueDepositoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
