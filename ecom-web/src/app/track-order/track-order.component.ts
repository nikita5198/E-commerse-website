import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { UserStorageService } from '../services/storage/user-storage.service';

@Component({
  selector: 'app-track-order',
  templateUrl: './track-order.component.html',
  styleUrls: ['./track-order.component.scss']
})
export class TrackOrderComponent {
  
  searchOrderForm !:FormGroup;
  order:any;
  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private userStorageService: UserStorageService,
    private snackBar: MatSnackBar,
    private router: Router) { }

    ngOnInit(){
      this.searchOrderForm =this.fb.group({
        trackingId:[null,[Validators.required]]
      })
    }
    submitForm(){
      this.authService.getOrderByTrackingId(this.searchOrderForm.get('trackingId').value).subscribe(res=>{
        console.log(res);
        this.order=res;
      })
    }
}
