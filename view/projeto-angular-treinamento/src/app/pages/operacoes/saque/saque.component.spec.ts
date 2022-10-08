import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaqueComponent } from './saque.component';

describe('SaqueComponent', () => {
  let component: SaqueComponent;
  let fixture: ComponentFixture<SaqueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SaqueComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SaqueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
