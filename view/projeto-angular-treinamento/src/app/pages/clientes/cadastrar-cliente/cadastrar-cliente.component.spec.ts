import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarEditarClienteComponent } from './cadastrar-cliente.component';

describe('CadastrarClienteComponent', () => {
  let component: CadastrarEditarClienteComponent;
  let fixture: ComponentFixture<CadastrarEditarClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CadastrarEditarClienteComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CadastrarEditarClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
