import { IArticle } from 'app/shared/model/article.model';

export interface ICategorie {
  id?: number;
  code?: string;
  libelle?: string;
  famille?: string;
  articles?: IArticle[];
}

export class Categorie implements ICategorie {
  constructor(public id?: number, public code?: string, public libelle?: string, public famille?: string, public articles?: IArticle[]) {}
}
