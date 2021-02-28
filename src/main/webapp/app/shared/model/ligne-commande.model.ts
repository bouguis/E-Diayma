import { IPanier } from 'app/shared/model/panier.model';

export interface ILigneCommande {
  id?: number;
  quantiteCommande?: number;
  prixTotal?: number;
  paniers?: IPanier[];
  commandeId?: number;
  articleId?: number;
}

export class LigneCommande implements ILigneCommande {
  constructor(
    public id?: number,
    public quantiteCommande?: number,
    public prixTotal?: number,
    public paniers?: IPanier[],
    public commandeId?: number,
    public articleId?: number
  ) {}
}
