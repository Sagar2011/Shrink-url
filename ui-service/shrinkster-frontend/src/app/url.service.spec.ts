import { TestBed } from '@angular/core/testing';

import { UrlService } from './url.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('UrlService', () => {
  beforeEach(() => TestBed.configureTestingModule({imports:[
    HttpClientTestingModule
  ]}));

  it('should be created', () => {
    const service: UrlService = TestBed.get(UrlService);
    expect(service).toBeTruthy();
  });
});
