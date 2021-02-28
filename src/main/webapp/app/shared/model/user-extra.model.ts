import { ICommande } from 'app/shared/model/commande.model';

export interface IUserExtra {
  id?: number;
  localisation?: string;
  userId?: number;
  commandes?: ICommande[];
}

export class UserExtra implements IUserExtra {
  constructor(public id?: number, public localisation?: string, public userId?: number, public commandes?: ICommande[]) {}
}
