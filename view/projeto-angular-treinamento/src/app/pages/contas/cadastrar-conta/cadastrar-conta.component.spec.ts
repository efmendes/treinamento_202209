import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarContaComponent } from './cadastrar-conta.component';

describe('CadastrarContaComponent', () => {
  let component: CadastrarContaComponent;
  let fixture: ComponentFixture<CadastrarContaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastrarContaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastrarContaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
