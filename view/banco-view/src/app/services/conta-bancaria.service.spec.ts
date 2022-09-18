import { TestBed } from '@angular/core/testing';

import { ContaBancariaService } from './conta-bancaria.service';

describe('ContaBancariaService', () => {
  let service: ContaBancariaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContaBancariaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
