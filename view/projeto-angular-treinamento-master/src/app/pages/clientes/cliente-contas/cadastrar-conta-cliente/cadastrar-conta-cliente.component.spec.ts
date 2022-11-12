import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarContaClienteComponent } from './cadastrar-conta-cliente.component';

describe('CadastrarContaClienteComponent', () => {
  let component: CadastrarContaClienteComponent;
  let fixture: ComponentFixture<CadastrarContaClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastrarContaClienteComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastrarContaClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
