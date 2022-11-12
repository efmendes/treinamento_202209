import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarEditarComponent } from './cadastrar.component';

describe('CadastrarComponent', () => {
  let component: CadastrarEditarComponent;
  let fixture: ComponentFixture<CadastrarEditarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastrarEditarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastrarEditarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
