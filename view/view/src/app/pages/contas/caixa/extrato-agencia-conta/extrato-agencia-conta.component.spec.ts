import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExtratoAgenciaContaComponent } from './extrato-agencia-conta.component';

describe('ExtratoAgenciaContaComponent', () => {
  let component: ExtratoAgenciaContaComponent;
  let fixture: ComponentFixture<ExtratoAgenciaContaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExtratoAgenciaContaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExtratoAgenciaContaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
