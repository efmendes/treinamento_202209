import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormTransferComponent } from './form-transfer.component';

describe('FormTransferComponent', () => {
  let component: FormTransferComponent;
  let fixture: ComponentFixture<FormTransferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FormTransferComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormTransferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
