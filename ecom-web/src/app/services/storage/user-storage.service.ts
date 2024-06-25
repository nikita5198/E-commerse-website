import { Injectable } from '@angular/core';

const TOKEN = 'ecom-token';
const USER = 'ecom-user';

@Injectable({
  providedIn: 'root'
})
export class UserStorageService {

  constructor() { }

  public saveToken(token: string): void {
    window.localStorage.setItem(TOKEN, token);
  }

  public saveUser(user: any): void {
    window.localStorage.setItem(USER, JSON.stringify(user));
  }

  public getToken(): string | null {
    return localStorage.getItem(TOKEN);
  }

  public getUser(): any {
    const user = localStorage.getItem(USER);
    if (user) {
      try {
        return JSON.parse(user);
      } catch (e) {
        console.error('Error parsing user data:', e);
        return null;
      }
    }
    return null;
  }

  public getUserId(): string {
    const user = this.getUser();
    return user ? user.userId : '';
  }

  public getUserRole(): string {
    const user = this.getUser();
    return user ? user.role : '';
  }

  public isAdminLoggedIn(): boolean {
    const token = this.getToken();
    if (!token) {
      return false;
    }
    const role = this.getUserRole();
    return role === 'ADMIN';
  }

  public isCustomerLoggedIn(): boolean {
    const token = this.getToken();
    if (!token) {
      return false;
    }
    const role = this.getUserRole();
    return role === 'CUSTOMER';
  }

  public signOut(): void {
    window.localStorage.removeItem(TOKEN);
    window.localStorage.removeItem(USER);
  }
}
