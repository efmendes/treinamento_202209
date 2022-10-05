import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastraEditaContaComponent } from './cadastra-edita-conta.component';

describe('CadastraEditaContaComponent', () => {
  let component: CadastraEditaContaComponent;
  let fixture: ComponentFixture<CadastraEditaContaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CadastraEditaContaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CadastraEditaContaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
