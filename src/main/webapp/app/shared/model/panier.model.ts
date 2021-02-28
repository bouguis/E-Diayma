export interface IPanier {
  id?: number;
  codePanier?: string;
  ligneCommandeId?: number;
}

export class Panier implements IPanier {
  constructor(public id?: number, public codePanier?: string, public ligneCommandeId?: number) {}
}
