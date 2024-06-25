import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserStorageService } from '../services/storage/user-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm! : FormGroup;
  hidePassword = true;

  constructor(private fb:FormBuilder,private authService:AuthService, private userStorageService: UserStorageService,    private snackBar: MatSnackBar, private router: Router){}
  ngOnInit(){
    this.loginForm=this.fb.group({
      email:[null,[Validators.required,
        Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")
      ]],
      password:[null,[Validators.required]]
    })
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }
  onSubmit():void{
    const username=this.loginForm.get('email')!.value;
    const password=this.loginForm.get('password')!.value;
   this.authService.login(username,password).subscribe({
    next:(res:any)=>{
      if(this.userStorageService.isAdminLoggedIn()){
        this.router.navigateByUrl('admin/dashboard');
      }else if(this.userStorageService.isCustomerLoggedIn()){
        this.router.navigateByUrl('customer/dashboard');
      }
    },
    error: (error) => {
      this.snackBar.open('Bad credentials', 'ERROR', { duration: 5000 });
    }
   })
  }
}
