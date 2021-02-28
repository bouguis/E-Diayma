import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IArticle, Article } from 'app/shared/model/article.model';
import { ArticleService } from './article.service';
import { ICategorie } from 'app/shared/model/categorie.model';
import { CategorieService } from 'app/entities/categorie/categorie.service';

@Component({
  selector: 'jhi-article-update',
  templateUrl: './article-update.component.html',
})
export class ArticleUpdateComponent implements OnInit {
  isSaving = false;
  categories: ICategorie[] = [];

  editForm = this.fb.group({
    id: [],
    refArticle: [],
    designation: [],
    description: [],
    prix: [],
    quantiteStocke: [],
    image: [],
    origine: [],
    disponiblite: [],
    selectionne: [],
    categorieId: [],
  });

  constructor(
    protected articleService: ArticleService,
    protected categorieService: CategorieService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ article }) => {
      this.updateForm(article);

      this.categorieService.query().subscribe((res: HttpResponse<ICategorie[]>) => (this.categories = res.body || []));
    });
  }

  updateForm(article: IArticle): void {
    this.editForm.patchValue({
      id: article.id,
      refArticle: article.refArticle,
      designation: article.designation,
      description: article.description,
      prix: article.prix,
      quantiteStocke: article.quantiteStocke,
      image: article.image,
      origine: article.origine,
      disponiblite: article.disponiblite,
      selectionne: article.selectionne,
      categorieId: article.categorieId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const article = this.createFromForm();
    if (article.id !== undefined) {
      this.subscribeToSaveResponse(this.articleService.update(article));
    } else {
      this.subscribeToSaveResponse(this.articleService.create(article));
    }
  }

  private createFromForm(): IArticle {
    return {
      ...new Article(),
      id: this.editForm.get(['id'])!.value,
      refArticle: this.editForm.get(['refArticle'])!.value,
      designation: this.editForm.get(['designation'])!.value,
      description: this.editForm.get(['description'])!.value,
      prix: this.editForm.get(['prix'])!.value,
      quantiteStocke: this.editForm.get(['quantiteStocke'])!.value,
      image: this.editForm.get(['image'])!.value,
      origine: this.editForm.get(['origine'])!.value,
      disponiblite: this.editForm.get(['disponiblite'])!.value,
      selectionne: this.editForm.get(['selectionne'])!.value,
      categorieId: this.editForm.get(['categorieId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IArticle>>): void {
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

  trackById(index: number, item: ICategorie): any {
    return item.id;
  }
}
