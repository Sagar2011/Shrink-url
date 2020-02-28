import { TestBed } from '@angular/core/testing';

import { AuthInterceptorService } from './auth-interceptor.service';
import { RouterTestingModule } from '@angular/router/testing';

describe('AuthInterceptorService', () => {
  beforeEach(() => TestBed.configureTestingModule({imports:[
    RouterTestingModule
  ]}));

  it('should be created', () => {
    const service: AuthInterceptorService = TestBed.get(AuthInterceptorService);
    expect(service).toBeTruthy();
  });
});
