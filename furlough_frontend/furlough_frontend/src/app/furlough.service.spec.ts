import { TestBed, inject } from '@angular/core/testing';

import { FurloughService } from './furlough.service';

describe('FurloughService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FurloughService]
    });
  });

  it('should be created', inject([FurloughService], (service: FurloughService) => {
    expect(service).toBeTruthy();
  }));
});
