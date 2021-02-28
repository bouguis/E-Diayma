import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICommande, Commande } from 'app/shared/model/commande.model';
import { CommandeService } from './commande.service';
import { IArticle } from 'app/shared/model/article.model';
import { ArticleService } from 'app/entities/article/article.service';
import { IUserExtra } from 'app/shared/model/user-extra.model';
import { UserExtraService } from 'app/entities/user-extra/user-extra.service';

type SelectableEntity = IArticle | IUserExtra;

@Component({
  selector: 'jhi-commande-update',
  templateUrl: './commande-update.component.html',
})
export class CommandeUpdateComponent implements OnInit {
  isSaving = false;
  articles: IArticle[] = [];
  userextras: IUserExtra[] = [];

  editForm = this.fb.group({
    id: [],
    refCommande: [],
    etatCommande: [],
    destination: [],
    articles: [],
    userExtraId: [],
  });

  constructor(
    protected commandeService: CommandeService,
    protected articleService: ArticleService,
    protected userExtraService: UserExtraService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ commande }) => {
      this.updateForm(commande);

      this.articleService.query().subscribe((res: HttpResponse<IArticle[]>) => (this.articles = res.body || []));

      this.userExtraService.query().subscribe((res: HttpResponse<IUserExtra[]>) => (this.userextras = res.body || []));
    });
  }

  updateForm(commande: ICommande): void {
    this.editForm.patchValue({
      id: commande.id,
      refCommande: commande.refCommande,
      etatCommande: commande.etatCommande,
      destination: commande.destination,
      articles: commande.articles,
      userExtraId: commande.userExtraId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const commande = this.createFromForm();
    if (commande.id !== undefined) {
      this.subscribeToSaveResponse(this.commandeService.update(commande));
    } else {
      this.subscribeToSaveResponse(this.commandeService.create(commande));
    }
  }

  private createFromForm(): ICommande {
    return {
      ...new Commande(),
      id: this.editForm.get(['id'])!.value,
      refCommande: this.editForm.get(['refCommande'])!.value,
      etatCommande: this.editForm.get(['etatCommande'])!.value,
      destination: this.editForm.get(['destination'])!.value,
      articles: this.editForm.get(['articles'])!.value,
      userExtraId: this.editForm.get(['userExtraId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICommande>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: IArticle[], option: IArticle): IArticle {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
