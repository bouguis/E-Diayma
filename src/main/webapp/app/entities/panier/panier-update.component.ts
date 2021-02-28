import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPanier, Panier } from 'app/shared/model/panier.model';
import { PanierService } from './panier.service';
import { ILigneCommande } from 'app/shared/model/ligne-commande.model';
import { LigneCommandeService } from 'app/entities/ligne-commande/ligne-commande.service';

@Component({
  selector: 'jhi-panier-update',
  templateUrl: './panier-update.component.html',
})
export class PanierUpdateComponent implements OnInit {
  isSaving = false;
  lignecommandes: ILigneCommande[] = [];

  editForm = this.fb.group({
    id: [],
    codePanier: [],
    ligneCommandeId: [],
  });

  constructor(
    protected panierService: PanierService,
    protected ligneCommandeService: LigneCommandeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ panier }) => {
      this.updateForm(panier);

      this.ligneCommandeService.query().subscribe((res: HttpResponse<ILigneCommande[]>) => (this.lignecommandes = res.body || []));
    });
  }

  updateForm(panier: IPanier): void {
    this.editForm.patchValue({
      id: panier.id,
      codePanier: panier.codePanier,
      ligneCommandeId: panier.ligneCommandeId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const panier = this.createFromForm();
    if (panier.id !== undefined) {
      this.subscribeToSaveResponse(this.panierService.update(panier));
    } else {
      this.subscribeToSaveResponse(this.panierService.create(panier));
    }
  }

  private createFromForm(): IPanier {
    return {
      ...new Panier(),
      id: this.editForm.get(['id'])!.value,
      codePanier: this.editForm.get(['codePanier'])!.value,
      ligneCommandeId: this.editForm.get(['ligneCommandeId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPanier>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: ILigneCommande): any {
    return item.id;
  }
}
