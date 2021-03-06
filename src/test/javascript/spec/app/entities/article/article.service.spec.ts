import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ArticleService } from 'app/entities/article/article.service';
import { IArticle, Article } from 'app/shared/model/article.model';

describe('Service Tests', () => {
  describe('Article Service', () => {
    let injector: TestBed;
    let service: ArticleService;
    let httpMock: HttpTestingController;
    let elemDefault: IArticle;
    let expectedResult: IArticle | IArticle[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ArticleService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Article(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 'AAAAAAA', false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Article', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Article()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Article', () => {
        const returnedFromService = Object.assign(
          {
            refArticle: 'BBBBBB',
            designation: 'BBBBBB',
            description: 'BBBBBB',
            prix: 1,
            quantiteStocke: 1,
            image: 'BBBBBB',
            origine: 'BBBBBB',
            disponiblite: true,
            selectionne: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Article', () => {
        const returnedFromService = Object.assign(
          {
            refArticle: 'BBBBBB',
            designation: 'BBBBBB',
            description: 'BBBBBB',
            prix: 1,
            quantiteStocke: 1,
            image: 'BBBBBB',
            origine: 'BBBBBB',
            disponiblite: true,
            selectionne: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Article', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
