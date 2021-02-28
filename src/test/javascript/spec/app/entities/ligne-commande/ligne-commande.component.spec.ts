import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EDiaymaTestModule } from '../../../test.module';
import { LigneCommandeComponent } from 'app/entities/ligne-commande/ligne-commande.component';
import { LigneCommandeService } from 'app/entities/ligne-commande/ligne-commande.service';
import { LigneCommande } from 'app/shared/model/ligne-commande.model';

describe('Component Tests', () => {
  describe('LigneCommande Management Component', () => {
    let comp: LigneCommandeComponent;
    let fixture: ComponentFixture<LigneCommandeComponent>;
    let service: LigneCommandeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EDiaymaTestModule],
        declarations: [LigneCommandeComponent],
      })
        .overrideTemplate(LigneCommandeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LigneCommandeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LigneCommandeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LigneCommande(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ligneCommandes && comp.ligneCommandes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
