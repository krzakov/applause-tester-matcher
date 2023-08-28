import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeviceService {
  private devicesApiUrl = 'http://localhost:8080/api/devices';

  constructor(private http: HttpClient) {}

  getDevices(): Observable<string[]> {
    return this.http.get<string[]>(this.devicesApiUrl);
  }
}
