import { ILigneCommande } from 'app/shared/model/ligne-commande.model';
import { IArticle } from 'app/shared/model/article.model';

export interface ICommande {
  id?: number;
  refCommande?: string;
  etatCommande?: string;
  destination?: string;
  ligneCommandes?: ILigneCommande[];
  articles?: IArticle[];
  userExtraId?: number;
}

export class Commande implements ICommande {
  constructor(
    public id?: number,
    public refCommande?: string,
    public etatCommande?: string,
    public destination?: string,
    public ligneCommandes?: ILigneCommande[],
    public articles?: IArticle[],
    public userExtraId?: number
  ) {}
}
