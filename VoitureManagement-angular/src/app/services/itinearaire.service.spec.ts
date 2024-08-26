import { TestBed } from '@angular/core/testing';

import { ItineraireService } from './itineraire.service';

describe('ItinearaireService', () => {
  let service: ItineraireService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItineraireService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
