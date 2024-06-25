import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminService } from 'src/app/admin/service/admin.service';
import { CustomerService } from '../../services/customer.service';
import { UserStorageService } from 'src/app/services/storage/user-storage.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  products:any[]=[];
  searchProductForm!:FormGroup;
  wishlist=false;
  constructor(private customerService:CustomerService,private fb:FormBuilder,private snackBar:MatSnackBar,private userStorageService:UserStorageService){}

  ngOnInit(){
    this.getAllProducts();
    this.searchProductForm=this.fb.group({
      title:[null,[Validators.required]]
    })
  }
  getAllProducts(){
    this.products=[];
    this.customerService.getAllProducts().subscribe(res=>{
      console.log(res);
      res.forEach(element => {
        element.processedImg='data:image/jpeg;base64,'+element.byteImg;
        this.products.push(element);
      });
      console.log(this.products);
    })
  }
  submitForm(){
    this.products=[];
    const title=this.searchProductForm.get('title')!.value;

    this.customerService.getAllProductsByName(title).subscribe(res=>{
      res.forEach(element => {
        element.processedImg='data:image/jpeg;base64,'+element.byteImg;
        this.products.push(element);
      });
      console.log(this.products);
    })
  }
  
  addToCart(id:any){
   this.customerService.addToCart(id).subscribe(res=>{
    this.snackBar.open("Product added to cart successfully","Close",{duration:5000});
   })
  }

  addToWishlist(id:any){
    const wishlistDto={
      productId:id,
      userId:this.userStorageService.getUserId()
    }
    this.customerService.addProductToWishlist(wishlistDto).subscribe(res=>{
      if(res.id !=null){
        this.snackBar.open('Product added to Wishlist successfully!','Close',{duration:5000});
        this.wishlist=true;
      }else{
        this.snackBar.open("Already in wishlist",'ERROR',{duration:5000});
      }
    })
  }
}
