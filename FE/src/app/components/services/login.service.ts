import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../../environments/environment";
import { RegisterRequest, User } from "../constants/user-constants";

@Injectable({ providedIn: 'root' })
export class LoginService {
    private readonly http = inject(HttpClient);
    private readonly baseUrl = environment.apiBaseUrl.replace(/\/+$/, '');

    getUserByEmail(email: string): Observable<User> {
        return this.http.get<User>(`${this.baseUrl}/users/${email}`);
    }

    register(user: RegisterRequest): Observable<User> {
        return this.http.post<User>(`${this.baseUrl}/users`, user);
    }
}