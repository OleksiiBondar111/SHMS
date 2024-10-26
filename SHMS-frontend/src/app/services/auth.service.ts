import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthObject} from "../auth/model/auth.model";
import {of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'https://your-api-url.com/api'; // Replace with your API URL

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {
    const authObj: AuthObject = {access_token: '123', user: 'testUser'}
    // return this.http.post<{ token: string }>(`${this.apiUrl}/login`, {
    //   username,
    //   password,
    // }).pipe(map(response => response.token));
    return of(authObj).toPromise();
    // return this.http.post<AuthObject>(`${this.apiUrl}/testInterceptor`, {
    //   username,
    //   password,
    // }).toPromise();
  }

  testInterceptor(username: string, password: string) {
    return this.http.post<{ token: string }>(`${this.apiUrl}/testInterceptor`, {
      username,
      password,
    }).toPromise();
  }
}
