import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'user-extra',
        loadChildren: () => import('./user-extra/user-extra.module').then(m => m.EDiaymaUserExtraModule),
      },
      {
        path: 'pays',
        loadChildren: () => import('./pays/pays.module').then(m => m.EDiaymaPaysModule),
      },
      {
        path: 'commande',
        loadChildren: () => import('./commande/commande.module').then(m => m.EDiaymaCommandeModule),
      },
      {
        path: 'article',
        loadChildren: () => import('./article/article.module').then(m => m.EDiaymaArticleModule),
      },
      {
        path: 'categorie',
        loadChildren: () => import('./categorie/categorie.module').then(m => m.EDiaymaCategorieModule),
      },
      {
        path: 'panier',
        loadChildren: () => import('./panier/panier.module').then(m => m.EDiaymaPanierModule),
      },
      {
        path: 'ligne-commande',
        loadChildren: () => import('./ligne-commande/ligne-commande.module').then(m => m.EDiaymaLigneCommandeModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EDiaymaEntityModule {}
