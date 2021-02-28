export interface IPays {
  id?: number;
}

export class Pays implements IPays {
  constructor(public id?: number) {}
}
