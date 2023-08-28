import { TestBed } from '@angular/core/testing';

import { TestersService } from './testers.service';

describe('TestersService', () => {
  let service: TestersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TestersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
