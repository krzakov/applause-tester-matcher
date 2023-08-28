import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Tester} from "../model/tester.interface";



@Injectable({
  providedIn: 'root'
})
export class TestersService {
  private testersApiUrl = 'http://localhost:8080/api/testers';

  constructor(private http: HttpClient) {
  }

  getTesters(countryCodes: string[], deviceDescriptions: string[]): Observable<Tester[]> {

    const params = new HttpParams()
      .set('countryCodes', countryCodes.join(',')) // Convert array to comma-separated string
      .set('deviceDescriptions', deviceDescriptions.join(',')); // Convert array to comma-separated string

    return this.http.get<Tester[]>(this.testersApiUrl, {params});
  }
}
