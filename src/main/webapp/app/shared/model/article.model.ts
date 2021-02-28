import { ILigneCommande } from 'app/shared/model/ligne-commande.model';
import { ICommande } from 'app/shared/model/commande.model';

export interface IArticle {
  id?: number;
  refArticle?: string;
  designation?: string;
  description?: string;
  prix?: number;
  quantiteStocke?: number;
  image?: string;
  origine?: string;
  disponiblite?: boolean;
  selectionne?: boolean;
  ligneCommandes?: ILigneCommande[];
  categorieId?: number;
  commandes?: ICommande[];
}

export class Article implements IArticle {
  constructor(
    public id?: number,
    public refArticle?: string,
    public designation?: string,
    public description?: string,
    public prix?: number,
    public quantiteStocke?: number,
    public image?: string,
    public origine?: string,
    public disponiblite?: boolean,
    public selectionne?: boolean,
    public ligneCommandes?: ILigneCommande[],
    public categorieId?: number,
    public commandes?: ICommande[]
  ) {
    this.disponiblite = this.disponiblite || false;
    this.selectionne = this.selectionne || false;
  }
}
